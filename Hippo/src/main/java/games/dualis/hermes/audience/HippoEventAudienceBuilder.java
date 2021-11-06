package games.dualis.hermes.audience;

import games.dualis.hermes.listener.Listener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HippoEventAudienceBuilder implements EventAudience.Builder {

    private Class<?> topic;
    private final List<Listener> listeners = new ArrayList<>();

    @Override
    public Class<?> topic() {
        return topic;
    }

    @Override
    public EventAudience.Builder topic(Class<?> topic) {
        this.topic = topic;
        return this;
    }

    @Override
    public List<Listener> listeners() {
        return listeners;
    }

    @Override
    public EventAudience.Builder with(Listener listener) {
        listeners.add(listener);
        return this;
    }

    @Override
    public EventAudience.Builder with(Collection<Listener> listeners) {
        this.listeners.addAll(listeners);
        return this;
    }

}
