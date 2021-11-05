package games.dualis.hermes.listener;

/**
 * An object able to receive and perform action on a given event.
 */
public interface Listener {

    /**
     * Returns the topic.
     *
     * @return the topic
     */
    Class<?> topic();

    /**
     * Returns the priority.
     *
     * <p>Modifying the priority will change the order of receiving. Higher mean receiving faster.</p>
     *
     * @return the priority
     */
    int priority();

    /**
     * Receives given event and treats it.
     *
     * @param event the event
     */
    void receive(Object event);

}
