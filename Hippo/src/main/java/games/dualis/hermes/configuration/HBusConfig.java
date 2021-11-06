package games.dualis.hermes.configuration;

import games.dualis.hermes.EventBus;

public class HBusConfig implements EventBus.Configuration<HBusConfig> {

    private String id = "Hippo";

    @Override
    public String id() {
        return id;
    }

    @Override
    public HBusConfig id(String id) {
        this.id = id;
        return this;
    }
}
