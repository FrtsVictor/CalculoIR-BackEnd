package com.alterdata.calculo.irpf.services.calculo_mensal_irrf;

import com.alterdata.calculo.irpf.models.irrf.UserIRRFRequest;
import com.alterdata.calculo.irpf.models.irrf.UserIRRFResponse;
import com.alterdata.calculo.irpf.services.calculo_mensal_inss.CalcINSSService;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Setter
@Getter
@NoArgsConstructor
@Service
public class CalcIRRFService {

    private double irrf;
    private double parcelaADeduzir;
    private double porcentagemAliquota;
    private double baseCalculo;
    private double valorDependentes;
    private double valorTotalDescontos;
    private CalcINSSService calcINSSService;

    private double DecimalFormater(double decimal) {
        return Math.round(decimal * 100.0) / 100.0;
    }

    public UserIRRFResponse generateIRRF(UserIRRFRequest user) {
        generateBaseCalculo(user);
        generateFaixaSalarial();

        double IRRF = (this.baseCalculo * this.porcentagemAliquota / 100) - this.parcelaADeduzir;
        this.irrf = DecimalFormater(IRRF);
        return generateUsrIRRFOut(user);
    }

    public double generateBaseCalculo(UserIRRFRequest user) {
        calcINSSService = new CalcINSSService();
        double inss = calcINSSService.generateINSS(user.getSalarioMensalBruto());
        this.valorDependentes = user.getDependentes() * BaseMensalIRPF.valor_por_dependente;
        this.valorTotalDescontos = DecimalFormater(inss + this.valorDependentes + user.getPensaoAlimenticia());
        this.baseCalculo = DecimalFormater(user.getSalarioMensalBruto() - this.valorTotalDescontos);
        return this.baseCalculo;
    }

    public void generateFaixaSalarial() {
        if (this.baseCalculo <= BaseMensalIRPF.faixa_salarial_1_valor_final) {
            this.parcelaADeduzir = BaseMensalIRPF.faixa_salarial_1_parcela_dedutivel;
            this.porcentagemAliquota = BaseMensalIRPF.faixa_salarial_1_aliquota;
        }

        if (this.baseCalculo >= BaseMensalIRPF.faixa_salarial_2_valor_inicial &&
                this.baseCalculo <= BaseMensalIRPF.faixa_salarial_2_valor_final) {
            this.parcelaADeduzir = BaseMensalIRPF.faixa_salarial_2_parcela_dedutivel;
            this.porcentagemAliquota = BaseMensalIRPF.faixa_salarial_2_aliquota;
        }

        if (this.baseCalculo >= BaseMensalIRPF.faixa_salarial_3__valor_inicial &&
                this.baseCalculo <= BaseMensalIRPF.faixa_salarial_3_valor_final) {
            this.parcelaADeduzir = BaseMensalIRPF.faixa_salarial_3_parcela_dedutivel;
            this.porcentagemAliquota = BaseMensalIRPF.faixa_salarial_3_aliquota;
        }

        if (this.baseCalculo >= BaseMensalIRPF.faixa_salarial_4_valor_inicial &&
                this.baseCalculo <= BaseMensalIRPF.faixa_salarial_4_valor_final) {
            this.parcelaADeduzir = BaseMensalIRPF.faixa_salarial_4_parcela_dedutivel;
            this.porcentagemAliquota = BaseMensalIRPF.faixa_salarial_4_aliquota;
        }

        if (this.baseCalculo >= BaseMensalIRPF.faixa_salarial_5_valor_inicial) {
            this.parcelaADeduzir = BaseMensalIRPF.faixa_salarial_5_parcela_dedutivel;
            this.porcentagemAliquota = BaseMensalIRPF.faixa_salarial_5_aliquota;
        }
    }

    private UserIRRFResponse generateUsrIRRFOut(UserIRRFRequest user) {
        return UserIRRFResponse.builder()
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
                .IRRF(this.getIrrf())
                .build();
    }

}
