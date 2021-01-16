package com.alterdata.calculo.irpf.models.irpf;

import com.alterdata.calculo.irpf.models.default_users.UserSalarioAnual;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Data
public class UserIRPFRequest extends UserSalarioAnual {

    public UserIRPFRequest(String nome, double rendimentoAnualBruto) {
        super(nome, rendimentoAnualBruto);
    }

    public boolean validateRendimentoAnualBruto(UserIRPFRequest usrIn) {
        if (usrIn.getRendimentoAnualBruto() < 1000) {
            return true;
        }
        return false;
    }

}
