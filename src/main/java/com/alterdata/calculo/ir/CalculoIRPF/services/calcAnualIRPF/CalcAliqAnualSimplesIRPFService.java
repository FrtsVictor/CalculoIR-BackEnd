package com.alterdata.calculo.ir.CalculoIRPF.services.calcAnualIRPF;

import com.alterdata.calculo.ir.CalculoIRPF.modelsCVS.CalcUserIn;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Getter @Setter @Data
@Service
public class CalcAliqAnualSimplesIRPFService implements BaseAnualIRPF {

    private double rendimentoAnualBruto;
    private double deducaoSimplificada;
    private double baseDeCalculo;
    private double porcentagemAliquota;
    private double parcelaADeduzirAliquota;

    DecimalFormat df = new DecimalFormat("#.00");

    private void createDeducaoSimplificada(){
        double calcDeducaoSimplificada = this.rendimentoAnualBruto * valorBaseDeducaoSimplificada;

        if(calcDeducaoSimplificada > tetoDeducaoSimplificada ){
            calcDeducaoSimplificada = tetoDeducaoSimplificada;
        }
        this.deducaoSimplificada = Math.round(calcDeducaoSimplificada * 100.0)/100.0;
    }


    private void createCalculoBase(){
        double calcBaseCalculo = this.rendimentoAnualBruto - deducaoSimplificada;
        this.baseDeCalculo = Math.round(calcBaseCalculo * 100.0)/100.0;
    }


    private void aliquota1() {
        if (this.baseDeCalculo <= base1_valorInicial && this.baseDeCalculo >  0) {
            this.porcentagemAliquota = base1_aliquota;
            this.parcelaADeduzirAliquota = base1_parcelaADeduzir;
        }
    }

    private void aliquota2() {
        if (this.baseDeCalculo > base1_valorInicial && this.baseDeCalculo <= base2_valorFinal) {
            this.porcentagemAliquota = base2_aliquota;
            this.parcelaADeduzirAliquota = base2_parcelaADeduzir;
        }
    }

    private void aliquota3() {
        if (this.baseDeCalculo >= base3_valorInicial && this.baseDeCalculo <= base3_valorFinal) {
            this.porcentagemAliquota = base3_aliquota;
            this.parcelaADeduzirAliquota = base3_parcelaADeduzir;
        }
    }

    private void aliquota4() {
        if (this.baseDeCalculo >= base4_valorInicial && this.baseDeCalculo <= base4_valorFinal) {
            this.porcentagemAliquota = base4_aliquota;
            this.parcelaADeduzirAliquota = base4_parcelaADeduzir;
        }
    }

    private void aliquota5() {
        if (this.baseDeCalculo >= base5_valorInicial) {
            this.parcelaADeduzirAliquota = base5_parcelaADeduzir;
            this.porcentagemAliquota = base5_aliquota;
        }
    }


    public void generateAliquota(CalcUserIn usrCSVIn) {
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