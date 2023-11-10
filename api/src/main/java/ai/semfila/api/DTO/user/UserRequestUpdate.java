package ai.semfila.api.DTO.user;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.UUID;

public record UserRequestUpdate(
        String name,

        LocalDate birthday

) {
}
