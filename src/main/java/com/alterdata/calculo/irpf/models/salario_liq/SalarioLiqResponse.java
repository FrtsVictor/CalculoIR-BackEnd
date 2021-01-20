package com.alterdata.calculo.irpf.models.salario_liq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SalarioLiqResponse extends SalarioLiqRequest {

    @PositiveOrZero
    private double valorDependentes;
    @PositiveOrZero
    private double baseDeCalculo;
    @PositiveOrZero
    private double porcentagemAliquota;
    @PositiveOrZero
    private double parcelaADeduzir;
    @PositiveOrZero
    private double inss;
    @PositiveOrZero
    private double irrf;
    @PositiveOrZero
    private double totalDescontos;
    @PositiveOrZero
    private double salarioLiquido;


}
