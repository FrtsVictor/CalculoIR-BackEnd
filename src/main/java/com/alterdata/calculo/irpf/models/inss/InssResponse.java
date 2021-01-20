package com.alterdata.calculo.irpf.models.inss;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
public class InssResponse extends InssRequest {

    @PositiveOrZero
    private double parcelaADeduzir;
    @PositiveOrZero
    private double porcentagemAliquota;
    @PositiveOrZero
    private double inss;
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
