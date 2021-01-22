package com.alterdata.calculo.irpf.services.calculo_anual;

import com.alterdata.calculo.irpf.models.irpf.IrpfRequest;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

import static com.alterdata.calculo.irpf.services.calculo_anual.constants.AliquotaAnualIRPF.*;


@Data
@Service
public class AliqAnualIrpfService {

    private double rendimentoAnualBruto;
    private double deducaoSimplificada;
    private double baseDeCalculo;
    private double porcentagemAliquota;
    private double parcelaADeduzirAliquota;

    DecimalFormat df = new DecimalFormat("#.00");

    private void calcularDeducaoSimplificada() {
        double calcDeducaoSimplificada = this.rendimentoAnualBruto * VALOR_BASE_DEDUCAO_SIMPLIFICADA.value();

        if (calcDeducaoSimplificada > TETO_DEDUCAO_SIMPLIFICADA.value()) {
            calcDeducaoSimplificada = TETO_DEDUCAO_SIMPLIFICADA.value();
        }
        this.deducaoSimplificada = Math.round(calcDeducaoSimplificada * 100.0) / 100.0;
    }


    private void gerarBaseDeCalculo() {
        double calcBaseCalculo = this.rendimentoAnualBruto - deducaoSimplificada;
        this.baseDeCalculo = Math.round(calcBaseCalculo * 100.0) / 100.0;
    }

    private void aliquota1() {
        if (this.baseDeCalculo <= FAIXA_SALARIAL1_VALOR_FINAL.value() && this.baseDeCalculo > 0) {
            this.porcentagemAliquota = FAIXA_SALARIAL1_ALIQUOTA.value();
            this.parcelaADeduzirAliquota = FAIXA_SALARIAL1_PARCELA_DEDUTIVEL.value();
        }
    }

    private void aliquota2() {
        if (this.baseDeCalculo >= FAIXA_SALARIAL2_VALOR_INICIAL.value() &&
                this.baseDeCalculo <= FAIXA_SALARIAL2_VALOR_FINAL.value()) {
            this.porcentagemAliquota = FAIXA_SALARIAL2_ALIQUOTA.value();
            this.parcelaADeduzirAliquota = FAIXA_SALARIAL2_PARCELA_DEDUTIVEL.value();
        }
    }

    private void aliquota3() {
        if (this.baseDeCalculo >= FAIXA_SALARIAL3_VALOR_INICIAL.value() &&
                this.baseDeCalculo <= FAIXA_SALARIAL3_VALOR_FINAL.value()) {
            this.porcentagemAliquota = FAIXA_SALARIAL3_ALIQUOTA.value();
            this.parcelaADeduzirAliquota = FAIXA_SALARIAL3_PARCELA_DEDUTIVEL.value();
        }
    }

    private void aliquota4() {
        if (this.baseDeCalculo >= FAIXA_SALARIAL4_VALOR_INICIAL.value() &&
                this.baseDeCalculo <= FAIXA_SALARIAL4_VALOR_FINAL.value()) {
            this.porcentagemAliquota = FAIXA_SALARIAL4_ALIQUOTA.value();
            this.parcelaADeduzirAliquota = FAIXA_SALARIAL4_PARCELA_DEDUTIVEL.value();
        }
    }

    private void aliquota5() {
        if (this.baseDeCalculo >= FAIXA_SALARIAL5_VALOR_INICIAL.value()) {
            this.parcelaADeduzirAliquota = FAIXA_SALARIAL5_PARCELA_DEDUTIVEL.value();
            this.porcentagemAliquota = FAIXA_SALARIAL5_ALIQUOTA.value();
        }
    }


    public void gerarAliquota(IrpfRequest irpfRequest) {
        this.rendimentoAnualBruto = irpfRequest.getRendimentoAnualBruto();
        calcularDeducaoSimplificada();
        gerarBaseDeCalculo();
        aliquota1();
        aliquota2();
        aliquota3();
        aliquota4();
        aliquota5();
    }


}