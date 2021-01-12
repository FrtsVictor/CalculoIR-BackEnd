package com.alterdata.calculo.ir.CalculoIRPF.services.calcAnualIRPF;

import com.alterdata.calculo.ir.CalculoIRPF.modelsCVS.CalcUserOut;
import org.springframework.stereotype.Service;

@Service
public class CalcIRSimplesService {

    CalcAliqAnualSimplesIRPFService aliqService;
    private double impostoInicial;
    private double impostoRenda;

    public CalcIRSimplesService(CalcAliqAnualSimplesIRPFService aliqService) {
        this.aliqService = new CalcAliqAnualSimplesIRPFService();
    }

    private void calcularImpostoInicial() {
        this.impostoInicial =  aliqService.getPorcentagemAliquota() * aliqService.getBaseDeCalculo() /100 ;
    }

    public void calcularIR(CalcAliqAnualSimplesIRPFService calcAliqAnualSimplesWithoutCSVOutService) {
        this.aliqService = calcAliqAnualSimplesWithoutCSVOutService;
        calcularImpostoInicial();
        this.impostoRenda = this.impostoInicial - aliqService.getParcelaADeduzirAliquota();
    }

    public CalcUserOut generateCSVOut() {
        return CalcUserOut.builder()
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