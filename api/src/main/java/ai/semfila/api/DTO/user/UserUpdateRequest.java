package ai.semfila.api.DTO.user;

import java.time.LocalDate;

public record UserUpdateRequest(
        String name,

        LocalDate birthday

) {
}
