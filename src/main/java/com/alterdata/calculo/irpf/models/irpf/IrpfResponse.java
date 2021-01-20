package com.alterdata.calculo.irpf.models.irpf;

import com.alterdata.calculo.irpf.models.abtract_default_users.SalarioAnualRequest;
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
    private double porcentagemAliquota;
    @Positive
    private double baseDeCalculo;
    @Positive
    private double deducaoSimplificada;
    @PositiveOrZero
    private double impostoInicial;
    @PositiveOrZero
    private double parcelaDedutivel;
    @PositiveOrZero
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
