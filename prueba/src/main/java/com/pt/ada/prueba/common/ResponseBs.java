package com.pt.ada.prueba.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBs<T> {
    private int status;
    private String message;
    private String description;
    private T data;



    public static <T> ResponseBs<T> success(String message, T data, String description) {
        return new ResponseBs<>(HttpStatus.OK.value(), message, description != null ? description : message, data);
    }

    public static <T> ResponseBs<T> created(String message, T data, String description) {
        return new ResponseBs<>(HttpStatus.CREATED.value(), message, description != null ? description : message, data);
    }

    public static <T> ResponseBs<T> updated(String message, T data, String description) {
        return new ResponseBs<>(HttpStatus.OK.value(), message, description != null ? description : message, data);
    }

    public static <T> ResponseBs<T> notFound() {
        return new ResponseBs<>(HttpStatus.NOT_FOUND.value(), "Not Found", "No se han encontrado datos", null);
    }
    public static <T> ResponseBs<T> notFoundAndDescription(T data, String descripcion) {
        return new ResponseBs<>(HttpStatus.NOT_FOUND.value(), "Not Found", descripcion, data);
    }
    public static <T> ResponseBs<T> error(String message,String description) {
        return new ResponseBs<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, description, null);
    }

    public static <T> ResponseBs<T> notAuthorized() {
        return new ResponseBs<>(HttpStatus.UNAUTHORIZED.value(), "No autorizado", "No cuenta con permisos para realizar esta acción.", null);
    }
}
