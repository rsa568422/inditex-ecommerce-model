package org.iditex.ecommerce.model.entities;

import org.iditex.ecommerce.model.data.ProductData;
import org.iditex.ecommerce.model.data.SizeData;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductTest {

    @Nested
    @Tag("UNIT")
    class UnitTests {

        @InjectMocks
        private Product product;

        @Mock
        private Set<Size> sizes;

        @ParameterizedTest(name = "testIsVisible for id {argumentsWithNames}")
        @ValueSource(strings = {"1", "3", "5"})
        void testIsVisible(String param) {
            Long id = Long.valueOf(param);

            when(sizes.stream()).thenReturn(SizeData.findByProductId(id).stream());

            assertTrue(product.isVisible());
        }

        @ParameterizedTest(name = "testIsNotVisible for id {argumentsWithNames}")
        @ValueSource(strings = {"2", "4"})
        void testIsNotVisible(String param) {
            Long id = Long.valueOf(param);

            when(sizes.stream()).thenReturn(SizeData.findByProductId(id).stream());

            assertFalse(product.isVisible());
        }

        @Test
        void testConstructor() {
            Product a = new Product(1L, 1L, sizes);
            Product b = new Product();
            b.setId(1L);
            b.setSequence(1L);
            b.setSizes(sizes);

            assertAll(
                    () -> assertEquals(a, b),
                    () -> assertEquals(b, a)
            );
        }
    }

    @Nested
    @Tag("INTEGRATION")
    class IntegrationTests {

        @ParameterizedTest(name = "testIsVisible for id {argumentsWithNames}")
        @ValueSource(strings = {"1", "3", "5"})
        void testIsVisible(String param) {
            Long id = Long.valueOf(param);

            assertTrue(ProductData.PRODUCTS.get(id).isVisible());
        }

        @ParameterizedTest(name = "testIsNotVisible for id {argumentsWithNames}")
        @ValueSource(strings = {"2", "4"})
        void testIsNotVisible(String param) {
            Long id = Long.valueOf(param);

            assertFalse(ProductData.PRODUCTS.get(id).isVisible());
        }

        @Test
        void testConstructor() {
            Set<Size> aSizes = Stream.of(
                    new Size(11L, 1L, true, false)
            ).collect(Collectors.toSet());
            Set<Size> bSizes = Stream.of(
                    new Size(11L, 1L, true, false)
            ).collect(Collectors.toSet());

            Product a = new Product(1L, 1L, aSizes);
            Product b = new Product();
            b.setId(1L);
            b.setSequence(1L);
            b.setSizes(bSizes);

            assertAll(
                    () -> assertEquals(a, b),
                    () -> assertEquals(b, a)
            );
        }
    }
}