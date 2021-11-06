package games.dualis.hermes.listener;

import games.dualis.hermes.listener.invoker.Invoker;
import games.dualis.hermes.listener.invoker.InvokerClassFile;
import games.dualis.hermes.listener.invoker.InvokerClassLoader;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class HippoListener implements Listener {

    /**
     * Gets a unique method name from a method instance.
     *
     * @param method The method.
     * @return The unique name.
     */
    static String getUniqueMethodName(Method method) {
        final var parameters = new StringBuilder();
        for (Parameter parameter : method.getParameters()) {
            parameters.append(parameter.getType().getName().replace('.', '_'));
        }
        return method.getName() + parameters;
    }

    private final Object parent;
    private final Method method;
    private final Invoker invoker;

    public HippoListener(Object parent, Method method, InvokerClassLoader loader) {
        this.parent = parent;
        this.method = method;

        final var name = "lwjeb/generated/" + parent.getClass().getName().replace('.', '/') + "/" + getUniqueMethodName(method);
        final var invokerClass = new InvokerClassFile(parent.getClass(), method.getParameterTypes()[0], method, name);

        try {
            final var clazz = loader.createClass(name.replace('/', '.'), invokerClass.toByteArray());

            this.invoker = (Invoker) clazz.getConstructor()
                    .newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
