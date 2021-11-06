package games.dualis.hermes;

import games.dualis.hermes.audience.EventAudience;
import games.dualis.hermes.audience.immutable.EmptyEventAudience;
import games.dualis.hermes.configuration.HBusConfig;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class HippoEventBus implements EventBus<EventAudience> {

    private final Map<Class<?>, EventAudience> audiences;
    private final HBusConfig configuration = new HBusConfig();

    public HippoEventBus(Map<Class<?>, EventAudience> audiences) {
        this.audiences = audiences;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Configuration<HBusConfig> configuration() {
        return configuration;
    }

    @Override
    public Set<Class<?>> topics() {
        return audiences.keySet();
    }

    @Override
    public EventAudience audience(Class<?> topic) {
        return audiences.getOrDefault(topic, EmptyEventAudience.create(topic));
    }

}
