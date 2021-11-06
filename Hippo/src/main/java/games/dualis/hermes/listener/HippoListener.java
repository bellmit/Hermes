package games.dualis.hermes.listener;

import java.lang.reflect.Method;
import java.util.function.BiConsumer;

public class HippoListener implements Listener {

    private final Object parent;
    private final Method method;
    private final Invoker invoker;

    public HippoListener(Object parent, Method method) {
        this.parent = parent;
        this.method = method;
        this.invoker = ((o, o2) -> {});
    }

    @Override
    public Class<?> topic() {
        return method.getParameterTypes()[0];
    }

    @Override
    public int priority() {
        return method.getAnnotation(Listen.class).value();
    }

    @Override
    public void receive(Object event) {
        invoker.invoke(parent, event);
    }
}
