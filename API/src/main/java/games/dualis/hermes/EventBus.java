package games.dualis.hermes;

import games.dualis.hermes.api.Hermes;
import games.dualis.hermes.audience.EventAudience;
import games.dualis.hermes.audience.MutableEventAudience;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * An immutable object able to work with multiple event topics.
 *
 * <p>To achieve, each topic has an unique {@link EventAudience} implementation with
 * its own specifications.</p>
 *
 * @param <Audience> the audience type
 */
public interface EventBus<Audience extends EventAudience> {

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
    <T> EventBus.Configuration<T> configuration();

    /**
     * Returns the topics listed by this {@link EventBus}.
     *
     * <p>If a topic is contained by this collection, that means there's an audience
     * for it.</p>
     *
     * @return the topics
     */
    Set<Class<?>> topics();

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

    default void dispatch(Object event) {
        audience(event.getClass()).dispatch(event);
    }

    /**
     * An object used to configure the bus.
     *
     * @param <T> the configuration type
     */
    interface Configuration<T> {
        /**
         * Returns the id of this bus.
         *
         * @return the id
         */
        String id();

        /**
         * Defines the id of this bus.
         *
         * <p>The ID is only used when logging yet is required for the library to work.</p>
         *
         * @return the id.
         */
        T id(String id);
    }

    /**
     * A builder for {@link EventBus}.
     *
     * @see EventBus
     */
    interface Builder {
        List<EventAudience> audiences();
        Builder with(EventAudience audience);
        Builder with(Collection<EventAudience> audiences);
        default Builder with(EventAudience... audiences) {
            return with(List.of(audiences));
        }

        @SuppressWarnings("unchecked")
        default <Audience extends EventAudience> EventBus<Audience> immutable() {
            return (EventBus<Audience>) Hermes.factory()
                    .map(f -> f.immutable(this))
                    .orElseThrow(Hermes.NotInitializedException::new);
        }

        @SuppressWarnings("unchecked")
        default <Audience extends MutableEventAudience> MutableEventBus<Audience> mutable() {
            return (MutableEventBus<Audience>) Hermes.factory()
                    .map(f -> f.mutable(this))
                    .orElseThrow(Hermes.NotInitializedException::new);
        }
    }

}
