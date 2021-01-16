package com.alterdata.calculo.irpf.models.irrf;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserIRRFResponse {

    private String nome;
    private double salarioMensalBruto;
    private double dependentes;
    private double valorDependentes;
    private double pensaoAlimenticia;
    private double IRRF;
    private double INSS;
    private double parcelaADeduzir;
    private double porcentagemAliquota;
    private double baseCalculo;
    private double valorTotalDescontos;
}
