package games.dualis.hermes.audience.mutable;

import games.dualis.hermes.audience.MutableEventAudience;
import games.dualis.hermes.listener.HippoListener;
import games.dualis.hermes.listener.Listener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class HippoMutableEventAudience implements MutableEventAudience {

    private final Class<?> topic;
    private Listener[] listeners;

    public HippoMutableEventAudience(Class<?> topic, Listener[] listeners) {
        this.topic = topic;
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

    @Override
    public MutableEventAudience subscribe(Listener listener) {
        final var list = new ArrayList<>(List.of(listeners));
        list.add(listener);
        listeners = list.toArray(Listener[]::new);

        return this;
    }

    @Override
    public MutableEventAudience subscribe(Collection<Listener> listeners) {
        final var list = new ArrayList<>(listeners);
        list.addAll(listeners);
        this.listeners = list.toArray(Listener[]::new);

        return this;
    }

    @Override
    public MutableEventAudience unsubscribe(Listener listener) {
        final var list = new ArrayList<>(List.of(listeners));
        list.remove(listener);
        listeners = list.toArray(Listener[]::new);

        return this;
    }

    @Override
    public MutableEventAudience unsubscribe(Collection<Listener> listeners) {
        final var list = new ArrayList<>(listeners);
        list.removeAll(listeners);
        this.listeners = list.toArray(Listener[]::new);

        return this;
    }
}
