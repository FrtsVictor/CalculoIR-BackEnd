package com.alterdata.calculo.irpf.services.calculo_anual_irpf;

public interface BaseAnualIRPF {

    double teto_deducao_simplificada = 16754.34;
    double valor_base_deducao_simplificada = 0.20;

    double faixa_salarial_1_aliquota = 0.0;
    double faixa_salarial_1_valor_final = 22847.76;
    double faixa_salarial_1_parcela_dedutivel = 0.0;

    double faixa_salarial_2_aliquota = 7.5;
    double faixa_salarial_2_valor_inicial = 22847.77;
    double faixa_salarial_2_valor_final = 33919.80;
    double faixa_salarial_2_parcela_dedutivel = 1713.58;

    double faixa_salarial_3_aliquota = 15;
    double faixa_salarial_3_valor_inicial = 33919.81;
    double faixa_salarial_3_valor_final = 45012.60;
    double faixa_salarial_3_parcela_dedutivel = 4257.57;

    double faixa_salarial_4_aliquota = 22.5;
    double faixa_salarial_4_valor_inicial = 45012.61;
    double faixa_salarial_4_valor_final = 55976.16;
    double faixa_salarial_4_parcela_dedutivel = 7633.51;

    double faixa_salarial_5_aliquota = 27.5;
    double faixa_salarial_5_valor_inicial = 55976.17;
    double faixa_salarial_5_parcela_dedutivel = 10432.32;

}
