package org.iditex.ecommerce.model.repositories;

import org.iditex.ecommerce.model.entities.Size;

import java.util.Set;

public interface SizeRepository extends Repository<Size> {

    Set<Size> findByProductId(Long productId);

}
