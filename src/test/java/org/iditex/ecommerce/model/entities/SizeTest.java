package org.iditex.ecommerce.model.entities;

import org.iditex.ecommerce.model.data.SizeData;
import org.iditex.ecommerce.model.data.StockData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SizeTest {

    @Nested
    @Tag("UNIT")
    class UnitTests {

        @InjectMocks
        private Size size;

        @Mock
        private Stock stock;

        @BeforeEach
        void setUp() {
            size.setStock(Optional.of(stock));
        }

        @Test
        void testHaveStock11() {
            size.setBackSoon(true);
            size.setSpecial(false);

            boolean actual = size.haveStock();

            verify(stock, times(0)).getQuantity();
            assertTrue(actual);
        }

        @Test
        void testHaveStock12() {
            size.setBackSoon(false);
            size.setSpecial(false);

            when(stock.getQuantity()).thenReturn(StockData.STOCKS.get(12L).getQuantity());

            boolean actual = size.haveStock();

            verify(stock, times(1)).getQuantity();
            assertFalse(actual);
        }

        @Test
        void testHaveStock13() {
            size.setBackSoon(true);
            size.setSpecial(false);

            boolean actual = size.haveStock();

            verify(stock, times(0)).getQuantity();
            assertTrue(actual);
        }

        @Test
        void testHaveStock22() {
            size.setBackSoon(false);
            size.setSpecial(false);

            when(stock.getQuantity()).thenReturn(StockData.STOCKS.get(22L).getQuantity());

            boolean actual = size.haveStock();

            verify(stock, times(1)).getQuantity();
            assertFalse(actual);
        }

        @Test
        void testHaveStock33() {
            size.setBackSoon(false);
            size.setSpecial(false);

            when(stock.getQuantity()).thenReturn(StockData.STOCKS.get(33L).getQuantity());

            boolean actual = size.haveStock();

            verify(stock, times(1)).getQuantity();
            assertTrue(actual);
        }
    }

    @Nested
    @Tag("INTEGRATION")
    class IntegrationTests {

        @ParameterizedTest(name = "testHaveStock for id {argumentsWithNames}")
        @ValueSource(strings = {"11", "13", "33"})
        void testHaveStock(String param) {
            Long id = Long.valueOf(param);

            assertTrue(SizeData.SIZES.get(id).haveStock());
        }

        @ParameterizedTest(name = "testHaveNotStock for id {argumentsWithNames}")
        @ValueSource(strings = {"12", "22"})
        void testHaveNotStock(String param) {
            Long id = Long.valueOf(param);

            assertFalse(SizeData.SIZES.get(id).haveStock());
        }

    }

}