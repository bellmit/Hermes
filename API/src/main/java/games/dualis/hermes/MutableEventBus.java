package games.dualis.hermes;

import games.dualis.hermes.audience.MutableEventAudience;
import games.dualis.hermes.listener.scout.ListenerScout;

import java.util.List;

/**
 * An extension of {@link EventBus} which allows mutation of the audiences.
 */
public interface MutableEventBus<Configuration, Audience extends MutableEventAudience> extends EventBus<Configuration, Audience> {

    /**
     * Removes the audience for given topic.
     *
     * @param topic the topic
     */
    void removeAudience(Class<?> topic);

    interface Configuration<T> extends EventBus.Configuration<T> {
        T cache(boolean value);
        T scout(ListenerScout scout);
        T scout(Iterable<? extends ListenerScout> scouts);
        default T scout(ListenerScout... scouts) {
            return scout(List.of(scouts));
        }
    }

}
