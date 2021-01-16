package com.alterdata.calculo.irpf.services.calcIRRF;

import com.alterdata.calculo.irpf.models.UserIRRFIn;
import com.alterdata.calculo.irpf.models.UserIRRFOut;
import com.alterdata.calculo.irpf.services.calcINSS.CalcINSSService;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Setter @Getter @NoArgsConstructor @Service
public class CalcIRRFService {

    private double IRRF;
    private double parcelaADeduzir;
    private double porcentagemAliquota;
    private double baseCalculo;
    private double valorDependentes;
    private double valorTotalDescontos;
    private CalcINSSService calcINSSService;

//    @Autowired
//    CalcINSSService calcINSSService;

    private double DecimalFormater(double decimal) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Math.round(decimal * 100.0)/100.0;
//        return Double.parseDouble(df.format(decimal));
    }

    public UserIRRFOut generateIRRF(UserIRRFIn user){
        generateBaseCalculo(user);
        generateFaixaSalarial();

        double IRRF = (this.baseCalculo * this.porcentagemAliquota / 100) - this.parcelaADeduzir;
        this.IRRF = DecimalFormater(IRRF);
        return generateUsrIRRFOut(user);
    }

    public double generateBaseCalculo(UserIRRFIn user){
        calcINSSService = new CalcINSSService();
        double inss = calcINSSService.generateINSS(user.getSalarioMensalBruto());
        this.valorDependentes = user.getDependentes() * BaseMensalIRPF.valor_dependente;
        this.valorTotalDescontos = DecimalFormater(inss + this.valorDependentes + user.getPensaoAlimenticia());
        this.baseCalculo = DecimalFormater(user.getSalarioMensalBruto() - this.valorTotalDescontos);
        return this.baseCalculo;
    }

    public void generateFaixaSalarial(){
        if(this.baseCalculo <= BaseMensalIRPF.faixa1_valorFinal){
            this.parcelaADeduzir = BaseMensalIRPF.faixa1_parcelaADeduzir;
            this.porcentagemAliquota = BaseMensalIRPF.faixa1_aliquota;
        }

        if(this.baseCalculo >= BaseMensalIRPF.faixa2_valorInicial && this.baseCalculo <= BaseMensalIRPF.faixa2_valorFinal){
            this.parcelaADeduzir = BaseMensalIRPF.faixa2_parcelaADeduzir;
            this.porcentagemAliquota = BaseMensalIRPF.faixa2_aliquota;
        }

        if(this.baseCalculo >= BaseMensalIRPF.faixa3_valorInicial && this.baseCalculo <= BaseMensalIRPF.faixa3_valorFinal){
            this.parcelaADeduzir = BaseMensalIRPF.faixa3_parcelaADeduzir;
            this.porcentagemAliquota = BaseMensalIRPF.faixa3_aliquota;
        }

        if(this.baseCalculo >= BaseMensalIRPF.faixa4_valorInicial && this.baseCalculo <= BaseMensalIRPF.faixa4_valorFinal){
            this.parcelaADeduzir = BaseMensalIRPF.faixa4_parcelaADeduzir;
            this.porcentagemAliquota = BaseMensalIRPF.faixa4_aliquota;
        }

        if(this.baseCalculo >= BaseMensalIRPF.faixa5_valorInicial ){
            this.parcelaADeduzir = BaseMensalIRPF.faixa5_parcelaADeduzir;
            this.porcentagemAliquota = BaseMensalIRPF.faixa5_aliquota;
        }
    }

    private UserIRRFOut generateUsrIRRFOut(UserIRRFIn user){
        return UserIRRFOut.builder()
                .nome(user.getNome())
                .salarioMensalBruto(user.getSalarioMensalBruto())
                .INSS(calcINSSService.generateINSS(user.getSalarioMensalBruto()))
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
