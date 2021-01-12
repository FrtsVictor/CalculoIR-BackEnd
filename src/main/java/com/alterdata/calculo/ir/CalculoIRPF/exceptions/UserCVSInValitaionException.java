package com.alterdata.calculo.ir.CalculoIRPF.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class UserCVSInValitaionException extends Exception {

    private String error;
    private String message;

}
