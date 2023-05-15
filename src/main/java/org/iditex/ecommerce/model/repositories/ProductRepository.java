package org.iditex.ecommerce.model.repositories;

import org.iditex.ecommerce.model.entities.Product;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public interface ProductRepository extends Repository<Product> {

    Set<Product> findAll();

    default Set<Product> findVisiblesOrderBySequence() {
        return filterVisiblesAndOrderBySequence(findAll());
    }

    static Set<Product> filterVisiblesAndOrderBySequence(Set<Product> products) {
        return products.stream().filter(Product::isVisible).sorted().collect(Collectors.toCollection(LinkedHashSet::new));
    }

}
