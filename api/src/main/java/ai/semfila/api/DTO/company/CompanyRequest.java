package ai.semfila.api.DTO.company;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CNPJ;

public record CompanyRequest(
        @NotBlank
        String name,
        @CNPJ
        String cnpj,
        @NotBlank

        String address,
        @NotBlank
        @Pattern(regexp ="^[0-9]{11}$")
        String phone

) {
}
