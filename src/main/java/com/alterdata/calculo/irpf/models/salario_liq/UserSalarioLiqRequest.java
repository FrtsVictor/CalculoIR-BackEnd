package com.alterdata.calculo.irpf.models.salario_liq;

import com.alterdata.calculo.irpf.models.default_users.UserInSalarioMensal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Builder
public class UserSalarioLiqRequest extends UserInSalarioMensal {

    private double dependentes;
    private double pensaoAlimenticia;
    private double outrosDescontos;

    public UserSalarioLiqRequest(double dependentes, double pensaoAlimenticia, double outrosDescontos) {
        this.dependentes = dependentes;
        this.pensaoAlimenticia = pensaoAlimenticia;
        this.outrosDescontos = outrosDescontos;
    }

    public UserSalarioLiqRequest(@NotEmpty @Size(min = 4, max = 80) String nome, double salarioMensalBruto, double dependentes, double pensaoAlimenticia, double outrosDescontos) {
        super(nome, salarioMensalBruto);
        this.dependentes = dependentes;
        this.pensaoAlimenticia = pensaoAlimenticia;
        this.outrosDescontos = outrosDescontos;
    }
}
