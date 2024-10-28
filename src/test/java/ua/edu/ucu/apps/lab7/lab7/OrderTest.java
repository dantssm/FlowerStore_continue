package ua.edu.ucu.apps.lab7.lab7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.edu.ucu.apps.lab7.lab7.delivery.DHLDeliveryStrategy;
import ua.edu.ucu.apps.lab7.lab7.delivery.PostDeliveryStrategy;
import ua.edu.ucu.apps.lab7.lab7.flower.Flower;
import ua.edu.ucu.apps.lab7.lab7.flower.FlowerBucket;
import ua.edu.ucu.apps.lab7.lab7.flower.FlowerColor;
import ua.edu.ucu.apps.lab7.lab7.flower.FlowerPack;
import ua.edu.ucu.apps.lab7.lab7.flower.FlowerType;
import ua.edu.ucu.apps.lab7.lab7.payment.CreditCardPaymentStrategy;
import ua.edu.ucu.apps.lab7.lab7.payment.PayPalPaymentStrategy;
import ua.edu.ucu.apps.lab7.lab7.decorators.RibbonDecorator;
import ua.edu.ucu.apps.lab7.lab7.decorators.PaperDecorator;
import ua.edu.ucu.apps.lab7.lab7.decorators.BasketDecorator;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    private Order order;
    private FlowerBucket flowerBucket;

    @BeforeEach
    void setUp() {
        order = new Order();

        Flower flower = new Flower(FlowerColor.YELLOW, FlowerType.CACTUS, 15.0, 10.0);
        flower.setPrice(10.0);

        FlowerPack flowerPack = new FlowerPack(flower, 5);
        flowerBucket = new FlowerBucket();
        flowerBucket.addFlowerPack(flowerPack);
    }

    @Test
    void testAddItemToOrder() {
        order.addItem(flowerBucket);
        assertEquals(1, order.getItems().size());
    }

    @Test
    void testCalculateTotalPrice() {
        order.addItem(flowerBucket);
        assertEquals(50.0, order.calculateTotalPrice());
    }

    @Test
    void testCreditCardPaymentSuccess() {
        CreditCardPaymentStrategy creditCardPayment = new CreditCardPaymentStrategy();
        creditCardPayment.setCreditCardDetails("1234-5678-8765-4321", "12/23", "123");
        order.setPaymentStrategy(creditCardPayment);
        order.addItem(flowerBucket);

        assertDoesNotThrow(() -> order.processOrder(creditCardPayment));
    }

    @Test
    void testCreditCardPaymentInsufficientFunds() {
        CreditCardPaymentStrategy creditCardPayment = new CreditCardPaymentStrategy();
        creditCardPayment.setCreditCardDetails("1234-5678-8765-4321", "12/23", "123");
        creditCardPayment.card.setAmount(10.0);
        order.setPaymentStrategy(creditCardPayment);
        order.addItem(flowerBucket);

        assertFalse(creditCardPayment.pay(order.calculateTotalPrice()));
    }

    @Test
    void testPayPalPaymentSuccess() {
        PayPalPaymentStrategy paypalPayment = new PayPalPaymentStrategy();
        paypalPayment.setCredentials("amanda@ya.com", "amanda1985");
        order.setPaymentStrategy(paypalPayment);
        order.addItem(flowerBucket);

        paypalPayment.collectPaymentDetails();
        assertTrue(paypalPayment.pay(order.calculateTotalPrice()));
    }

    @Test
    void testPayPalPaymentInvalidCredentials() {
        PayPalPaymentStrategy paypalPayment = new PayPalPaymentStrategy();
        paypalPayment.setCredentials("wrong@ya.com", "wrongpassword");

        Exception exception = assertThrows(IllegalStateException.class, paypalPayment::collectPaymentDetails);
        assertEquals("Invalid email or password", exception.getMessage());
    }

    @Test
    void testDHLDeliverySuccess() {
        DHLDeliveryStrategy dhlDelivery = new DHLDeliveryStrategy();
        dhlDelivery.setAddress("123 Street, City");
        order.setDeliveryStrategy(dhlDelivery);
        order.addItem(flowerBucket);

        dhlDelivery.collectDeliveryDetails();
        assertDoesNotThrow(() -> dhlDelivery.deliver(order.getItems()));
    }

    @Test
    void testDHLDeliveryNoAddress() {
        DHLDeliveryStrategy dhlDelivery = new DHLDeliveryStrategy();

        Exception exception = assertThrows(IllegalStateException.class, dhlDelivery::collectDeliveryDetails);
        assertEquals("Delivery address not set", exception.getMessage());
    }

    @Test
    void testPostDeliverySuccess() {
        PostDeliveryStrategy postDelivery = new PostDeliveryStrategy();
        postDelivery.setPostOffice("Main Post Office");
        order.setDeliveryStrategy(postDelivery);
        order.addItem(flowerBucket);

        postDelivery.collectDeliveryDetails();
        assertDoesNotThrow(() -> postDelivery.deliver(order.getItems()));
    }

    @Test
    void testPostDeliveryNoPostOffice() {
        PostDeliveryStrategy postDelivery = new PostDeliveryStrategy();

        Exception exception = assertThrows(IllegalStateException.class, postDelivery::collectDeliveryDetails);
        assertEquals("Post office address not set", exception.getMessage());
    }

    @Test
    void testRibbonDecorator() {
        Item decoratedBucket = new RibbonDecorator(flowerBucket);
        assertEquals("A bucket containing 5 YELLOW CACTUS wrapped in Ribbon", decoratedBucket.getDescription());
        assertEquals(40 + flowerBucket.price(), decoratedBucket.price());
    }

    @Test
    void testPaperDecorator() {
        Item decoratedBucket = new PaperDecorator(flowerBucket);
        assertEquals("A bucket containing 5 YELLOW CACTUS wrapped in Paper", decoratedBucket.getDescription());
        assertEquals(13 + flowerBucket.price(), decoratedBucket.price());
    }

    @Test
    void testBasketDecorator() {
        Item decoratedBucket = new BasketDecorator(flowerBucket);
        assertEquals("A bucket containing 5 YELLOW CACTUS wrapped in Basket", decoratedBucket.getDescription());
        assertEquals(4 + flowerBucket.price(), decoratedBucket.price());
    }
}
