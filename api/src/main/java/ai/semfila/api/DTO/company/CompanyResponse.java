package ai.semfila.api.DTO.company;

import ai.semfila.api.model.Company;

import java.time.LocalDateTime;
import java.util.UUID;

public record CompanyResponse(
        UUID id,

        String name,

        String cpnj,

        String address,

        String phone,

        LocalDateTime created_at
) {
    public CompanyResponse(Company company){
        this(company.getId(), company.getName(), company.getCnpj(), company.getAddress(), company.getPhone(), company.getCreatedAt());
    }
}
