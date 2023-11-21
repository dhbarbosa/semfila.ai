package ai.semfila.api.DTO.product;

import ai.semfila.api.model.Company;
import ai.semfila.api.model.Type;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductRequest(
        @NotBlank
        String name,
        String description,
        @NotNull
        BigDecimal price,
        @NotBlank
        Company company,
        @NotBlank
        Type type
        ) {

}
