package com.alterdata.calculo.ir.CalculoIRPF.modelsCVS;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter @NoArgsConstructor
public abstract class UserCSV {

    protected String nome;
    protected String cpf;
    protected String dataNascimento;

}
