package com.pt.ada.prueba.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.SimpleDateFormat;
import java.util.*;

public class UtilsBs {


    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");


    public static <T> ResponseEntity<?> response(Optional<T> data, String descripcion) {
        if (data.isPresent()) {
            T t = data.get();
            return new ResponseEntity<>(ResponseBs.success("SUCCES", t, descripcion), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ResponseBs.notFoundAndDescription(null, descripcion), HttpStatus.OK);
        }
    }

    public static <T> ResponseEntity<?> responseCreate(Optional<T> data, String descripcion) {
        if (data.isPresent()) {
            T t = data.get();
            return new ResponseEntity<>(ResponseBs.created("SUCCES", t, descripcion), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ResponseBs.notFoundAndDescription(data, descripcion), HttpStatus.OK);
        }
    }


}
