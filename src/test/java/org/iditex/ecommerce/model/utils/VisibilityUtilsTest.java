package org.iditex.ecommerce.model.utils;

import org.iditex.ecommerce.model.entities.Product;
import org.iditex.ecommerce.model.repositories.ProductRepository;
import org.iditex.ecommerce.model.repositories.SizeRepository;
import org.iditex.ecommerce.model.repositories.StockRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.iditex.ecommerce.model.utils.Data.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisibilityUtilsTest {

    @InjectMocks
    private VisibilityUtils visibilityUtils;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private SizeRepository sizeRepository;

    @Mock
    private StockRepository stockRepository;

    @Test
    void getVisibleProductsSortedBySequence() {
        List<Long> productIdsToSearch = Arrays.asList(1L, 2L, 3L, 4L, 5L);
        List<Long> sizeIdsToSearch = Arrays.asList(12L, 21L, 22L, 33L, 41L, 42L, 43L, 52L, 53L);

        when(productRepository.findAll()).thenReturn(PRODUCTS);
        productIdsToSearch.forEach(id -> when(sizeRepository.findByProductId(argThat(arg -> Objects.equals(arg, id)))).thenReturn(SIZES.get(id)));
        sizeIdsToSearch.forEach(id -> when(stockRepository.findBySizeId(argThat(arg -> Objects.equals(arg, id)))).thenReturn(Optional.ofNullable(STOCKS.get(id))));

        StringJoiner actual = new StringJoiner(", ");
        visibilityUtils.getVisibleProductsSortedBySequence().stream().map(Product::getId).map(String::valueOf).forEach(actual::add);

        assertAll(
                () -> assertEquals("5, 1, 3", actual.toString()),
                () -> verify(sizeRepository, times(productIdsToSearch.size())).findByProductId(argThat(productIdsToSearch::contains)),
                () -> verify(stockRepository, times(sizeIdsToSearch.size())).findBySizeId(argThat(sizeIdsToSearch::contains))
        );
    }
}