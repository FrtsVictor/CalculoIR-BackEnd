package com.alterdata.calculo.irpf.models.irpf;

import com.alterdata.calculo.irpf.models.abtract_default_users.SalarioAnualRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class IrpfResponse extends SalarioAnualRequest {

    @PositiveOrZero
    @Schema(description = "Valor referente a porcentagem da aliquota para calulo de imposto de renda")
    private double porcentagemAliquota;

    @Positive
    @Schema(description = "Valor referente base de calculo para gerar aliquota")
    private double baseDeCalculo;

    @Positive
    @Schema(description = "Valor referente a deducao simplificada, 20% do rendimento anual bruto")
    private double deducaoSimplificada;

    @PositiveOrZero
    @Schema(description = "Valor referente ao impost de renda sem descontos da parcela dedutivel")
    private double impostoInicial;

    @PositiveOrZero
    @Schema(description = "Valor referente ao calculo da aliquota, deducao por faixa de renda")
    private double parcelaDedutivel;

    @PositiveOrZero
    @Schema(description = "Valor final do imposto de renda")
    private double impostoRenda;

    @Builder
    public IrpfResponse(@NotEmpty @Size(min = 4, max = 80) String nome,
                        @Positive double rendimentoAnualBruto,
                        @PositiveOrZero double porcentagemAliquota,
                        @Positive double baseDeCalculo,
                        @Positive double deducaoSimplificada,
                        @PositiveOrZero double impostoInicial,
                        @PositiveOrZero double parcelaDedutivel,
                        @PositiveOrZero double impostoRenda) {
        super(nome, rendimentoAnualBruto);
        this.porcentagemAliquota = porcentagemAliquota;
        this.baseDeCalculo = baseDeCalculo;
        this.deducaoSimplificada = deducaoSimplificada;
        this.impostoInicial = impostoInicial;
        this.parcelaDedutivel = parcelaDedutivel;
        this.impostoRenda = impostoRenda;
    }

    public IrpfResponse(@Positive double porcentagemAliquota, @Positive double baseDeCalculo, @Positive double deducaoSimplificada, @Positive double impostoInicial, @Positive double parcelaDedutivel, @Positive double impostoRenda) {
        this.porcentagemAliquota = porcentagemAliquota;
        this.baseDeCalculo = baseDeCalculo;
        this.deducaoSimplificada = deducaoSimplificada;
        this.impostoInicial = impostoInicial;
        this.parcelaDedutivel = parcelaDedutivel;
        this.impostoRenda = impostoRenda;
    }
}
