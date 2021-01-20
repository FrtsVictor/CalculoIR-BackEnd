package com.alterdata.calculo.irpf.util;

import com.alterdata.calculo.irpf.models.irpf.IrpfResponse;

public class IrpfResponseCreator {

    public static IrpfResponse createValidIRPFResponse() {
        return IrpfResponse.builder()
                .baseDeCalculo(1)
                .deducaoSimplificada(1)
                .impostoInicial(1)
                .impostoRenda(1)
                .porcentagemAliquota(1)
                .parcelaDedutivel(1)
                .rendimentoAnualBruto(1)
                .nome("Test User")
                .build();
    }

}
