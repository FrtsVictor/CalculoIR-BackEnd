package com.alterdata.calculo.irpf.util;

import com.alterdata.calculo.irpf.models.irrf.IrrfRequest;

public class CriarIrrfRequest {

    public static IrrfRequest createValidIrrfRequest() {
        return IrrfRequest.builder()
                .nome("Test")
                .salarioMensalBruto(1500)
                .dependentes(1)
                .pensaoAlimenticia(100)
                .build();
    }

    public static IrrfRequest createValidWithZeroIrrfRequest() {
        return IrrfRequest.builder()
                .nome("Test")
                .salarioMensalBruto(0)
                .dependentes(0)
                .pensaoAlimenticia(0)
                .build();
    }
}
