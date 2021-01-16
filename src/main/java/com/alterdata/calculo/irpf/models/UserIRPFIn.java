package com.alterdata.calculo.irpf.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @Data
public class UserIRPFIn extends UserInSalAnual {

    public UserIRPFIn(String nome, double rendimentoAnualBruto) {
        super(nome, rendimentoAnualBruto);
    }

    public boolean validateNome(UserIRPFIn usrIn) {
        if (usrIn.getNome().isBlank() || usrIn.getNome().length() < 3) {
            return true;
        }
        return false;
    }

    public boolean validateRendimentoAnualBruto(UserIRPFIn usrIn) {
        if (usrIn.getRendimentoAnualBruto() < 1000) {
            return true;
        }
        return false;
    }

}
