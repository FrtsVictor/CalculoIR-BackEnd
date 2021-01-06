package com.alterdata.calculo.ir.CalculoIRPF.dto;

import lombok.Builder;
import lombok.Data;

@Builder  @Data
public class UserDto {

    private Integer id;
    private String nome;
    private String email;
    private String userName;

}
