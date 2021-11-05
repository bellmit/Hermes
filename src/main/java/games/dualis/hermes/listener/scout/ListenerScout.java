package games.dualis.hermes.listener.scout;

import games.dualis.hermes.listener.Listener;

import java.util.List;

/**
 * An object used to scout {@link Listener} instances.
 *
 * <p>The scouts should be singleton inside of the programs or should be provided
 * by default in the configuration relative to each implementations.</p>
 */
public interface ListenerScout {

    /**
     * Scouts an object and searches for any trace of {@link Listener} instance
     * inside them.
     *
     * <p>If a listener is found and accessible, it will be returned.</p>
     *
     * @param object the object
     * @return the listeners
     */
    List<Listener> scout(Object object);

}
