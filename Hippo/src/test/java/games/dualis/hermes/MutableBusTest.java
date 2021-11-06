package games.dualis.hermes;

import games.dualis.hermes.audience.MutableEventAudience;
import games.dualis.hermes.listener.Listen;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MutableBusTest {

    private static MutableEventBus<MutableEventAudience> bus;

    @BeforeAll
    @Test
    static void busCreation() {
        HippoHermes.setup();

        bus = EventBus.builder()
                .mutable();

        assertNotNull(bus);
    }

    @Test
    public void busSubscription() {
        bus.subscribe(new Listeners());

        assertEquals(bus.topics().size(), 2);
    }

    @Test
    public void busDispatch() {
        final var audience = bus.audience(String.class);

        assertEquals(audience.listeners().size(), 1);

        audience.dispatch("Hello world");
        assertEquals(Listeners.text, "Hello world");
    }

    @Test
    public void busBenchmark() {
        for(int i = 0; i < 100_000_000; i++) {
            bus.dispatch(2);
        }

        for(int x = 0; x < 20; x++) {
            final var start = System.nanoTime();
            for(int i = 0; i < 3_000_000; i++) {
                bus.dispatch(2);
            }
            long finish = System.nanoTime();
            System.out.println("Published 3 millions message in " + (finish - start) * 1.0e-6 + "ms");
        }
        assertTrue(true);
    }

    public static class Listeners {
        static String text;

        @Listen
        public void onReceive(String text) {
            System.out.println("Received: " + text);
            Listeners.text = text;
        }

        @Listen
        public void onReceive(Integer number) {}
    }

}
