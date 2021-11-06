package games.dualis.hermes;

import games.dualis.hermes.api.Hermes;
import games.dualis.hermes.audience.EventAudience;
import games.dualis.hermes.audience.HippoEventAudienceBuilder;
import games.dualis.hermes.audience.MutableEventAudience;
import games.dualis.hermes.audience.immutable.HippoEventAudience;
import games.dualis.hermes.audience.mutable.HippoMutableEventAudience;
import games.dualis.hermes.listener.Listener;

import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class HippoHermes implements Hermes {

    private static final HippoHermes SINGLETON = new HippoHermes();

    /**
     * Defines {@link HippoHermes} as the factory and returns a new bus builder.
     *
     * @return the hippo hermes builder
     */
    public static EventBus.Builder setup() {
        return Hermes.factory(SINGLETON)
                .busBuilder();
    }

    /**
     * {@inheritDoc}
     *
     * @return A {@link HippoEventAudienceBuilder}
     */
    @Override
    public EventAudience.Builder audienceBuilder() {
        return new HippoEventAudienceBuilder();
    }

    /**
     * {@inheritDoc}
     *
     * @return A {@link HippoEventAudience}
     */
    @Override
    public EventAudience immutable(EventAudience.Builder builder) {
        return new HippoEventAudience(
                builder.topic(),
                builder.listeners().toArray(Listener[]::new)
        );
    }

    /**
     * {@inheritDoc}
     *
     * @return A {@link HippoMutableEventAudience}
     */
    @Override
    public MutableEventAudience mutable(EventAudience.Builder builder) {
        return new HippoMutableEventAudience(
                builder.topic(),
                builder.listeners().toArray(Listener[]::new)
        );
    }

    /**
     * {@inheritDoc}
     *
     * @return A {@link HippoBusBuilder}
     */
    @Override
    public EventBus.Builder busBuilder() {
        return new HippoBusBuilder();
    }

    /**
     * {@inheritDoc}
     *
     * @return A {@link EventBus}
     */
    @Override
    public <Configuration, Audience extends EventAudience> EventBus<Configuration, Audience> immutable(EventBus.Builder builder) {
        return (EventBus<Configuration, Audience>) new HippoEventBus(
                builder.audiences().stream()
                        .collect(Collectors.toMap(EventAudience::topic, v -> v))
        );
    }

    /**
     * {@inheritDoc}
     *
     * @return A {@link HippoMutableEventBus}
     */
    @Override
    public <Configuration, Audience extends MutableEventAudience> MutableEventBus<Configuration, Audience> mutable(EventBus.Builder builder) {
        return (MutableEventBus<Configuration, Audience>) new HippoMutableEventBus(
                builder.audiences().stream()
                        .collect(Collectors.toMap(EventAudience::topic, v -> (MutableEventAudience)v))
        );
    }
}
