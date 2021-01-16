package com.alterdata.calculo.irpf.models.inss;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserINSSResponse {

    private String nome;
    private double salarioMensalBruto;
    private double parcelaADeduzir;
    private double porcentagemAliquota;
    private double inss;
    private double deducaoAliquota;

}
