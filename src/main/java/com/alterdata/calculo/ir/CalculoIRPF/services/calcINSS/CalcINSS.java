package com.alterdata.calculo.ir.CalculoIRPF.services.calcINSS;

import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Getter
@Data
@Service
public class CalcINSS implements BaseMensalAliquotaINSS {

    private double parcelaADeduzir;
    private double porcentagemAliquota;
    private double inss;
    private double deducaoAliquota;

    private double DecimalFormater(double decimal) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Double.parseDouble(df.format(decimal));
    }

    public double generateINSS(double salarioBruto){
        generateFaixaSalarial(salarioBruto);
        this.deducaoAliquota = salarioBruto * ( this.porcentagemAliquota / 100);
        double inss =  this.deducaoAliquota - this.parcelaADeduzir;

        if(inss > tetoINSS){
            inss = tetoINSS;
        }
        this.inss = DecimalFormater(inss);
        return DecimalFormater(inss);
    }

    public void generateFaixaSalarial(double salarioBruto){
        if(salarioBruto <= faixa1_valorFinal){
            this.parcelaADeduzir = faixa1_parcelaADeduzir;
            this.porcentagemAliquota = faixa1_aliquota;
        }

        if(salarioBruto > faixa1_valorFinal && salarioBruto <= faixa2_valorFinal){
            this.parcelaADeduzir = faixa2_parcelaADeduzir;
            this.porcentagemAliquota = faixa2_aliquota;
        }

        if(salarioBruto > faixa2_valorFinal && salarioBruto <= faixa3_valorFinal){
            this.parcelaADeduzir = faixa3_parcelaADeduzir;
            this.porcentagemAliquota = faixa3_aliquota;
        }

        if(salarioBruto >= faixa4_valorInicial ){
            this.parcelaADeduzir = faixa4_parcelaADeduzir;
            this.porcentagemAliquota = faixa4_aliquota;
        }
    }

}


