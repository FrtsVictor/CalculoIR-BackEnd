package com.alterdata.calculo.ir.CalculoIRPF.services.calcIRRF;

import com.alterdata.calculo.ir.CalculoIRPF.models.UserIRRFIn;
import com.alterdata.calculo.ir.CalculoIRPF.models.UserIRRFOut;
import com.alterdata.calculo.ir.CalculoIRPF.services.calcINSS.CalcINSSService;

import static com.alterdata.calculo.ir.CalculoIRPF.services.calcIRRF.BaseMensalIRPF.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Setter @Getter @NoArgsConstructor @Service
public class CalcIRRFService {

    private double IRRF;
    private double parcelaADeduzir;
    private double porcentagemAliquota;
    private double baseCalculo;
    private double valorDependentes;
    private double valorTotalDescontos;

    @Autowired
    CalcINSSService calcINSSService;

    public UserIRRFOut generateIRRF(UserIRRFIn user){
        generateBaseCalculo(user);
        generateFaixaSalarial();

        this.IRRF = (this.baseCalculo * this.porcentagemAliquota / 100) - this.parcelaADeduzir;
        return generateUsrIRRFOut(user);
    }

    public double generateBaseCalculo(UserIRRFIn user){
        double inss = calcINSSService.generateINSS(user.getSalarioBruto());
        this.valorDependentes = user.getDependentes() * valor_dependente;
        this.valorTotalDescontos = inss + this.valorDependentes + user.getPensaoAlimenticia();
        this.baseCalculo = user.getSalarioBruto() - this.valorTotalDescontos;
        return this.baseCalculo;
    }

    public void generateFaixaSalarial(){
        if(this.baseCalculo <= faixa1_valorFinal){
            this.parcelaADeduzir = faixa1_parcelaADeduzir;
            this.porcentagemAliquota = faixa1_aliquota;
        }

        if(this.baseCalculo >= faixa2_valorInicial && this.baseCalculo <= faixa2_valorFinal){
            this.parcelaADeduzir = faixa2_parcelaADeduzir;
            this.porcentagemAliquota = faixa2_aliquota;
        }

        if(this.baseCalculo >= faixa3_valorInicial && this.baseCalculo <= faixa3_valorFinal){
            this.parcelaADeduzir = faixa3_parcelaADeduzir;
            this.porcentagemAliquota = faixa3_aliquota;
        }

        if(this.baseCalculo >= faixa4_valorInicial && this.baseCalculo <= faixa4_valorFinal){
            this.parcelaADeduzir = faixa4_parcelaADeduzir;
            this.porcentagemAliquota = faixa4_aliquota;
        }

        if(this.baseCalculo >= faixa5_valorInicial ){
            this.parcelaADeduzir = faixa5_parcelaADeduzir;
            this.porcentagemAliquota = faixa5_aliquota;
        }
    }

    private UserIRRFOut generateUsrIRRFOut(UserIRRFIn user){
        return UserIRRFOut.builder()
                .nome(user.getNome())
                .salarioBruto(user.getSalarioBruto())
                .INSS(calcINSSService.generateINSS(user.getSalarioBruto()))
                .dependentes(user.getDependentes())
                .valorDependentes(this.getValorDependentes())
                .pensaoAlimenticia(user.getPensaoAlimenticia())
                .valorTotalDescontos(this.getValorTotalDescontos())
                .baseCalculo(this.getBaseCalculo())
                .porcentagemAliquota(this.getPorcentagemAliquota())
                .parcelaADeduzir(this.getParcelaADeduzir())
                .IRRF(this.getIRRF())
                .build();
    }

}
