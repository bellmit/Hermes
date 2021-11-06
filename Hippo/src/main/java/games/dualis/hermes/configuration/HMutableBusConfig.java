package games.dualis.hermes.configuration;

import games.dualis.hermes.MutableEventBus;
import games.dualis.hermes.listener.invoker.InvokerClassLoader;
import games.dualis.hermes.listener.scout.CompiledListenerScout;
import games.dualis.hermes.listener.scout.ListenerScout;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class HMutableBusConfig implements MutableEventBus.Configuration<HMutableBusConfig> {

    private String id = "Hippo";
    private InvokerClassLoader loader = new InvokerClassLoader(this.getClass().getClassLoader());
    private Collection<ListenerScout> scouts = List.of(CompiledListenerScout.SINGLETON);

    @Override
    public String id() {
        return id;
    }

    @Override
    public HMutableBusConfig id(String id) {
        this.id = id;
        return this;
    }

    @Override
    public ClassLoader loader() {
        return loader;
    }

    @Override
    public HMutableBusConfig loader(ClassLoader loader) {
        this.loader = new InvokerClassLoader(loader);
        return this;
    }

    @Override
    public boolean cache() {
        return true;
    }

    @Override
    public HMutableBusConfig cache(boolean value) {
        return this;
    }

    @Override
    public Collection<ListenerScout> scouts() {
        return scouts;
    }

    @Override
    public HMutableBusConfig scout(ListenerScout scout) {
        this.scouts = List.of(scout);
        return this;
    }

    @Override
    public HMutableBusConfig scout(Collection<ListenerScout> scouts) {
        this.scouts = scouts;
        return this;
    }
}
