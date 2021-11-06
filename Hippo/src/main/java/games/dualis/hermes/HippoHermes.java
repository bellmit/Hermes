package games.dualis.hermes;

import games.dualis.hermes.api.Hermes;
import games.dualis.hermes.audience.EventAudience;
import games.dualis.hermes.audience.HippoEventAudienceBuilder;
import games.dualis.hermes.audience.MutableEventAudience;
import games.dualis.hermes.audience.immutable.HippoEventAudience;
import games.dualis.hermes.audience.mutable.HippoMutableEventAudience;
import games.dualis.hermes.listener.Listener;

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

    @Override
    public EventBus.Builder busBuilder() {
        return null;
    }

    @Override
    public <Configuration, Audience extends EventAudience> EventBus<Configuration, Audience> immutable(EventBus.Builder builder) {
        return null;
    }

    @Override
    public <Configuration, Audience extends MutableEventAudience> MutableEventBus<Configuration, Audience> mutable(EventBus.Builder builder) {
        return null;
    }
}
