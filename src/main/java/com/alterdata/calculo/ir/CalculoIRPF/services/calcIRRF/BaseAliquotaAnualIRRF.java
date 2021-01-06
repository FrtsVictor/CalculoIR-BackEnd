package com.alterdata.calculo.ir.CalculoIRPF.services.calcIRRF;

public interface BaseAliquotaAnualIRRF {

    double tetoINSS = 713.10;

    double faixa1_aliquota= 7.5;
    double faixa1_valorFinal = 1045.00;
    double faixa1_parcelaADeduzir = 0;

    double faixa2_aliquota = 9;
    double faixa2_valorInicial = 1045.01;
    double faixa2_valorFinal = 2089.60;
    double faixa2_parcelaADeduzir = 15.67;

    double faixa3_aliquota = 12;
    double faixa3_valorInicial = 2089.61;
    double faixa3_valorFinal =  3134.40;
    double faixa3_parcelaADeduzir = 78.36;

    double faixa4_aliquota = 22.5;
    double faixa4_valorInicial =  3134.41;
    double faixa4_valorFinal = 6101.06;
    double faixa4_parcelaADeduzir = 146.05;
}
