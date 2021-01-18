package com.alterdata.calculo.irpf.models.default_users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class UserInSalarioMensal {

    @NotEmpty
    @Size(min = 4, max = 80)
    protected String nome;
    protected double salarioMensalBruto;

    protected boolean validateSalarioBruto(UserInSalarioMensal usrIn) {
        return usrIn.getSalarioMensalBruto() < 1;
    }

}
