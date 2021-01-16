package com.alterdata.calculo.irpf.models.irrf;

import com.alterdata.calculo.irpf.models.default_users.UserInSalarioMensal;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class UserIRRFRequest extends UserInSalarioMensal {

    private double dependentes;
    private double pensaoAlimenticia;

    public UserIRRFRequest(String nome, double salarioBruto, double dependentes, double pensaoAlimenticia) {
        super(nome, salarioBruto);
        this.dependentes = dependentes;
        this.pensaoAlimenticia = pensaoAlimenticia;
    }

    public UserIRRFRequest(double dependentes, double pensaoAlimenticia) {
        this.dependentes = dependentes;
        this.pensaoAlimenticia = pensaoAlimenticia;
    }


    @Override
    public boolean validateSalarioBruto(UserInSalarioMensal usrIn) {
        return super.validateSalarioBruto(usrIn);
    }

}
