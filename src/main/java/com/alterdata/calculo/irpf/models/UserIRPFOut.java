package com.alterdata.calculo.irpf.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class UserIRPFOut extends UserInSalAnual {

    private double rendimentoAnualBruto;
    private double porcentagemAliquota;
    private double baseDeCalculo;
    private double deducaoSimplificada;
    private double impostoInicial;
    private double parcelaDedutivel;
    private double impostoRenda;

    public void copyUsuarioEntrada(UserIRPFIn usrEntrada){
        this.nome = usrEntrada.nome;
    }

}
