package games.dualis.hermes.configuration;

import games.dualis.hermes.EventBus;
import games.dualis.hermes.listener.invoker.InvokerClassLoader;

public class HippoBusConfiguration implements EventBus.Configuration<HippoBusConfiguration> {

    private String id = "Hippo";

    @Override
    public String id() {
        return id;
    }

    @Override
    public HippoBusConfiguration id(String id) {
        this.id = id;
        return this;
    }
}
