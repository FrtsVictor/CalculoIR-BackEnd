package com.alterdata.calculo.ir.CalculoIRPF.modelsCVS;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @Data
public class CalcUserIn extends CalcUser {

    private double rendimentoAnualBruto;

    public CalcUserIn(String nome, String cpf, String dataNascimento, double rendimentoAnualBruto) {
        super(nome, cpf);
        this.rendimentoAnualBruto = rendimentoAnualBruto;
    }

    public CalcUserIn(String nome, String cpf, double rendimentoAnualBruto) {
        this.nome = nome;
        this.cpf = cpf;
        this.rendimentoAnualBruto = rendimentoAnualBruto;
    }
}
