package com.alterdata.calculo.irpf.services.calculo_mensal_irrf;

public interface BaseMensalIRPF {

    double faixa_salarial_1_aliquota = 0.0;
    double faixa_salarial_1_valor_final = 1903.98;
    double faixa_salarial_1_parcela_dedutivel = 0.0;

    double faixa_salarial_2_aliquota = 7.5;
    double faixa_salarial_2_valor_inicial = 1903.99;
    double faixa_salarial_2_valor_final = 2826.65;
    double faixa_salarial_2_parcela_dedutivel = 142.80;

    double faixa_salarial_3_aliquota = 15;
    double faixa_salarial_3__valor_inicial = 2826.66;
    double faixa_salarial_3_valor_final = 3751.05;
    double faixa_salarial_3_parcela_dedutivel = 354.80;

    double faixa_salarial_4_aliquota = 22.5;
    double faixa_salarial_4_valor_inicial = 3751.06;
    double faixa_salarial_4_valor_final = 4664.68;
    double faixa_salarial_4_parcela_dedutivel = 636.13;

    double faixa_salarial_5_aliquota = 27.5;
    double faixa_salarial_5_valor_inicial = 4664.69;
    double faixa_salarial_5_parcela_dedutivel = 869.36;

    double valor_por_dependente = 189.59;
}
