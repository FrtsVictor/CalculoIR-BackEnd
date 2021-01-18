package com.alterdata.calculo.irpf.services.calculo_anual.constants;

public enum TetoSalarialAnualAliquotaIRPF {

    TETO_DEDUCAO_SALARIAL(83771.70),

    TETO_SALARIAL1_INICIAL(28559.70),

    TETO_SALARIAL2_INICIAL(28559.71),
    TETO_SALARIAL2_FINAL(42399.75),

    TETO_SALARIAL3_INICIAL(42399.76),
    TETO_SALARIAL3_FINAL(56265.75),

    TETO_SALARIAL4_INICIAL(56265.76),
    TETO_SALARIAL4_FINAL(69970.20),

    TETO_SALARIAL5_INICIAL(69970.21);

    private final double value;

    TetoSalarialAnualAliquotaIRPF(double faixa) {
        this.value = faixa;
    }

    public double value() {
        return value;
    }


}
