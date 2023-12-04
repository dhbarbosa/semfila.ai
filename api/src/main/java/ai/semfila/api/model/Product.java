package ai.semfila.api.model;

import ai.semfila.api.DTO.Product.ProductRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
@Document(collection = "Product")
public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID= 1L;

    @Id
    private String id;

    @Field( "name")
    private String name;

    @Field("price")
    private BigDecimal price;

    @Field(name = "company_id")
    private String company;

    @Field("orders_id")
    private String orders;

    @Field("type")
    private String type;

}
