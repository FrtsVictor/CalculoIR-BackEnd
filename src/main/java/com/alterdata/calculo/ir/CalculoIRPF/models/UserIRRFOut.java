package com.alterdata.calculo.ir.CalculoIRPF.models;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserIRRFOut {

    private String nome;
    private double salarioBruto;
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
