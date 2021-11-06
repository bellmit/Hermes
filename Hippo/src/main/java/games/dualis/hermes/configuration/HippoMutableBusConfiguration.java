package games.dualis.hermes.configuration;

import games.dualis.hermes.MutableEventBus;
import games.dualis.hermes.listener.invoker.InvokerClassLoader;
import games.dualis.hermes.listener.scout.ListenerScout;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class HippoMutableBusConfiguration implements MutableEventBus.Configuration<HippoMutableBusConfiguration> {

    private String id = "Hippo";
    private InvokerClassLoader loader = new InvokerClassLoader(this.getClass().getClassLoader());
    private Collection<ListenerScout> scouts = Collections.emptyList();

    @Override
    public String id() {
        return id;
    }

    @Override
    public HippoMutableBusConfiguration id(String id) {
        this.id = id;
        return this;
    }

    @Override
    public ClassLoader loader() {
        return loader;
    }

    @Override
    public HippoMutableBusConfiguration loader(ClassLoader loader) {
        this.loader = new InvokerClassLoader(loader);
        return this;
    }

    @Override
    public boolean cache() {
        return true;
    }

    @Override
    public HippoMutableBusConfiguration cache(boolean value) {
        return this;
    }

    @Override
    public Collection<ListenerScout> scouts() {
        return scouts;
    }

    @Override
    public HippoMutableBusConfiguration scout(ListenerScout scout) {
        this.scouts = List.of(scout);
        return this;
    }

    @Override
    public HippoMutableBusConfiguration scout(Collection<ListenerScout> scouts) {
        this.scouts = scouts;
        return this;
    }
}
