package com.alterdata.calculo.irpf.services.calcINSS;

import com.alterdata.calculo.irpf.models.UserINSSIn;
import com.alterdata.calculo.irpf.models.UserINSSOut;
import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Getter
@Data
@Service
public class CalcINSSService implements BaseMensalAliquotaINSS {

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
        this.deducaoAliquota = DecimalFormater(salarioBruto * ( this.porcentagemAliquota / 100));
        double inss =  this.deducaoAliquota - this.parcelaADeduzir;

        if(inss > tetoINSS){
            inss = tetoINSS;
        }
        this.inss = DecimalFormater(inss);
        return this.inss;
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

    public UserINSSOut generateUserINSSOut(UserINSSIn user){
        return UserINSSOut.builder()
                .salarioMensalBruto(user.getSalarioMensalBruto())
                .nome(user.getNome())
                .parcelaADeduzir(this.getParcelaADeduzir())
                .porcentagemAliquota(this.getPorcentagemAliquota())
                .inss(this.getInss())
                .deducaoAliquota(this.getDeducaoAliquota())
                .build();
    }

}


