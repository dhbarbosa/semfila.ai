package ai.semfila.api.DTO.product;

import ai.semfila.api.model.Type;

import java.math.BigDecimal;

public record ProductUpdate(
        String name,
        String description,
        BigDecimal price,
        Type type
) {

}

