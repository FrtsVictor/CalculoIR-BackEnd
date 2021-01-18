package com.alterdata.calculo.irpf.services.calculo_mensal;

import com.alterdata.calculo.irpf.models.irrf.UserIRRFRequest;
import com.alterdata.calculo.irpf.models.irrf.UserIRRFResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import static com.alterdata.calculo.irpf.services.calculo_mensal.constants.AliquotaMensalIRPF.*;


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

    private double decimalFormater(double decimal) {
        return Math.round(decimal * 100.0) / 100.0;
    }

    public UserIRRFResponse generateIRRF(UserIRRFRequest user) {
        generateBaseCalculo(user);
        generateFaixaSalarial();

        double irrfCalc = (this.baseCalculo * this.porcentagemAliquota / 100) - this.parcelaADeduzir;
        this.irrf = decimalFormater(irrfCalc);
        return generateUsrIRRFOut(user);
    }

    public double generateBaseCalculo(UserIRRFRequest user) {
        calcINSSService = new CalcINSSService();
        double inss = calcINSSService.generateINSS(user.getSalarioMensalBruto());
        this.valorDependentes = user.getDependentes() * VALOR_POR_DEPENDENTE.value();
        this.valorTotalDescontos = decimalFormater(inss + this.valorDependentes + user.getPensaoAlimenticia());
        this.baseCalculo = decimalFormater(user.getSalarioMensalBruto() - this.valorTotalDescontos);
        return this.baseCalculo;
    }

    public void generateFaixaSalarial() {
        if (this.baseCalculo <= FAIXA_SALARIAL1_VALOR_FINAL.value()) {
            this.parcelaADeduzir = FAIXA_SALARIAL1_PARCELA_DEDUTIVEL.value();
            this.porcentagemAliquota = FAIXA_SALARIAL1_ALIQUOTA.value();
        }

        if (this.baseCalculo >= FAIXA_SALARIAL2_VALOR_INICIAL.value() &&
                this.baseCalculo <= FAIXA_SALARIAL2_VALOR_FINAL.value()) {
            this.parcelaADeduzir = FAIXA_SALARIAL2_PARCELA_DEDUTIVEL.value();
            this.porcentagemAliquota = FAIXA_SALARIAL2_ALIQUOTA.value();
        }

        if (this.baseCalculo >= FAIXA_SALARIAL3__VALOR_INICIAL.value() &&
                this.baseCalculo <= FAIXA_SALARIAL3_VALOR_FINAL.value()) {
            this.parcelaADeduzir = FAIXA_SALARIAL3_PARCELA_DEDUTIVEL.value();
            this.porcentagemAliquota = FAIXA_SALARIAL3_ALIQUOTA.value();
        }

        if (this.baseCalculo >= FAIXA_SALARIAL4_VALOR_INICIAL.value() &&
                this.baseCalculo <= FAIXA_SALARIAL4_VALOR_FINAL.value()) {
            this.parcelaADeduzir = FAIXA_SALARIAL4_PARCELA_DEDUTIVEL.value();
            this.porcentagemAliquota = FAIXA_SALARIAL4_ALIQUOTA.value();
        }

        if (this.baseCalculo >= FAIXA_SALARIAL5_VALOR_INICIAL.value()) {
            this.parcelaADeduzir = FAIXA_SALARIAL5_PARCELA_DEDUTIVEL.value();
            this.porcentagemAliquota = FAIXA_SALARIAL5_ALIQUOTA.value();
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
