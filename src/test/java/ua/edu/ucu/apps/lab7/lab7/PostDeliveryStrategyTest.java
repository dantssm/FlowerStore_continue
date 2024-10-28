package ua.edu.ucu.apps.lab7.lab7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.edu.ucu.apps.lab7.lab7.delivery.PostDeliveryStrategy;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostDeliveryStrategyTest {

    private PostDeliveryStrategy deliveryStrategy;

    @BeforeEach
    void setUp() {
        deliveryStrategy = new PostDeliveryStrategy();
    }

    @Test
    void testSetPostOfficeAndCollectDeliveryDetails() {
        deliveryStrategy.setPostOffice("Post Office 101");
        assertDoesNotThrow(deliveryStrategy::collectDeliveryDetails);
    }

    @Test
    void testCollectDeliveryDetailsWithoutPostOffice() {
        Exception exception = assertThrows(IllegalStateException.class, deliveryStrategy::collectDeliveryDetails);
        assertEquals("Post office address not set", exception.getMessage());
    }

    @Test
    void testDeliverWithPostOffice() {
        deliveryStrategy.setPostOffice("Post Office 101");
        deliveryStrategy.deliver(List.of(new Item() {
            @Override
            public double price() { return 0; }
            @Override
            public String getDescription() { return "Test Item"; }
        }));
    }

    @Test
    void testDeliverWithoutPostOffice() {
        deliveryStrategy.deliver(List.of());
    }
}
