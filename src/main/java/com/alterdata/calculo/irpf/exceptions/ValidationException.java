package com.alterdata.calculo.irpf.exceptions;

import lombok.Data;

import javax.validation.Path;
import java.util.Map;

@Data
public class ValidationException extends RuntimeException {

    private static final long serialVersionUID = 1905122041950251207L;

    private final Map<Path, String> error;

    public ValidationException(Map<javax.validation.Path, String> error) {
        this.error = error;
    }
}
