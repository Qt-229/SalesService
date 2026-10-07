/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import com.mycompany.salesservice.*;
/**
 *
 * @author choig
 */

public class SalesServiceTest {

    private SalesService service;

    @BeforeEach
    void setUp() {
        service = new SalesService();
    }

    @Test
    void testCalculateSubtotal_500x2() {
        Product product = new Product(
            "P01", "Product 1", 500, 2
        );

        assertEquals(
            1000,
            service.calculateSubtotal(product),
            0.001
        );
    }

    @Test
    void testCalculateSubtotal_100x10() {
        Product product = new Product(
            "P02", "Product 2", 100, 10
        );

        assertEquals(
            1000,
            service.calculateSubtotal(product),
            0.001
        );
    }

    @Test
    void testCalculateSubtotal_1000x5() {
        Product product = new Product(
            "P03", "Product 3", 1000, 5
        );

        assertEquals(
            5000,
            service.calculateSubtotal(product),
            0.001
        );
    }

    @ParameterizedTest
    @CsvSource({
        "999.99, 0",
        "1000, 50",
        "4999.99, 249.9995",
        "5000, 500",
        "9999.99, 999.999",
        "10000, 1500"
    })
    void testCalculateDiscount(double subtotal, double expected) {
        assertEquals(
            expected,
            service.calculateDiscount(subtotal),
            0.001
        );
    }

    @Test
    void testCalculateShippingFee_Below2000() {
        assertEquals(
            50,
            service.calculateShippingFee(1999.99),
            0.001
        );
    }

    @Test
    void testCalculateShippingFee_At2000() {
        assertEquals(
            0,
            service.calculateShippingFee(2000),
            0.001
        );
    }

    @Test
    void testCalculateShippingFee_Above2000() {
        assertEquals(
            0,
            service.calculateShippingFee(2000.01),
            0.001
        );
    }

    @Test
    void testCalculateTotal_FirstCase() {
        Product product = new Product(
            "P01", "Product 1", 500, 2
        );

        assertEquals(
            1000,
            service.calculateTotal(product),
            0.001
        );
    }

    @Test
    void testCalculateTotal_SecondCase() {
        Product product = new Product(
            "P02", "Product 2", 1000, 5
        );

        assertEquals(
            4500,
            service.calculateTotal(product),
            0.001
        );
    }

    @Test
    void testClassifyCustomer_Regular() {
        assertEquals(
            "REGULAR",
            service.classifyCustomer(999.99)
        );
    }

    @Test
    void testClassifyCustomer_Silver() {
        assertEquals(
            "SILVER",
            service.classifyCustomer(1000)
        );
    }

    @Test
    void testClassifyCustomer_Gold() {
        assertEquals(
            "GOLD",
            service.classifyCustomer(5000)
        );
    }

    @Test
    void testClassifyCustomer_Vip() {
        assertEquals(
            "VIP",
            service.classifyCustomer(10000)
        );
    }

    @Test
    void testCalculateSubtotal_NullProduct() {
        assertThrows(
            IllegalArgumentException.class,
            () -> service.calculateSubtotal(null)
        );
    }

    @Test
    void testCalculateDiscount_NegativeSubtotal() {
        assertThrows(
            IllegalArgumentException.class,
            () -> service.calculateDiscount(-1)
        );
    }
}