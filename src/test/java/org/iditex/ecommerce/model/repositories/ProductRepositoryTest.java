package org.iditex.ecommerce.model.repositories;

import org.iditex.ecommerce.model.data.ProductData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @Spy
    ProductRepository repository;

    @Test
    void testFindVisiblesOrderBySequence() {
        when(repository.findAll()).thenReturn(ProductData.findAll());

        StringJoiner joiner = new StringJoiner(", ");
        repository.findVisiblesOrderBySequence()
                .forEach(product -> joiner.add(product.getId().toString()));

        verify(repository, times(1)).findVisiblesOrderBySequence();
        verify(repository, times(1)).findAll();
        assertEquals("5, 1, 3", joiner.toString());
    }
}