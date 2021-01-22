package com.alterdata.calculo.irpf.services.calculo_mensal;

import com.alterdata.calculo.irpf.models.irrf.IrrfRequest;
import com.alterdata.calculo.irpf.models.irrf.IrrfResponse;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import static com.alterdata.calculo.irpf.services.calculo_mensal.constants.AliquotaMensalIRPF.*;


@Data
@Service
@NoArgsConstructor
public class IrrfService {

    private double irrf;
    private double parcelaADeduzir;
    private double porcentagemAliquota;
    private double baseCalculo;
    private double valorDependentes;
    private double valorTotalDescontos;
    private InssService inssService;

    private double decimalFormater(double decimal) {
        return Math.round(decimal * 100.0) / 100.0;
    }

    public IrrfResponse calcularIrrf(IrrfRequest user) {
        gerarBaseCalculo(user);
        gerarFaixaSalarial();

        double irrfCalc = (this.baseCalculo * this.porcentagemAliquota / 100) - this.parcelaADeduzir;
        this.irrf = decimalFormater(irrfCalc);
        return gerarIrrfResponse(user);
    }

    public double gerarBaseCalculo(IrrfRequest user) {
        inssService = new InssService();
        double inss = inssService.calcularINSS(user.getSalarioMensalBruto());
        this.valorDependentes = user.getDependentes() * VALOR_POR_DEPENDENTE.value();
        this.valorTotalDescontos = decimalFormater(inss + this.valorDependentes + user.getPensaoAlimenticia());
        this.baseCalculo = decimalFormater(user.getSalarioMensalBruto() - this.valorTotalDescontos);
        return this.baseCalculo;
    }

    public void gerarFaixaSalarial() {
        if (this.baseCalculo <= FAIXA_SALARIAL1_VALOR_FINAL.value()) {
            this.parcelaADeduzir = FAIXA_SALARIAL1_PARCELA_DEDUTIVEL.value();
            this.porcentagemAliquota = FAIXA_SALARIAL1_ALIQUOTA.value();
        }

        if (this.baseCalculo >= FAIXA_SALARIAL2_VALOR_INICIAL.value() &&
                this.baseCalculo <= FAIXA_SALARIAL2_VALOR_FINAL.value()) {
            this.parcelaADeduzir = FAIXA_SALARIAL2_PARCELA_DEDUTIVEL.value();
            this.porcentagemAliquota = FAIXA_SALARIAL2_ALIQUOTA.value();
        }

        if (this.baseCalculo >= FAIXA_SALARIAL3_VALOR_INICIAL.value() &&
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

    public IrrfResponse gerarIrrfResponse(IrrfRequest user) {
        double inss = inssService.calcularINSS(user.getSalarioMensalBruto());
        return IrrfResponse.builder()
                .nome(user.getNome())
                .salarioMensalBruto(user.getSalarioMensalBruto())
                .dependentes(user.getDependentes())
                .pensaoAlimenticia(user.getPensaoAlimenticia())
                .totalDescontos(this.getValorTotalDescontos())
                .valorDependentes(this.getValorDependentes())
                .baseDeCalculo(this.getBaseCalculo())
                .inss(inss)
                .porcentagemAliquota(this.getPorcentagemAliquota())
                .parcelaADeduzir(this.getParcelaADeduzir())
                .irrf(this.getIrrf())
                .build();
    }
}

