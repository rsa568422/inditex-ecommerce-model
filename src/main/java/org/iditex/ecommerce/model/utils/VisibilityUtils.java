package org.iditex.ecommerce.model.utils;

import org.iditex.ecommerce.model.entities.Product;
import org.iditex.ecommerce.model.entities.Size;
import org.iditex.ecommerce.model.repositories.ProductRepository;
import org.iditex.ecommerce.model.repositories.SizeRepository;
import org.iditex.ecommerce.model.repositories.StockRepository;

import java.util.Collections;
import java.util.List;

public final class VisibilityUtils {

    private final ProductRepository productRepository;

    private final SizeRepository sizeRepository;

    private final StockRepository stockRepository;

    public VisibilityUtils(ProductRepository productRepository,
                           SizeRepository sizeRepository,
                           StockRepository stockRepository) {
        this.productRepository = productRepository;
        this.sizeRepository = sizeRepository;
        this.stockRepository = stockRepository;
    }

    public List<Product> getVisibleProductsSortedBySequence() {
        return Collections.emptyList();
    }

    private boolean isVisible(Product product) {
        return false;
    }

    private boolean haveStock(Size size) {
        return false;
    }
}
