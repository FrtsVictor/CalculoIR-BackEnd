package com.alterdata.calculo.irpf.services.calculo_mensal.constants;

public enum TetoSalarialMensalAliquotaIRPF {

    TETO_SALARIAL1_VALOR_FINAL(2075.07),

    TETO_SALARIAL2_VALOR_INICIAL(2075.08),
    TETO_SALARIAL2_VALOR_FINAL(3123.06),

    TETO_SALARIAL3_VALOR_INICIAL(3123.07),
    TETO_SALARIAL3_VALOR_FINAL(4197.68),

    TETO_SALARIAL4_VALOR_INICIAL(4197.69),
    TETO_SALARIAL4_VALOR_FINAL(5260.04),

    TETO_SALARIAL5_VALOR_INICIAL(5260.05);

    private final double value;

    TetoSalarialMensalAliquotaIRPF(double faixa) {
        this.value = faixa;
    }

    public double value() {
        return value;
    }


}
