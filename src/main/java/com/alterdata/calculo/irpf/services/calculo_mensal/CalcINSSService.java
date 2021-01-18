package com.alterdata.calculo.irpf.services.calculo_mensal;

import static com.alterdata.calculo.irpf.services.calculo_mensal.constants.AliquotaMensalINSS.*;

import com.alterdata.calculo.irpf.models.inss.UserINSSRequest;
import com.alterdata.calculo.irpf.models.inss.UserINSSResponse;
import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Getter
@Data
@Service
public class CalcINSSService {

    private double parcelaADeduzir;
    private double porcentagemAliquota;
    private double inss;
    private double deducaoAliquota;

    private double decimalFormater(double decimal) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Double.parseDouble(df.format(decimal));
    }

    public double generateINSS(double salarioBruto) {
        generateFaixaSalarial(salarioBruto);
        this.deducaoAliquota = decimalFormater(salarioBruto * (this.porcentagemAliquota / 100));
        double inssCalc = this.deducaoAliquota - this.parcelaADeduzir;

        if (inssCalc > TETO_INSS.value()) {
            inssCalc = TETO_INSS.value();
        }
        this.inss = decimalFormater(inssCalc);
        return this.inss;
    }

    public void generateFaixaSalarial(double salarioBruto) {

        if (salarioBruto <= FAIXA_SALARIAL1_VALOR_FINAL.value()) {
            this.parcelaADeduzir = FAIXA_SALARIAL1_PARCELA_DEDUTIVEL.value();
            this.porcentagemAliquota = FAIXA_SALARIAL1_ALIQUOTA.value();
        }
        if (salarioBruto >= FAIXA_SALARIAL2_VALOR_INICIAL.value() &&
                salarioBruto <= FAIXA_SALARIAL2_VALOR_FINAL.value()) {
            this.parcelaADeduzir = FAIXA_SALARIAL2_PARCELA_DEDUTIVEL.value();
            this.porcentagemAliquota = FAIXA_SALARIAL2_ALIQUOTA.value();
        }

        if (salarioBruto >= FAIXA_SALARIAL3_VALOR_INICIAL.value()
                && salarioBruto <= FAIXA_SALARIAL3_VALOR_FINAL.value()) {
            this.parcelaADeduzir = FAIXA_SALARIAL3_PARCELA_DEDUTIVEL.value();
            this.porcentagemAliquota = FAIXA_SALARIAL3_ALIQUOTA.value();
        }

        if (salarioBruto >= FAIXA_SALARIAL4_VALOR_INICIAL.value()) {
            this.parcelaADeduzir = FAIXA_SALARIAL4_PARCELA_DEDUTIVEL.value();
            this.porcentagemAliquota = FAIXA_SALARIAL4_ALIQUOTA.value();
        }
    }

    public UserINSSResponse generateUserINSSOut(UserINSSRequest user) {
        return UserINSSResponse.builder()
                .salarioMensalBruto(user.getSalarioMensalBruto())
                .nome(user.getNome())
                .parcelaADeduzir(this.getParcelaADeduzir())
                .porcentagemAliquota(this.getPorcentagemAliquota())
                .inss(this.getInss())
                .deducaoAliquota(this.getDeducaoAliquota())
                .build();
    }

}


