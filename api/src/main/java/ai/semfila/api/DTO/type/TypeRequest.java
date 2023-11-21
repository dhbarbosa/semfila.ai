package ai.semfila.api.DTO.type;

import jakarta.validation.constraints.NotBlank;

public record TypeRequest(
        @NotBlank
         String name,
         String description
) {
}
