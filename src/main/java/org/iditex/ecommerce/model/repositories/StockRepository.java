package org.iditex.ecommerce.model.repositories;

import org.iditex.ecommerce.model.entities.Stock;

import java.util.Optional;

public interface StockRepository extends Repository<Stock> {

    Optional<Stock> findBySizeId(Long sizeId);

}
