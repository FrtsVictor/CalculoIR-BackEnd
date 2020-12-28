package com.alterdata.calculo.ir.CalculoIRPF.services.calc;

import com.alterdata.calculo.ir.CalculoIRPF.modelsCVS.UserCSVOut;
import org.springframework.stereotype.Service;

@Service
public class CalcImpostoRenda {

    CalcBaseAliqAnualSimplesWithoutCSVOutService aliqService;
    private double impostoInicial;
    private double impostoRenda;

    public CalcImpostoRenda(CalcBaseAliqAnualSimplesWithoutCSVOutService aliqService) {
        this.aliqService = new CalcBaseAliqAnualSimplesWithoutCSVOutService();
    }

    private void calcularImpostoInicial() {
        this.impostoInicial =  aliqService.getPorcentagemAliquota() * aliqService.getBaseDeCalculo() /100 ;
    }

    public void calcularIR(CalcBaseAliqAnualSimplesWithoutCSVOutService calcAliqAnualSimplesWithoutCSVOutService) {
        this.aliqService = calcAliqAnualSimplesWithoutCSVOutService;
        calcularImpostoInicial();
        this.impostoRenda = this.impostoInicial - aliqService.getParcelaADeduzirAliquota();
    }

    public UserCSVOut generateCSVOut() {
        return UserCSVOut.builder()
                .rendimentoAnualBruto(aliqService.getRendimentoAnualBruto())
                .porcentagemAliquota(aliqService.getPorcentagemAliquota())
                .baseDeCalculo(this.aliqService.getBaseDeCalculo())
                .parcelaDedutivel(this.aliqService.getParcelaADeduzirAliquota())
                .deducaoSimplificada(aliqService.getDeducaoSimplificada())
                .impostoInicial(this.impostoInicial)
                .impostoRenda(this.impostoRenda)
                .build();
    }

}
