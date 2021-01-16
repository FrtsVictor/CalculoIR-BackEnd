package com.alterdata.calculo.irpf.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public abstract class UserInSalMensal {

    protected String nome;
    protected double salarioMensalBruto;

    protected boolean validateNome(UserInSalMensal usrIn) {
        if (usrIn.getNome().isBlank() || usrIn.getNome().length() < 3) {
            return false;
        }
        return true;
    }

    protected boolean validateSalarioBruto(UserInSalMensal usrIn) {
        if (usrIn.getSalarioMensalBruto() < 1) {
            return false;
        }
        return true;
    }

}
