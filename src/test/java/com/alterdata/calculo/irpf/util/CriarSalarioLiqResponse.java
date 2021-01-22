package com.alterdata.calculo.irpf.util;

import com.alterdata.calculo.irpf.models.salario_liq.SalarioLiqResponse;

public class CriarSalarioLiqResponse {

    public static SalarioLiqResponse createValidSalarioLiqResponse() {
        return SalarioLiqResponse.builder()
                .nome("Teste")
                .inss(1)
                .irrf(1)
                .baseDeCalculo(1)
                .dependentes(1)
                .salarioLiquido(1)
                .outrosDescontos(1)
                .parcelaADeduzir(1)
                .valorDependentes(1)
                .pensaoAlimenticia(1)
                .salarioMensalBruto(1)
                .porcentagemAliquota(1)
                .totalDescontos(1)
                .build();
    }

}
