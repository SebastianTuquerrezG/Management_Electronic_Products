package com.edu.unicauca.eproducts.eproduct_api_rest.controllers.exceptionsController;

import java.util.Locale;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.edu.unicauca.eproducts.eproduct_api_rest.controllers.exceptionsController.exceptionsEstructure.ErrorCode;
import com.edu.unicauca.eproducts.eproduct_api_rest.controllers.exceptionsController.exceptionsEstructure.ErrorUtils;
import com.edu.unicauca.eproducts.eproduct_api_rest.controllers.exceptionsController.exceptionsLocal.EntidadNoExisteException;
import com.edu.unicauca.eproducts.eproduct_api_rest.controllers.exceptionsController.exceptionsLocal.EntidadYaExisteException;
import com.edu.unicauca.eproducts.eproduct_api_rest.controllers.exceptionsController.exceptionsLocal.ReglaNegocioException;
import com.edu.unicauca.eproducts.eproduct_api_rest.controllers.exceptionsController.exceptionsEstructure.Error;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RestApiExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleGenericException(final HttpServletRequest request, final Exception exception, final Locale locale) {
        final Error error = ErrorUtils
                    .createError(ErrorCode.GENERIC_ERROR.getCodigo(), 
                        ErrorCode.GENERIC_ERROR.getLlaveMensaje(), 
                        HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .setUrl(request.getRequestURL().toString()).setMetodo(request.getMethod());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntidadYaExisteException.class)
    public ResponseEntity<Error> handleGenericException(final HttpServletRequest req,
                    final EntidadYaExisteException ex) {
            final Error error = ErrorUtils
                            .createError(ErrorCode.ENTITY_ALREADY_EXIST.getCodigo(),
                                            String.format("%s, %s", ErrorCode.ENTITY_ALREADY_EXIST.getLlaveMensaje(),
                                                            ex.getMessage()),
                                            HttpStatus.NOT_ACCEPTABLE.value())
                            .setUrl(req.getRequestURL().toString()).setMetodo(req.getMethod());
            return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(ReglaNegocioException.class)
    public ResponseEntity<Error> handleGenericException(final HttpServletRequest req,
                    final ReglaNegocioException ex, final Locale locale) {
            final Error error = ErrorUtils
                            .createError(ErrorCode.BUSINESS_RULE_VALIDATION.getCodigo(), ex.formatException(),
                                            HttpStatus.BAD_REQUEST.value())
                            .setUrl(req.getRequestURL().toString()).setMetodo(req.getMethod());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntidadNoExisteException.class)
    public ResponseEntity<Error> handleGenericException(final HttpServletRequest req,
                    final EntidadNoExisteException ex, final Locale locale) {
            final Error error = ErrorUtils
                            .createError(ErrorCode.ENTITY_NOT_FOUND.getCodigo(),
                                            String.format("%s, %s",
                                                            ErrorCode.ENTITY_NOT_FOUND.getLlaveMensaje(),
                                                            ex.getMessage()),
                                            HttpStatus.NOT_FOUND.value())
                            .setUrl(req.getRequestURL().toString()).setMetodo(req.getMethod());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}