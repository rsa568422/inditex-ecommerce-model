package org.iditex.ecommerce.model.entities;

import lombok.Data;
import lombok.NonNull;

@Data
public class Stock {

    @NonNull
    private Long sizeId;

    @NonNull
    private Long quantity;

}
