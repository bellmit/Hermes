package games.dualis.hermes.listener.scout;

import games.dualis.hermes.MutableEventBus;
import games.dualis.hermes.listener.HippoListener;
import games.dualis.hermes.listener.Listen;
import games.dualis.hermes.listener.Listener;
import games.dualis.hermes.listener.invoker.InvokerClassLoader;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class CompiledListenerScout implements ListenerScout {

    public static final CompiledListenerScout SINGLETON = new CompiledListenerScout();

    CompiledListenerScout() {}

    @Override
    public List<Listener> scout(MutableEventBus.Configuration<?> configuration, Object object) {
        return Arrays.stream(object.getClass().getDeclaredMethods())
                .filter(m -> isCompilable(object, m))
                .map(m -> compile(configuration.loader(), object, m))
                .toList();
    }

    private static boolean isCompilable(Object parent, Method method) {
        return method.isAnnotationPresent(Listen.class)
                && method.getParameterCount() == 1
                && method.canAccess(parent);
    }

    private static Listener compile(ClassLoader loader, Object parent, Method method) {
        return new HippoListener(parent, method, (InvokerClassLoader) loader);
    }

}
