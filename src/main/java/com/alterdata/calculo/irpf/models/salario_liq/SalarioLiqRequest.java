package com.alterdata.calculo.irpf.models.salario_liq;

import com.alterdata.calculo.irpf.models.abtract_default_users.SalarioMensalRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
public class SalarioLiqRequest extends SalarioMensalRequest {

    @PositiveOrZero
    private double dependentes;
    @PositiveOrZero
    private double pensaoAlimenticia;
    @PositiveOrZero
    private double outrosDescontos;

}
