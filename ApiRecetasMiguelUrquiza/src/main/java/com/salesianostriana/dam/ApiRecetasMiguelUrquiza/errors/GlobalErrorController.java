package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.errors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.errors.badRequest.EntityBadRequestException;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.errors.badRequest.TiempoInvalidoException;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.errors.confict.EntityConflict;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.errors.notfound.EntityNotFoundException;
import lombok.Builder;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestControllerAdvice
public class GlobalErrorController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ProblemDetail handlerEntityNotFound(EntityNotFoundException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Entidad no encontrada");
        return problemDetail;

    }

    @ExceptionHandler(EntityConflict.class)
    public ProblemDetail handlerEntityConflict(EntityConflict ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT,ex.getMessage());
        problemDetail.setTitle("Conflicto con la entidad");

        return problemDetail;
    }
    @ExceptionHandler(EntityBadRequestException.class)
    public ProblemDetail handlerTiempoInvalido(EntityBadRequestException ex){
        ProblemDetail problemDetail  = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,ex.getMessage());
        problemDetail.setTitle("Requisitos inválidos");
        return problemDetail;

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ProblemDetail result = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,"Error de validación");

        List<ApiValidationSubError> subErrors=
                ex.getAllErrors().stream()
                        .map(ApiValidationSubError::from)
                        .toList();

        result.setProperty("invalid-params",subErrors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(result);
    }

    @Builder
    record ApiValidationSubError(
            String object,
            String message,
            @JsonInclude(JsonInclude.Include.NON_NULL)
            String field,
            @JsonInclude(JsonInclude.Include.NON_NULL) //Esto lo que hace es que si es nulo no lo envia de vuelta no aparece.
            Object rejectedValue
    ){
        public ApiValidationSubError(String object, String message){
            this(object,message,null,null);
        }

        public static ApiValidationSubError from(ObjectError error){
            ApiValidationSubError result = null;

            if(error instanceof FieldError fieldError){
                result = ApiValidationSubError.builder()
                        .object(fieldError.getObjectName())
                        .message(fieldError.getDefaultMessage())
                        .field(fieldError.getField())
                        .rejectedValue(fieldError.getRejectedValue())
                        .build();
            }else{
                result = ApiValidationSubError.builder()
                        .object(error.getObjectName())
                        .message(error.getDefaultMessage())
                        .build();
            }
            return result;
        }

    }


}
