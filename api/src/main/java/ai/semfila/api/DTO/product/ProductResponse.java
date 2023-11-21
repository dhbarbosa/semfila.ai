package ai.semfila.api.DTO.product;

import ai.semfila.api.model.Company;
import ai.semfila.api.model.Product;
import ai.semfila.api.model.Type;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProductResponse(
        UUID id,
        String name,
        String description,
        BigDecimal price,

        Company company,

        Type type,

        LocalDateTime createAt

) {

    public ProductResponse(Product product){
        this(product.getId(),product.getName(), product.getDescription(), product.getPrice(), product.getCompany(), product.getType(), product
                .getCreatedAt());
    }
}
