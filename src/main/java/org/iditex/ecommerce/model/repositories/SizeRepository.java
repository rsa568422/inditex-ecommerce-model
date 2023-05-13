package org.iditex.ecommerce.model.repositories;

import org.iditex.ecommerce.model.entities.Size;

import java.util.List;

public interface SizeRepository extends Repository<Size> {

    List<Size> findByProductId(Long productId);

}
