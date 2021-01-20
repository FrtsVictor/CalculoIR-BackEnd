package com.alterdata.calculo.irpf.models.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPutRequest {

    @NotEmpty
    @Size(min = 5 , max = 50)
    private String username;
    @NotEmpty
    @Size(min = 3, max = 80)
    private String nome;
    private double salarioMensal;
    private double dependentes;
    private double pensaoAlimenticia;

}
