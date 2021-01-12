package com.alterdata.calculo.ir.CalculoIRPF.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserIRRFIn {

    private String nome;
    private double salarioBruto;
    private double dependentes;
    private double pensaoAlimenticia;
}
