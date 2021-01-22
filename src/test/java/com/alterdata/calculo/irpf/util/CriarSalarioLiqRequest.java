package com.alterdata.calculo.irpf.util;

import com.alterdata.calculo.irpf.models.salario_liq.SalarioLiqRequest;

public class CriarSalarioLiqRequest {

    public static SalarioLiqRequest createValidUserSalarioLiq() {
        return SalarioLiqRequest.builder()
                .nome("Teste")
                .dependentes(1)
                .pensaoAlimenticia(1)
                .outrosDescontos(1)
                .salarioMensalBruto(1)
                .build();
    }

}
