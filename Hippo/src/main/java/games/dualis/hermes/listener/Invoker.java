package games.dualis.hermes.listener;

/**
 * An object used to invoke a method in a given parent object.
 */
@FunctionalInterface
public interface Invoker {

    /**
     * Invokes a specific method in given parent instance.
     *
     * @param parent the parent
     * @param event the event
     */
    void invoke(Object parent, Object event);

}
