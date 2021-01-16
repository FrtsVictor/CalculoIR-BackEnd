package com.alterdata.calculo.irpf.models.inss;

import com.alterdata.calculo.irpf.models.default_users.UserInSalarioMensal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserINSSRequest extends UserInSalarioMensal {


    public UserINSSRequest(String nome, double salarioBruto) {
        super(nome, salarioBruto);
    }

    public UserINSSRequest() {
    }


    @Override
    public boolean validateSalarioBruto(UserInSalarioMensal usrIn) {
        return super.validateSalarioBruto(usrIn);
    }
}
