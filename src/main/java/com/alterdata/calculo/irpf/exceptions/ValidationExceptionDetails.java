package com.alterdata.calculo.irpf.exceptions;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class ValidationExceptionDetails extends ExceptionDetails {
    private final String fields;
    private final String fieldsMessage;
}
