package games.dualis.hermes;

import games.dualis.hermes.audience.EventAudience;
import games.dualis.hermes.audience.MutableEventAudience;

import java.util.List;

/**
 * An immutable object able to work with multiple event topics.
 *
 * <p>To achieve, each topic has an unique {@link EventAudience} implementation with
 * its own specifications.</p>
 *
 * @param <Audience> the audience type
 */
public interface EventBus<Configuration, Audience extends EventAudience> {

    /**
     * Returns a new {@link Builder} instance.
     *
     * @param <Configuration> the configuration type
     * @param <Audience> the audience type
     * @return the builder
     */
    static Builder builder() {
        return Hermes.factory()
                .map(Hermes::busBuilder)
                .orElseThrow(Hermes.NotInitializedException::new);
    }

    /**
     * Returns the configuration of this {@link EventBus}.
     *
     * @return the configuration
     */
    EventBus.Configuration<Configuration> configuration();

    /**
     * Returns the audience for a given topic.
     *
     * <p>The returned value cannot be null, if the topic has no listener, an empty implementation
     * of {@link EventAudience} will be returned.</p>
     *
     * @param topic the topic
     * @return the audience
     */
    Audience audience(Class<?> topic);

    /**
     * An object used to configure the bus.
     *
     * @param <T> the configuration type
     */
    interface Configuration<T> {
        /**
         * Defines the id of this bus.
         *
         * <p>The ID is only used when logging yet is required for the library to work.</p>
         *
         * @return the id.
         */
        T id(String id);

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
    }

    /**
     * A builder for {@link EventBus}.
     *
     * @see EventBus
     */
    interface Builder {
        <T> Builder configuration(EventBus.Configuration<T> configuration);
        Builder with(EventAudience audience);
        Builder with(Iterable<? extends EventAudience> audiences);
        default Builder with(EventAudience... audiences) {
            return with(List.of(audiences));
        }

        @SuppressWarnings("unchecked")
        default <Configuration, Audience extends EventAudience> EventBus<Configuration, Audience> immutable() {
            return (EventBus<Configuration, Audience>) Hermes.factory()
                    .map(f -> f.immutable(this))
                    .orElseThrow(Hermes.NotInitializedException::new);
        }

        @SuppressWarnings("unchecked")
        default <Configuration, Audience extends MutableEventAudience> MutableEventBus<Configuration, Audience> mutable() {
            return (MutableEventBus<Configuration, Audience>) Hermes.factory()
                    .map(f -> f.mutable(this))
                    .orElseThrow(Hermes.NotInitializedException::new);
        }
    }

}
