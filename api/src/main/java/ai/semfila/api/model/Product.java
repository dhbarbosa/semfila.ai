package ai.semfila.api.model;

import ai.semfila.api.DTO.product.ProductRequest;
import ai.semfila.api.DTO.product.ProductUpdate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="PRODUCT")
public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID= 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "product")
    private List<Orders> orders;

    @Column( name="type", nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Product(ProductRequest productRequest){
        this.name = productRequest.name();
        this.price = productRequest.price();
        this.description = productRequest.description();
        this.company = productRequest.company();
        this.type= productRequest.type();
        this.orders = new ArrayList<>();
        this.deleted = false;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void update(ProductUpdate productUpdate) {
        if(productUpdate.name()!= null){
            this.setName(productUpdate.name());
        }
        if(productUpdate.description() != null){
            this.setDescription(productUpdate.description());
        }
        if(productUpdate.price() != null){
            this.setPrice(productUpdate.price());
        }
        if(productUpdate.type() != null){
            this.setType(productUpdate.type());
        }
    }
}
