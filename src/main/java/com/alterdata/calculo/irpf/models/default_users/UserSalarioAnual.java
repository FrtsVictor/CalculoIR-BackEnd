package com.alterdata.calculo.irpf.models.default_users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public abstract class UserSalarioAnual {

    @NotEmpty
    @Size(min = 4, max = 80)
    protected String nome;
    protected double rendimentoAnualBruto;
}
