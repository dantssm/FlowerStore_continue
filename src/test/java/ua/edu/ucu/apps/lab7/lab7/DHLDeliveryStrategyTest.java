package ua.edu.ucu.apps.lab7.lab7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.edu.ucu.apps.lab7.lab7.delivery.DHLDeliveryStrategy;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DHLDeliveryStrategyTest {

    private DHLDeliveryStrategy deliveryStrategy;

    @BeforeEach
    void setUp() {
        deliveryStrategy = new DHLDeliveryStrategy();
    }

    @Test
    void testSetAddressAndCollectDeliveryDetails() {
        deliveryStrategy.setAddress("123 Main St");
        assertDoesNotThrow(deliveryStrategy::collectDeliveryDetails);
    }

    @Test
    void testCollectDeliveryDetailsWithoutAddress() {
        Exception exception = assertThrows(IllegalStateException.class, deliveryStrategy::collectDeliveryDetails);
        assertEquals("Delivery address not set", exception.getMessage());
    }

    @Test
    void testDeliverWithAddress() {
        deliveryStrategy.setAddress("123 Main St");
        deliveryStrategy.deliver(List.of(new Item() {
            @Override
            public double price() { return 0; }
            @Override
            public String getDescription() { return "Test Item"; }
        }));
    }

    @Test
    void testDeliverWithoutAddress() {
        deliveryStrategy.deliver(List.of());
    }
}

