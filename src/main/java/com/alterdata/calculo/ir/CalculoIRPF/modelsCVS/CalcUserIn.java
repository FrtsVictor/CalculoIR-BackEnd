package com.alterdata.calculo.ir.CalculoIRPF.modelsCVS;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @Data
public class CalcUserIn extends CalcUser {

    private double rendimentoAnualBruto;

    public CalcUserIn(String nome,  double rendimentoAnualBruto) {
        super(nome);
        this.rendimentoAnualBruto = rendimentoAnualBruto;
    }

}
