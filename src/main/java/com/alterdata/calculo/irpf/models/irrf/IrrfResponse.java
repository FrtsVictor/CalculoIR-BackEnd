package com.alterdata.calculo.irpf.models.irrf;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.PositiveOrZero;

@Data
@SuperBuilder
@NoArgsConstructor
public class IrrfResponse extends IrrfRequest {

    @PositiveOrZero
    @Schema(description = "Valor referente base de calculo para gerar aliquota")
    private double baseDeCalculo;

    @PositiveOrZero
    @Schema(description = "Valor referente a porcentagem da aliquota para calulo de imposto de renda")
    private double porcentagemAliquota;

    @PositiveOrZero
    @Schema(description = "Valor referente ao total dos dependentes multiplicado pelo valor do mesmo")
    private double valorDependentes;

    @PositiveOrZero
    private double totalDescontos;

    @PositiveOrZero
    @Schema(description = "Valor referente a parcela dedutivel do desconto de aliquota")
    private double parcelaADeduzir;

    @PositiveOrZero
    @Schema(description = "Valor referente ao imposto de renda retido na fonte, para pessoa simples")
    private double irrf;

    @PositiveOrZero
    @Schema(description = "Valor referente ao desconto de inss para pessoa simples")
    private double inss;


}
