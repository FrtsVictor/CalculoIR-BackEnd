package com.alterdata.calculo.irpf.models.irpf;

import com.alterdata.calculo.irpf.models.default_users.UserSalarioAnual;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserIRPFRequest extends UserSalarioAnual {

    public UserIRPFRequest(String nome, double rendimentoAnualBruto) {
        super(nome, rendimentoAnualBruto);
    }

    public boolean validateRendimentoAnualBruto(UserIRPFRequest usrIn) {
        return usrIn.getRendimentoAnualBruto() < 1000;
    }

}
