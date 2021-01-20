package com.alterdata.calculo.irpf.models.irrf;

import com.alterdata.calculo.irpf.models.abtract_default_users.SalarioMensalRequest;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
public class IrrfRequest extends SalarioMensalRequest {

    @PositiveOrZero
    private double dependentes;
    @PositiveOrZero
    private double pensaoAlimenticia;



}

