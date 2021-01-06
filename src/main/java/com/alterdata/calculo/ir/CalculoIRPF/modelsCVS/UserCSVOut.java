package com.alterdata.calculo.ir.CalculoIRPF.modelsCVS;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class UserCSVOut extends UserCSV {

    private double rendimentoAnualBruto;
    private double porcentagemAliquota;
    private double baseDeCalculo;
    private double deducaoSimplificada;
    private double impostoInicial;
    private double parcelaDedutivel;
    private double impostoRenda;

    public void copyUsuarioEntrada(UserCSVIn usrEntrada){
        this.cpf = usrEntrada.cpf;
        this.nome = usrEntrada.nome;
    }

}
