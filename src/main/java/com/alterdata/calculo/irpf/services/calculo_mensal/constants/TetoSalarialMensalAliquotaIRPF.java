package com.alterdata.calculo.irpf.services.calculo_mensal.constants;

public enum TetoSalarialMensalAliquotaIRPF {

    TETO_SALARIAL1_VALOR_FINAL(2074.15),

    TETO_SALARIAL2_VALOR_INICIAL(2074.16),
    TETO_SALARIAL2_VALOR_FINAL(3118.24),

    TETO_SALARIAL3_VALOR_INICIAL(3118.25),
    TETO_SALARIAL3_VALOR_FINAL(4188.77),

    TETO_SALARIAL4_VALOR_INICIAL(4188.78),
    TETO_SALARIAL4_VALOR_FINAL(5251.13),

    TETO_SALARIAL5_VALOR_INICIAL(5251.14);

    private final double value;

    TetoSalarialMensalAliquotaIRPF(double faixa) {
        this.value = faixa;
    }

    public double value() {
        return value;
    }


}
