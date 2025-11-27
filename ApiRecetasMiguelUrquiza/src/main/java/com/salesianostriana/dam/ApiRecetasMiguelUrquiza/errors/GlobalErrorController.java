package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.errors;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.errors.badRequest.TiempoInvalidoException;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.errors.confict.EntityConflict;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.errors.notfound.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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
    @ExceptionHandler(TiempoInvalidoException.class)
    public ProblemDetail handlerTiempoInvalido(TiempoInvalidoException ex){
        ProblemDetail problemDetail  = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,ex.getMessage());
        problemDetail.setTitle("Tiempo de preparación inválido");
        return problemDetail;

    }

}
