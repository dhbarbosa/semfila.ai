package ai.semfila.api.config.erros;

import ai.semfila.api.config.erros.DTO.ErrorMessage;
import ai.semfila.api.config.erros.DTO.ErrorNotSuported;
import ai.semfila.api.config.erros.DTO.ErrorObject;
import ai.semfila.api.config.erros.DTO.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<ErrorObject> errors = getErrors(ex);
        ErrorResponse errorResponse = getErrorResponse(ex, status, errors);
        return new ResponseEntity<>(errorResponse, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorNotSuported errorNotSuported = getErroNotSuported(ex, headers, status, request);
        return new ResponseEntity<>(errorNotSuported, status);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
        return new ResponseEntity<>(getErrorMessage(ex, HttpStatus.FORBIDDEN), HttpStatus.FORBIDDEN);
    }

    private ErrorMessage getErrorMessage(
            AccessDeniedException ex,
            HttpStatusCode status) {


        return new ErrorMessage(
                new Date(),
                status.value(),
                "Denied!!"

        );
    }
    private ErrorNotSuported getErroNotSuported(
            HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        return new ErrorNotSuported(
                "Request not supported!",
                ex.getBody().getDetail(),
                status.value(),
                headers.toString(),
                request.getContextPath());
    }

    private ErrorResponse getErrorResponse(
            MethodArgumentNotValidException ex,
            HttpStatusCode status,
            List<ErrorObject> errors
    ) {
        return new ErrorResponse(
                "Request have some invalid field",
                status.value(),
                status.toString(),
                ex.getBindingResult().getObjectName(),
                errors);
    }

    private List<ErrorObject> getErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new ErrorObject(error.getDefaultMessage(), error.getField(), error.getRejectedValue()))
                .collect(Collectors.toList());
    }
}