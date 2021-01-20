package com.alterdata.calculo.irpf.models.irrf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Data
@SuperBuilder
@NoArgsConstructor
public class IrrfResponse extends IrrfRequest {

    @PositiveOrZero
    private double baseDeCalculo;
    @PositiveOrZero
    private double porcentagemAliquota;
    @PositiveOrZero
    private double valorDependentes;
    @PositiveOrZero
    private double totalDescontos;
    @PositiveOrZero
    private double parcelaADeduzir;
    @PositiveOrZero
    private double irrf;
    @PositiveOrZero
    private double inss;


}
