package games.dualis.hermes.audience;

import games.dualis.hermes.listener.Listener;

import java.util.List;

/**
 * An extension of {@link EventAudience} which allows mutation of the collection of listeners.
 */
public interface MutableEventAudience extends EventAudience {

    /**
     * Adds given listener to this audience.
     *
     * @param listener the listener
     * @return the audience
     */
    MutableEventAudience add(Listener listener);

    /**
     * Adds each listener in given iterable to this audience.
     *
     * @param listeners the listeners
     * @return the audience
     */
    MutableEventAudience add(Iterable<? extends Listener> listeners);

    /**
     * Adds each listener in given array to this audience.
     *
     * @param listeners the listeners
     * @return the audience
     */
    default MutableEventAudience add(Listener... listeners) {
        return add(List.of(listeners));
    }

    /**
     * Removes given listener to this audience.
     *
     * @param listener the listener
     * @return the audience
     */
    MutableEventAudience remove(Listener listener);

    /**
     * Removes each listener in given iterable to this audience.
     *
     * @param listeners the listeners
     * @return the audience
     */
    MutableEventAudience remove(Iterable<? extends Listener> listeners);

    /**
     * Removes each listener in given array to this audience.
     *
     * @param listeners the listeners
     * @return the audience
     */
    default MutableEventAudience remove(Listener... listeners) {
        return add(List.of(listeners));
    }

}
