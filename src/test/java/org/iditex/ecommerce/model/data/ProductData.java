package org.iditex.ecommerce.model.data;

import org.iditex.ecommerce.model.entities.Product;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductData {

    public static final Map<Long, Product> PRODUCTS = Stream.of(
            new Product(1L, 10L, SizeData.findByProductId(1L)),
            new Product(2L, 7L, SizeData.findByProductId(2L)),
            new Product(3L, 15L, SizeData.findByProductId(3L)),
            new Product(4L, 13L, SizeData.findByProductId(4L)),
            new Product(5L, 6L, SizeData.findByProductId(5L))
    ).collect(Collectors.groupingBy(Product::getId, Collectors.collectingAndThen(Collectors.toList(), list -> list.get(0))));

    public static Set<Product> findAll() {
        return new HashSet<>(PRODUCTS.values());
    }

}
