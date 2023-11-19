package ai.semfila.api.DTO.type;

import jakarta.validation.constraints.NotBlank;

public record TypeUpdateRequest(
        @NotBlank
        String name,
        String description
) {
}
