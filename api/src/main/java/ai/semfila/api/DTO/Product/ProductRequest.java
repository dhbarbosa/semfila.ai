package ai.semfila.api.DTO.Product;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRequest {
    @NotBlank String id;
    @NotBlank String name;
    @NotBlank BigDecimal price;
    LocalDateTime updateA;
}
