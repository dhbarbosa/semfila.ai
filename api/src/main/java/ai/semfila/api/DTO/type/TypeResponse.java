package ai.semfila.api.DTO.type;

import ai.semfila.api.model.Type;

import java.util.UUID;

public record TypeResponse(
        UUID uuid,
        String name,
        String description
) {
    public TypeResponse(Type type){
        this(type.getId(), type.getName(), type.getDescription());
    }
}
