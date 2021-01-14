package com.alterdata.calculo.irpf.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserINSSIn extends UserInSalMensal {


    public UserINSSIn(String nome, double salarioBruto) {
        super(nome, salarioBruto);
    }

    public UserINSSIn() {
    }

    @Override
    public boolean validateNome(UserInSalMensal usrIn) {
        return super.validateNome(usrIn);
    }

    @Override
    public boolean validateSalarioBruto(UserInSalMensal usrIn) {
        return super.validateSalarioBruto(usrIn);
    }
}
