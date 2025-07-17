package com.pt.ada.prueba.common.exceptions;

import com.pt.ada.prueba.common.ResponseBs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Manejador para excepciones de lógica de negocio
    @ExceptionHandler(BusinessLogicException.class)
    public ResponseEntity<?> handleBusinessLogicException(BusinessLogicException ex) {
       // LOGGER.warn("Error de lógica de negocio: {}", ex.getMessage()); // Solo loguea como advertencia
        return new ResponseEntity<>(ResponseBs.error("ERROR_LOGICA_NEGOCIO", ex.getMessage()), HttpStatus.OK);
    }

    // Manejador para excepciones globales
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex) {
        LOGGER.error("Error inesperado: {}", ex.getMessage(), ex);
        return new ResponseEntity<>(ResponseBs.error("ERROR", ex.getMessage()), HttpStatus.OK);
    }
}
