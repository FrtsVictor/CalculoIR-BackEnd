package com.alterdata.calculo.irpf.services.calcIRRF;

public interface BaseMensalIRPF {

    double faixa1_aliquota = 0.0;
    double faixa1_valorFinal = 1903.98;
    double faixa1_parcelaADeduzir = 0.0;

    double faixa2_aliquota = 7.5;
    double faixa2_valorInicial = 1903.99;
    double faixa2_valorFinal = 2826.65;
    double faixa2_parcelaADeduzir = 142.80;

    double faixa3_aliquota = 15;
    double faixa3_valorInicial = 2826.66;
    double faixa3_valorFinal = 3751.05;
    double faixa3_parcelaADeduzir = 354.80;

    double faixa4_aliquota = 22.5;
    double faixa4_valorInicial = 3751.06;
    double faixa4_valorFinal = 4664.68;
    double faixa4_parcelaADeduzir = 636.13;

    double faixa5_aliquota = 27.5;
    double faixa5_valorInicial = 4664.69;
    double faixa5_parcelaADeduzir = 869.36;

    double valor_dependente = 189.59;
}
