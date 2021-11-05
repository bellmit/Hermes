package games.dualis.hermes;

import games.dualis.hermes.audience.MutableEventAudience;
import games.dualis.hermes.listener.scout.ListenerScout;

import java.util.List;

/**
 * An extension of {@link EventBus} which allows mutation of the audiences.
 */
public interface MutableEventBus<Configuration, Audience extends MutableEventAudience> extends EventBus<Configuration, Audience> {

    /**
     * Scouts given object using the listener scouts and subscribes the
     * listeners to their audience.
     * @see ListenerScout
     *
     * @param object the object
     */
    void subscribe(Object object);

    /**
     * Scouts given object using the listener scouts and unsubscribes the
     * listeners to their audience.
     * @see ListenerScout
     *
     * @param object the object
     */
    void unsubscribe(Object object);

    /**
     * Removes the audience for given topic.
     *
     * @param topic the topic
     */
    void clear(Class<?> topic);

    /**
     * An object used to configure a {@link MutableEventBus} which is an extension of {@link EventBus.Configuration}
     *
     * @param <T> the configuration type
     */
    interface Configuration<T> extends EventBus.Configuration<T> {
        /**
         * Enables scout caching, accelerating the process of subscription/unsubscription.
         *
         * @param value the value
         * @return the configuration
         */
        T cache(boolean value);

        /**
         * Defines the scout used by the bus.
         *
         * @param scout the scout
         * @return the configuration
         */
        T scout(ListenerScout scout);

        /**
         * Defines the scouts used by the bus.
         *
         * @param scouts the scouts
         * @return the configuration
         */
        T scout(Iterable<? extends ListenerScout> scouts);

        /**
         * Defines the scouts used by the bus.
         *
         * @param scouts the scouts
         * @return the configuration
         */
        default T scout(ListenerScout... scouts) {
            return scout(List.of(scouts));
        }
    }

}
