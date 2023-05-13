package org.iditex.ecommerce.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class Size {

    private Long id;

    @NonNull
    private Long productId;

    private boolean backSoon;

    private boolean special;

}
