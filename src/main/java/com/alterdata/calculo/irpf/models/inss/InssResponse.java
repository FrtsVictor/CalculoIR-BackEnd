package com.alterdata.calculo.irpf.models.inss;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
public class InssResponse extends InssRequest {

    @PositiveOrZero
    @Schema(description = "Valor referente a parcela dedutivel do desconto de aliquota")
    private double parcelaADeduzir;

    @PositiveOrZero
    @Schema(description = "Porcentagem de aliquota para gerar parcela dedutivel")
    private double porcentagemAliquota;

    @PositiveOrZero
    @Schema(description = "Valor do desconto de inss para pessoa simples")
    private double inss;

    @Schema(description = "Valor referente ao calculo da aliquota, deducao por faixa de renda")
    @PositiveOrZero
    private double deducaoAliquota;

    public InssResponse(@PositiveOrZero double parcelaADeduzir,
                        @PositiveOrZero double porcentagemAliquota,
                        @PositiveOrZero double inss,
                        @PositiveOrZero double deducaoAliquota) {
        this.parcelaADeduzir = parcelaADeduzir;
        this.porcentagemAliquota = porcentagemAliquota;
        this.inss = inss;
        this.deducaoAliquota = deducaoAliquota;
    }


    @Builder
    public InssResponse(String nome,
                        double salarioMensalBruto,
                        @PositiveOrZero double parcelaADeduzir,
                        @PositiveOrZero double porcentagemAliquota,
                        @PositiveOrZero double inss,
                        @PositiveOrZero double deducaoAliquota) {
        super(nome, salarioMensalBruto);
        this.parcelaADeduzir = parcelaADeduzir;
        this.porcentagemAliquota = porcentagemAliquota;
        this.inss = inss;
        this.deducaoAliquota = deducaoAliquota;
    }
}
