package com.alterdata.calculo.irpf.util;

import com.alterdata.calculo.irpf.models.inss.InssResponse;

public class InssResponseCreator {

    public static InssResponse createValidINSSResponse() {
        return InssResponse.builder()
                .nome("Test User")
                .salarioMensalBruto(100)
                .deducaoAliquota(100)
                .inss(100)
                .parcelaADeduzir(100)
                .porcentagemAliquota(100)
                .build();
    }

}
