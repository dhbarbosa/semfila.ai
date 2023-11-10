package ai.semfila.api.config.erros.DTO;

import java.util.Date;

public record ErrorMessage(
        Date timestamp,
        int status,
        String Conflict
) {

}