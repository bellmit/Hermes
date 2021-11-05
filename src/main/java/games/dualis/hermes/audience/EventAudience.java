package games.dualis.hermes.audience;

import games.dualis.hermes.Hermes;
import games.dualis.hermes.listener.Listener;

import java.util.Collection;
import java.util.List;

/**
 * An object holding an immutable collection of listeners and which provides an efficient way to send events to them.
 */
public interface EventAudience {

    /**
     * Returns a new {@link EventAudience.Builder} instance.
     *
     * @return the builder
     */
    static EventAudience.Builder audienceBuilder() {
        return Hermes.factory()
                .map(Hermes::audienceBuilder)
                .orElseThrow(Hermes.NotInitializedException::new);
    }

    /**
     * Returns the topic of this audience.
     *
     * @return the topic
     */
    Class<?> topic();

    /**
     * Returns the listeners contained by this audience.
     *
     * <p>Note that the only optimized operation for audiences is dispatching, this might
     * cause decreased performance when this method is called.</p>
     *
     * <p>The given collection is immutable even if the audience is qualified as {@code mutable}.</p>
     *
     * @return the collection of listeners
     */
    Collection<Listener> listeners();

    /**
     * Dispatches given event to each of its listeners in this {@link EventAudience}
     * if there are any.
     *
     * <p>This process is done synchronously and we do not guarantee {@code concurrency}.
     * Each implementation is unique and might or might not handle some functions.</p>
     *
     * @param event the event
     */
    void dispatch(Object event);

    /**
     * A builder for {@link EventAudience}.
     *
     * @see EventAudience
     */
    interface Builder {
        Builder topic(Class<?> topic);
        Builder with(Listener listener);
        Builder with(Iterable<? extends Listener> listeners);
        default Builder with(Listener... listeners) {
            return with(List.of(listeners));
        }
        default EventAudience build() {
            return Hermes.factory()
                    .map(f -> f.immutable(this))
                    .orElseThrow(Hermes.NotInitializedException::new);
        }
    }

}
