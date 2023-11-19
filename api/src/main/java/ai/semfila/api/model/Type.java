package ai.semfila.api.model;


import ai.semfila.api.DTO.type.TypeRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TYPE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Type  implements Serializable {

    @Serial
    private static final long serialVersionUID= 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "type")
    private List<Product> product;

    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Type(TypeRequest typeRequest) {
        this.name = typeRequest.name();
        this.description = typeRequest.description();
        this.deleted = false;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.product = new ArrayList<>();
    }

    public void update(TypeRequest typeRequest){
        if(typeRequest.name() != null){
            this.name = typeRequest.name();
            this.updatedAt = LocalDateTime.now();
        }
        if(typeRequest.description() != null){
            this.description = typeRequest.description();
            this.updatedAt = LocalDateTime.now();
        }
    }
}
