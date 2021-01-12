package com.alterdata.calculo.ir.CalculoIRPF.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserINSSOut {

    private String nome;
    private double salarioBruto;
    private double parcelaADeduzir;
    private double porcentagemAliquota;
    private double inss;
    private double deducaoAliquota;

}
