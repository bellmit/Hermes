package games.dualis.hermes;

import games.dualis.hermes.audience.EventAudience;
import games.dualis.hermes.audience.immutable.EmptyEventAudience;
import games.dualis.hermes.configuration.HippoBusConfiguration;

import java.util.Map;

public class HippoEventBus implements EventBus<HippoBusConfiguration, EventAudience> {

    private final Map<Class<?>, EventAudience> audiences;
    private final HippoBusConfiguration configuration = new HippoBusConfiguration();

    public HippoEventBus(Map<Class<?>, EventAudience> audiences) {
        this.audiences = audiences;
    }

    @Override
    public Configuration<HippoBusConfiguration> configuration() {
        return configuration;
    }

    @Override
    public EventAudience audience(Class<?> topic) {
        return audiences.getOrDefault(topic, EmptyEventAudience.create(topic));
    }

}
