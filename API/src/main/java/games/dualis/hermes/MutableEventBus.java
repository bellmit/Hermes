package games.dualis.hermes;

import games.dualis.hermes.audience.MutableEventAudience;
import games.dualis.hermes.listener.scout.ListenerScout;

import java.util.Collection;
import java.util.List;

/**
 * An extension of {@link EventBus} which allows mutation of the audiences.
 */
public interface MutableEventBus<Audience extends MutableEventAudience> extends EventBus<Audience> {

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
         * Returns the {@link ClassLoader} used to define new classes.
         *
         * <p>A lot of implementations will inject classes compiled at runtime in
         * the class loader. Some environments require to use specific class loaders.</p>
         *
         * <p>In some cases, this might no be used.</p>
         *
         * @return the loader
         */
        ClassLoader loader();

        /**
         * Defines the {@link ClassLoader} used to define new classes.
         *
         * <p>A lot of implementations will inject classes compiled at runtime in
         * the class loader. Some environments require to use specific class loaders.</p>
         *
         * <p>In some cases, this might no be used.</p>
         *
         * @param loader the loader
         * @return the configuration
         */
        T loader(ClassLoader loader);

        /**
         * Returns scout caching is enabled.
         *
         * @return whether caching is enabled
         */
        boolean cache();

        /**
         * Enables scout caching, accelerating the process of subscription/unsubscription.
         *
         * @param value the value
         * @return the configuration
         */
        T cache(boolean value);

        /**
         * Returns the scouts used by the bus.
         *
         * @return the scouts
         */
        Collection<ListenerScout> scouts();

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
        T scout(Collection<ListenerScout> scouts);

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
