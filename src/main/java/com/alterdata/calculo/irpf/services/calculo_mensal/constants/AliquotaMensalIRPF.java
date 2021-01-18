package com.alterdata.calculo.irpf.services.calculo_mensal.constants;

public enum AliquotaMensalIRPF {

    FAIXA_SALARIAL1_ALIQUOTA (0.0),
    FAIXA_SALARIAL1_VALOR_FINAL (1903.98),
    FAIXA_SALARIAL1_PARCELA_DEDUTIVEL (0.0),

    FAIXA_SALARIAL2_ALIQUOTA (7.5),
    FAIXA_SALARIAL2_VALOR_INICIAL (1903.99),
    FAIXA_SALARIAL2_VALOR_FINAL (2826.65),
    FAIXA_SALARIAL2_PARCELA_DEDUTIVEL (142.80),

    FAIXA_SALARIAL3_ALIQUOTA (15),
    FAIXA_SALARIAL3__VALOR_INICIAL (2826.66),
    FAIXA_SALARIAL3_VALOR_FINAL (3751.05),
    FAIXA_SALARIAL3_PARCELA_DEDUTIVEL (354.80),

    FAIXA_SALARIAL4_ALIQUOTA (22.5),
    FAIXA_SALARIAL4_VALOR_INICIAL (3751.06),
    FAIXA_SALARIAL4_VALOR_FINAL (4664.68),
    FAIXA_SALARIAL4_PARCELA_DEDUTIVEL (636.13),

    FAIXA_SALARIAL5_ALIQUOTA (27.5),
    FAIXA_SALARIAL5_VALOR_INICIAL (4664.69),
    FAIXA_SALARIAL5_PARCELA_DEDUTIVEL (869.36),

    VALOR_POR_DEPENDENTE (189.59);

    private final double value;

    AliquotaMensalIRPF(double faixa) {
        this.value = faixa;
    }

    public double value(){
        return value;
    }


}
