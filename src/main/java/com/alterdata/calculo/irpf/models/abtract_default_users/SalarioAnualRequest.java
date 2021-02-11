package com.alterdata.calculo.irpf.models.abtract_default_users;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class SalarioAnualRequest {

    @NotEmpty
    @Size(min = 4, max = 80)
    @Schema(description = "Nome referente ao usuario para calculo", example = "Usuario Teste", required = true)
    protected String nome;

    @Positive
    @Schema(description = "Valor do rendimento anual bruto sem descontos", example = "20000.00", required = true)
    protected double rendimentoAnualBruto;
}
