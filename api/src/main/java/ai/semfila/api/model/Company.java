package ai.semfila.api.model;

import ai.semfila.api.DTO.company.CompanyRequest;
import ai.semfila.api.DTO.company.CompanyUpdateRequest;
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
@Table(name = "COMPANY")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company implements Serializable {
    @Serial
    private static final long serialVersionUID= 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "cnpj", nullable = false, unique = true)
    private String cnpj;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone", nullable = false, length = 11)
    private String phone;

    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "company")
    private List<Product> products;

    public Company(CompanyRequest companyRequest) {
        this.name = companyRequest.name();
        this.cnpj = companyRequest.cnpj().replace(".","").replace("-","").replace("/", "");
        this.address = companyRequest.address();
        this.phone = companyRequest.phone();
        this.deleted = false;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.products = new ArrayList<>();
    }

    public void update(CompanyUpdateRequest companyUpdateRequest) {
        if(companyUpdateRequest.name() != null){
            this.name = companyUpdateRequest.name();
            this.updatedAt = LocalDateTime.now();

        }
        if(companyUpdateRequest.address() != null){
            this.address = companyUpdateRequest.address();
            this.updatedAt = LocalDateTime.now();
        }
    }
}
