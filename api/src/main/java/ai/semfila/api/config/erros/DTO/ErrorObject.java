package ai.semfila.api.config.erros.DTO;

public record ErrorObject(
        String message,
        String field,
        Object parameter) {
}
