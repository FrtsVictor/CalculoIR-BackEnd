package com.alterdata.calculo.irpf.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public abstract class UserInSalAnual {
    protected String nome;
    protected double rendimentoAnualBruto;
}
