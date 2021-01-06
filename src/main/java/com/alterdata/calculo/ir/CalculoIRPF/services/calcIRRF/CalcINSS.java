package com.alterdata.calculo.ir.CalculoIRPF.services.calcIRRF;

import com.alterdata.calculo.ir.CalculoIRPF.models.UserLiq;

import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
public class CalcINSS implements BaseAliquotaAnualIRRF{

    private double parcelaADeduzir;
    private double porcentagemAliquota;
    private double inss;

    public void generateINSS(UserLiq userLiq){
        generateFaixaSalarial(userLiq);
        double inss= userLiq.getSalarioLiquido() * this.porcentagemAliquota - this.parcelaADeduzir;

        if(inss > tetoINSS){
            inss = tetoINSS;
        }
        this.inss = inss;
    }

    public void generateFaixaSalarial(UserLiq userLiq){
        double salaraioLiquido = userLiq.getSalarioLiquido();

        if(salaraioLiquido <= faixa1_valorFinal){
            this.parcelaADeduzir = faixa1_parcelaADeduzir;
            this.porcentagemAliquota = faixa1_aliquota;
        }

        if(salaraioLiquido > faixa1_valorFinal & salaraioLiquido <= faixa2_valorFinal){
            this.parcelaADeduzir = faixa2_parcelaADeduzir;
            this.porcentagemAliquota = faixa2_aliquota;
        }

        if(salaraioLiquido > faixa2_valorFinal & salaraioLiquido <= faixa3_valorFinal){
            this.parcelaADeduzir = faixa3_parcelaADeduzir;
            this.porcentagemAliquota = faixa3_aliquota;
        }

        if(salaraioLiquido >= faixa4_valorInicial ){
            this.parcelaADeduzir = faixa4_parcelaADeduzir;
            this.porcentagemAliquota = faixa4_aliquota;
        }
    }

}
