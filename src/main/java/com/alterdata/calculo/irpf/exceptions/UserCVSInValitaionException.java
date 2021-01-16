package com.alterdata.calculo.irpf.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class UserCVSInValitaionException extends Exception {

    private String error;
    private String message;

}
