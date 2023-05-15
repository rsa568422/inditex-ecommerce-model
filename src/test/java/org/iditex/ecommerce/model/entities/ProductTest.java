package org.iditex.ecommerce.model.entities;

import org.iditex.ecommerce.model.data.ProductData;
import org.iditex.ecommerce.model.data.SizeData;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    }
}