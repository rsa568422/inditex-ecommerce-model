package org.iditex.ecommerce.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Size {

    private Long id;

    private Long productId;

    private boolean backSoon;

    private boolean special;

    private Optional<Stock> stock;

    public Size(Long id, Long productId, boolean backSoon, boolean special) {
        this.id = id;
        this.productId = productId;
        this.backSoon = backSoon;
        this.special = special;
        this.stock = Optional.empty();
    }

    public Size(Long id, Long productId, boolean backSoon, boolean special, Stock stock) {
        this.id = id;
        this.productId = productId;
        this.backSoon = backSoon;
        this.special = special;
        this.stock = Optional.ofNullable(stock);
    }

    public boolean haveStock() {
        return backSoon || stock
                .map(Stock::getQuantity)
                .map(quantity -> quantity.compareTo(0L) > 0)
                .orElse(false);
    }

}
