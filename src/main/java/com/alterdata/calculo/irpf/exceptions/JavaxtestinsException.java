package com.alterdata.calculo.irpf.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Path;
import java.util.Map;

@Data
public class JavaxtestinsException extends RuntimeException {

  private Map<Path, String> error;

  public JavaxtestinsException(Map<javax.validation.Path, String> error) {
    this.error = error;
  }
}
