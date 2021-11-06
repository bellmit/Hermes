package games.dualis.hermes.audience.immutable;

import games.dualis.hermes.audience.EventAudience;
import games.dualis.hermes.listener.Listener;

import java.util.Collection;
import java.util.Collections;

public record EmptyEventAudience(Class<?> topic) implements EventAudience {

    public static EmptyEventAudience create(Class<?> topic) {
        return new EmptyEventAudience(topic);
    }

    @Override
    public Collection<Listener> listeners() {
        return Collections.emptyList();
    }

    @Override
    public void dispatch(Object event) {

    }
}
