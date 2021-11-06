package games.dualis.hermes.audience.immutable;

import games.dualis.hermes.audience.EventAudience;
import games.dualis.hermes.listener.HippoListener;
import games.dualis.hermes.listener.Listener;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class HippoEventAudience implements EventAudience {

    private final Class<?> topic;
    private final Listener[] listeners;

    public HippoEventAudience(Class<?> topic, Listener[] listeners) {
        this.topic = topic;
        Arrays.sort(listeners, Comparator.comparingInt(Listener::priority));
        this.listeners = listeners;
    }

    @Override
    public Class<?> topic() {
        return topic;
    }

    @Override
    public Collection<Listener> listeners() {
        return Arrays.asList(listeners);
    }

    @Override
    public void dispatch(Object event) {
        final var size = listeners.length;
        for(var i = 0; i < size; i++) {
            listeners[i].receive(event);
        }
    }



}
