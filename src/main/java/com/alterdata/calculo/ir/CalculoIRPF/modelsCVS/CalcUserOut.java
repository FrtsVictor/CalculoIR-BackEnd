package com.alterdata.calculo.ir.CalculoIRPF.modelsCVS;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class CalcUserOut extends CalcUser {

    private double rendimentoAnualBruto;
    private double porcentagemAliquota;
    private double baseDeCalculo;
    private double deducaoSimplificada;
    private double impostoInicial;
    private double parcelaDedutivel;
    private double impostoRenda;

    public void copyUsuarioEntrada(CalcUserIn usrEntrada){
        this.nome = usrEntrada.nome;
    }

}
