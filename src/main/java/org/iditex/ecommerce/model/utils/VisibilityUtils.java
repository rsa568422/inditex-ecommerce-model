package org.iditex.ecommerce.model.utils;

import org.iditex.ecommerce.model.entities.Product;
import org.iditex.ecommerce.model.entities.Size;
import org.iditex.ecommerce.model.entities.Stock;
import org.iditex.ecommerce.model.repositories.ProductRepository;
import org.iditex.ecommerce.model.repositories.SizeRepository;
import org.iditex.ecommerce.model.repositories.StockRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        return productRepository.findAll()
                .stream()
                .filter(this::isVisible)
                .sorted(Comparator.comparing(Product::getSequence))
                .collect(Collectors.toList());
    }

    private boolean isVisible(Product product) {
        return !sizeRepository.findByProductId(product.getId())
                .stream()
                .filter(this::haveStock)
                .map(Size::isSpecial)
                .reduce(true, Boolean::logicalAnd);
    }

    private boolean haveStock(Size size) {
        return size.isBackSoon() || stockRepository.findBySizeId(size.getId())
                .map(Stock::getQuantity)
                .map(quantity -> quantity.compareTo(0L) > 0)
                .orElse(false);
    }
}