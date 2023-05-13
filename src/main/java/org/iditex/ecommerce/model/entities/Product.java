package org.iditex.ecommerce.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class Product {

    private Long id;

    @NonNull
    private Long sequence;

}
