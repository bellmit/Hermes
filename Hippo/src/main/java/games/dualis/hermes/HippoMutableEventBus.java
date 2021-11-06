package games.dualis.hermes;

import games.dualis.hermes.audience.mutable.HippoMutableEventAudience;
import games.dualis.hermes.configuration.HippoMutableBusConfiguration;
import games.dualis.hermes.listener.Listener;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;

public class HippoMutableEventBus implements MutableEventBus<HippoMutableBusConfiguration, HippoMutableEventAudience> {

    private final Map<Class<?>, HippoMutableEventAudience> audiences;
    private final HippoMutableBusConfiguration configuration = new HippoMutableBusConfiguration();
    private final Cache cache = new Cache();

    public HippoMutableEventBus(Map<Class<?>, HippoMutableEventAudience> audiences) {
        this.audiences = new HashMap<>(audiences);
    }

    @Override
    public EventBus.Configuration<HippoMutableBusConfiguration> configuration() {
        return configuration;
    }

    @Override
    public HippoMutableEventAudience audience(Class<?> topic) {
        var audience = audiences.get(topic);
        if(audience == null) {
           audience = new HippoMutableEventAudience(topic, new Listener[]{});
           audiences.put(topic, audience);
        }
        return audience;
    }

    @Override
    public void subscribe(Object object) {
        final var listeners = cache.get(object).orElseGet(() ->
                configuration.scouts().stream()
                        .map(s -> s.scout(configuration, object))
                        .flatMap(List::stream)
                        .collect(groupingBy(Listener::topic))
        );

        listeners.forEach((t, l) -> audience(t).subscribe(l));
    }

    @Override
    public void unsubscribe(Object object) {
        final var listeners = cache.get(object).orElseGet(() ->
                configuration.scouts().stream()
                        .map(s -> s.scout(configuration, object))
                        .flatMap(List::stream)
                        .collect(groupingBy(Listener::topic))
        );

        listeners.forEach((t, l) -> audience(t).unsubscribe(l));
    }

    @Override
    public void clear(Class<?> topic) {
        audiences.remove(topic);
    }

    public static class Cache {
        private final Map<Object, Map<Class<?>, List<Listener>>> cache = new WeakHashMap<>();

        public Cache cache(Object object, Map<Class<?>, List<Listener>> scout) {
            this.cache.put(object, scout);
            return this;
        }

        public Optional<Map<Class<?>, List<Listener>>> get(Object object) {
            return Optional.ofNullable(cache.get(object));
        }
    }

}
