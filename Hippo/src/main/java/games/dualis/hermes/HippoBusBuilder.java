package games.dualis.hermes;

import games.dualis.hermes.audience.EventAudience;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HippoBusBuilder implements EventBus.Builder {

    private EventBus.Configuration<?> configuration;
    private final List<EventAudience> audiences = new ArrayList<>();

    @Override
    public List<EventAudience> audiences() {
        return audiences;
    }

    @Override
    public EventBus.Builder with(EventAudience audience) {
        audiences.add(audience);
        return this;
    }

    @Override
    public EventBus.Builder with(Collection<EventAudience> audiences) {
        this.audiences.addAll(audiences);
        return this;
    }

}
