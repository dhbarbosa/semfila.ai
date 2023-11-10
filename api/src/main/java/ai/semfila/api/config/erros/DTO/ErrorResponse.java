package ai.semfila.api.config.erros.DTO;

import java.util.List;

public record ErrorResponse(
        String message,
        int code,
        String status,
        String objectName,
        List<ErrorObject> errors)  {
}
