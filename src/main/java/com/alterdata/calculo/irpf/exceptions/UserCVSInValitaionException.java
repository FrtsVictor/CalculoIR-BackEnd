package com.alterdata.calculo.irpf.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class UserCVSInValitaionException extends Exception {

    private final String error;
    private final String message;

}
