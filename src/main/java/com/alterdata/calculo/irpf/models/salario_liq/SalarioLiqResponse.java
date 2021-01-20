package com.alterdata.calculo.irpf.models.salario_liq;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Valor referente ao total dos dependentes multiplicado pelo valor do mesmo")
    private double valorDependentes;

    @PositiveOrZero
    @Schema(description = "Valor referente base de calculo para gerar aliquota")
    private double baseDeCalculo;

    @PositiveOrZero
    @Schema(description = "Valor referente a porcentagem da aliquota para calulo de imposto de renda")
    private double porcentagemAliquota;

    @PositiveOrZero
    @Schema(description = "Valor referente a parcela dedutivel do desconto de aliquota")
    private double parcelaADeduzir;

    @PositiveOrZero
    @Schema(description = "Valor referente ao desconto de inss para pessoa simples")
    private double inss;

    @PositiveOrZero
    @Schema(description = "Valor referente ao imposto de renda retido na fonte, para pessoa simples")
    private double irrf;

    @PositiveOrZero
    @Schema(description = "Valor referente a soma de todos descontos efetuados no salario mensal bruto")
    private double totalDescontos;

    @PositiveOrZero
    @Schema(description = "Valor referente a salario liquido pessoa simples")
    private double salarioLiquido;


}
