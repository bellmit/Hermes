package games.dualis.hermes.api;

import games.dualis.hermes.EventBus;
import games.dualis.hermes.MutableEventBus;
import games.dualis.hermes.audience.EventAudience;
import games.dualis.hermes.audience.MutableEventAudience;

import java.util.Optional;

/**
 * An object used as a factory for the library.
 *
 * <p>Any implementation will need initialize the library by modifying the value of
 * {@link Hermes#factory}.</p>
 */
public interface Hermes {

    static Hermes factory(Hermes factory) {
        HermesLoader.factory = factory;
        return factory;
    }

    /**
     * Returns the {@link Hermes} wrapped in an optional.
     *
     * @return the hermes factory
     */
    static Optional<Hermes> factory() {
        return Optional.ofNullable(HermesLoader.factory);
    }

    /**
     * Returns a new {@link EventAudience.Builder} instance.
     *
     * @return the builder
     */
    EventAudience.Builder audienceBuilder();

    /**
     * Builds a {@link EventAudience} from given builder.
     *
     * @param builder the builder
     * @return the audience
     */
    EventAudience immutable(EventAudience.Builder builder);

    /**
     * Builds a {@link MutableEventAudience} from given builder.
     *
     * @param builder the builder
     * @return the audience
     */
    MutableEventAudience mutable(EventAudience.Builder builder);

    /**
     * Returns a new {@link EventBus.Builder} instance.
     *
     * @return the builder
     */
    EventBus.Builder busBuilder();

    /**
     * Builds a {@link EventBus} from given builder.
     *
     * @param builder the builder
     * @return the audience
     */
    <Audience extends EventAudience> EventBus<Audience> immutable(EventBus.Builder builder);


    /**
     * Builds a {@link EventBus} from given builder.
     *
     * @param builder the builder
     * @return the audience
     */
    <Configuration, Audience extends MutableEventAudience> MutableEventBus<Audience> mutable(EventBus.Builder builder);

    /**
     * An exception thrown when {@link Hermes#factory} is not initialized.
     */
    class NotInitializedException extends RuntimeException {
        public NotInitializedException() {
            super("Hermes' factory has not been initialized.");
        }
    }

}
