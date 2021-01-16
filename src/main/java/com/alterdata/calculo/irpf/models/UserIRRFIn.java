package com.alterdata.calculo.irpf.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class UserIRRFIn extends UserInSalMensal {

    private double dependentes;
    private double pensaoAlimenticia;

    public UserIRRFIn(String nome, double salarioBruto, double dependentes, double pensaoAlimenticia) {
        super(nome, salarioBruto);
        this.dependentes = dependentes;
        this.pensaoAlimenticia = pensaoAlimenticia;
    }

    public UserIRRFIn(double dependentes, double pensaoAlimenticia) {
        this.dependentes = dependentes;
        this.pensaoAlimenticia = pensaoAlimenticia;
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
