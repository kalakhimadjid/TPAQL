package org.TP3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @Mock
    private OrderDao orderDao;

    private OrderController orderController;
    private OrderService realOrderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // on utilise un vrai OrderService avec un mock d'OrderDao
        realOrderService = new OrderService(orderDao);
        orderController = new OrderController(realOrderService);
    }

    @Test
    void testCreateOrder_withMocks() {
        // Arrange
        Order order = new Order(2001L, "ROG STRIX");

        // Act
        orderController.createOrder(order);

        // Assert
        // Vérifie que OrderDao.saveOrder est bien appelé avec l'objet order
        verify(orderDao, times(1)).saveOrder(order);
    }
}
