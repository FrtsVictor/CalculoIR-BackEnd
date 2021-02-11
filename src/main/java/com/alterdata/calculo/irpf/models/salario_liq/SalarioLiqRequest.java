package com.alterdata.calculo.irpf.models.salario_liq;

import com.alterdata.calculo.irpf.models.abtract_default_users.SalarioMensalRequest;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Valor referente ao numero do total de dependentes")
    private double dependentes;

    @PositiveOrZero
    @Schema(description = "Valor referente a pensao alimenticia")
    private double pensaoAlimenticia;

    @PositiveOrZero
    @Schema(description = "Valor referente aos descontos em folha, plano de saude, vale alimentaçao e outros")
    private double outrosDescontos;

}
