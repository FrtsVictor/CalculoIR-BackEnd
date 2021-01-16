package com.alterdata.calculo.irpf.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserINSSOut {

    private String nome;
    private double salarioMensalBruto;
    private double parcelaADeduzir;
    private double porcentagemAliquota;
    private double inss;
    private double deducaoAliquota;

}
