package org.iditex.ecommerce.model.entities;

import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Comparable<Product> {

    private Long id;

    private Long sequence;

    private Set<Size> sizes;

    public boolean isVisible() {
        return !sizes
                .stream()
                .filter(Size::haveStock)
                .map(Size::isSpecial)
                .reduce(true, Boolean::logicalAnd);
    }

    @Override
    public int compareTo(Product product) {
        return sequence.compareTo(product.getSequence());
    }
}
