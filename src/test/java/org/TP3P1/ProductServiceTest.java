package org.TP3P1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {
    @Mock
    private ProductApiClient apiClient;

    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductService(apiClient);
    }

    @Test
    void testGetProduct_success() throws Exception {
        // Arrange
        String productId = "G513RC";
        Product expectedProduct = new Product(productId, "ASUS ROG STRIX");
        when(apiClient.getProduct(productId)).thenReturn(expectedProduct);

        // Act
        Product result = productService.getProduct(productId);

        // Assert
        assertNotNull(result);
        assertEquals("ASUS ROG STRIX", result.getName());
        verify(apiClient, times(1)).getProduct(productId);
    }

    @Test
    void testGetProduct_apiThrowsException() {
        // Arrange
        String productId = "P404";
        try {
            when(apiClient.getProduct(productId)).thenThrow(new RuntimeException("API error"));

            // Act + Assert
            assertThrows(RuntimeException.class, () -> productService.getProduct(productId));
            verify(apiClient).getProduct(productId);
        } catch (Exception e) {
            fail("Exception should not be thrown from mock setup");
        }
    }

    @Test
    void testGetProduct_returnsNull() throws Exception {
        // Arrange
        String productId = "nullProduct";
        when(apiClient.getProduct(productId)).thenReturn(null);

        // Act
        Product result = productService.getProduct(productId);

        // Assert
        assertNull(result);
        verify(apiClient).getProduct(productId);
    }


}