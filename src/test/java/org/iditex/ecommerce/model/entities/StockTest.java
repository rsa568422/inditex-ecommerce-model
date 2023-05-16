package org.iditex.ecommerce.model.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StockTest {

    @Test
    void testConstructor() {
        Stock a = new Stock(11L, 0L);
        Stock b = new Stock();
        b.setSizeId(11L);
        b.setQuantity(0L);

        assertAll(
                () -> assertEquals(a, b),
                () -> assertEquals(b, a)
        );
    }

}