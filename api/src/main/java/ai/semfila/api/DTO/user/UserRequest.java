package ai.semfila.api.DTO.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record UserRequest(
        @NotBlank
        String name,
        @CPF
        String cpf,
        @Pattern(regexp ="^[0-9]{11}$")
        @NotBlank
        String phone,

        @NotNull
        LocalDate birthday

) {
}
