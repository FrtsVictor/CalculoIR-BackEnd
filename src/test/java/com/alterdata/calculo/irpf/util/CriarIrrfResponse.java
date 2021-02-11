package com.alterdata.calculo.irpf.util;

import com.alterdata.calculo.irpf.models.irrf.IrrfResponse;

public class CriarIrrfResponse {


    public static IrrfResponse createValidIRPFResponse() {
        return IrrfResponse.builder()
                .nome("teste")
                .inss(1)
                .irrf(1)
                .dependentes(1)
                .baseDeCalculo(1)
                .totalDescontos(1)
                .parcelaADeduzir(1)
                .valorDependentes(1)
                .pensaoAlimenticia(1)
                .salarioMensalBruto(1)
                .porcentagemAliquota(1)
                .build();
    }
}
