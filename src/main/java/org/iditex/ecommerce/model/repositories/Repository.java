package org.iditex.ecommerce.model.repositories;

import java.util.List;

interface Repository<T> extends AutoCloseable {

    List<T> findAll();

}
