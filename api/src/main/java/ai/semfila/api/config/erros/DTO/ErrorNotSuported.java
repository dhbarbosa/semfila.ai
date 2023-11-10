package ai.semfila.api.config.erros.DTO;

public record ErrorNotSuported(
        String message,
        String detail,
        int value,
        String string,
        String contextPath
) {

}
