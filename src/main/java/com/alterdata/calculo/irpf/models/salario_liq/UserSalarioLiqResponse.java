package com.alterdata.calculo.irpf.models.salario_liq;

import com.alterdata.calculo.irpf.models.default_users.UserInSalarioMensal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSalarioLiqResponse {

    private String nome;
    private double salarioMensalBruto;
    private double dependentes;
    private double valorDependentes;
    private double baseCalculo;
    private double pensaoAlimenticia;
    private double porcentagemAliquota;
    private double parcelaADeduzir;
    private double inss;
    private double irrf;
    private double valorTotalDescontos;
    private double outrosDescontos;
    private double salarioLiquido;

}
