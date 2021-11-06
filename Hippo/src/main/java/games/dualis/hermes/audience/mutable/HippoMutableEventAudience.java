package games.dualis.hermes.audience.mutable;

import games.dualis.hermes.audience.MutableEventAudience;
import games.dualis.hermes.listener.HippoListener;
import games.dualis.hermes.listener.Listener;

import java.util.*;

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
        final var list = new ArrayList<>(List.of(this.listeners));
        list.add(listener);
        list.sort(Comparator.comparingInt(Listener::priority));
        listeners = list.toArray(Listener[]::new);

        return this;
    }

    @Override
    public MutableEventAudience subscribe(Collection<Listener> listeners) {
        final var list = new ArrayList<>(List.of(this.listeners));
        list.addAll(listeners);
        list.sort(Comparator.comparingInt(Listener::priority));
        this.listeners = list.toArray(Listener[]::new);

        return this;
    }

    @Override
    public MutableEventAudience unsubscribe(Listener listener) {
        final var list = new ArrayList<>(List.of(this.listeners));
        list.remove(listener);
        list.sort(Comparator.comparingInt(Listener::priority));
        listeners = list.toArray(Listener[]::new);

        return this;
    }

    @Override
    public MutableEventAudience unsubscribe(Collection<Listener> listeners) {
        final var list = new ArrayList<>(List.of(this.listeners));
        list.removeAll(listeners);
        list.sort(Comparator.comparingInt(Listener::priority));
        this.listeners = list.toArray(Listener[]::new);

        return this;
    }
}
