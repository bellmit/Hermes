package games.dualis.hermes.audience;

import games.dualis.hermes.listener.Listener;

import java.util.Collection;
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
    MutableEventAudience subscribe(Listener listener);

    /**
     * Adds each listener in given iterable to this audience.
     *
     * @param listeners the listeners
     * @return the audience
     */
    MutableEventAudience subscribe(Collection<Listener> listeners);

    /**
     * Adds each listener in given array to this audience.
     *
     * @param listeners the listeners
     * @return the audience
     */
    default MutableEventAudience subscribe(Listener... listeners) {
        return subscribe(List.of(listeners));
    }

    /**
     * Removes given listener to this audience.
     *
     * @param listener the listener
     * @return the audience
     */
    MutableEventAudience unsubscribe(Listener listener);

    /**
     * Removes each listener in given iterable to this audience.
     *
     * @param listeners the listeners
     * @return the audience
     */
    MutableEventAudience unsubscribe(Collection<Listener> listeners);

    /**
     * Removes each listener in given array to this audience.
     *
     * @param listeners the listeners
     * @return the audience
     */
    default MutableEventAudience unsubscribe(Listener... listeners) {
        return subscribe(List.of(listeners));
    }

}
