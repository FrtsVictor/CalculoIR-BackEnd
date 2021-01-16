package com.alterdata.calculo.irpf.services.calculo_anual_irpf;

import com.alterdata.calculo.irpf.models.irpf.UserIRPFRequest;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Getter
@Setter
@Data
@Service
public class CalcAliqAnualSimplesIRPFService implements BaseAnualIRPF {

    private double rendimentoAnualBruto;
    private double deducaoSimplificada;
    private double baseDeCalculo;
    private double porcentagemAliquota;
    private double parcelaADeduzirAliquota;

    DecimalFormat df = new DecimalFormat("#.00");

    private void createDeducaoSimplificada() {
        double calcDeducaoSimplificada = this.rendimentoAnualBruto * valor_base_deducao_simplificada;

        if (calcDeducaoSimplificada > teto_deducao_simplificada) {
            calcDeducaoSimplificada = teto_deducao_simplificada;
        }
        this.deducaoSimplificada = Math.round(calcDeducaoSimplificada * 100.0) / 100.0;
    }

    private void createCalculoBase() {
        double calcBaseCalculo = this.rendimentoAnualBruto - deducaoSimplificada;
        this.baseDeCalculo = Math.round(calcBaseCalculo * 100.0) / 100.0;
    }

    private void aliquota1() {
        if (this.baseDeCalculo <= faixa_salarial_1_valor_final && this.baseDeCalculo > 0) {
            this.porcentagemAliquota = faixa_salarial_1_aliquota;
            this.parcelaADeduzirAliquota = faixa_salarial_1_parcela_dedutivel;
        }
    }

    private void aliquota2() {
        if (this.baseDeCalculo > faixa_salarial_1_valor_final &&
                this.baseDeCalculo <= faixa_salarial_2_valor_final) {
            this.porcentagemAliquota = faixa_salarial_2_aliquota;
            this.parcelaADeduzirAliquota = faixa_salarial_2_parcela_dedutivel;
        }
    }

    private void aliquota3() {
        if (this.baseDeCalculo >= faixa_salarial_3_valor_inicial &&
                this.baseDeCalculo <= faixa_salarial_3_valor_final) {
            this.porcentagemAliquota = faixa_salarial_3_aliquota;
            this.parcelaADeduzirAliquota = faixa_salarial_3_parcela_dedutivel;
        }
    }

    private void aliquota4() {
        if (this.baseDeCalculo >= faixa_salarial_4_valor_inicial &&
                this.baseDeCalculo <= faixa_salarial_4_valor_final) {
            this.porcentagemAliquota = faixa_salarial_4_aliquota;
            this.parcelaADeduzirAliquota = faixa_salarial_4_parcela_dedutivel;
        }
    }

    private void aliquota5() {
        if (this.baseDeCalculo >= faixa_salarial_5_valor_inicial) {
            this.parcelaADeduzirAliquota = faixa_salarial_5_parcela_dedutivel;
            this.porcentagemAliquota = faixa_salarial_5_aliquota;
        }
    }


    public void generateAliquota(UserIRPFRequest usrCSVIn) {
        this.rendimentoAnualBruto = usrCSVIn.getRendimentoAnualBruto();
        createDeducaoSimplificada();
        createCalculoBase();
        aliquota1();
        aliquota2();
        aliquota3();
        aliquota4();
        aliquota5();
    }


}