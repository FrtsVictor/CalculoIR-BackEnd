package com.alterdata.calculo.irpf.models.abtract_default_users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class SalarioMensalRequest {

    @NotEmpty
    @Size(min = 4, max = 80)
    protected String nome;
    @Positive
    protected double salarioMensalBruto;
}
