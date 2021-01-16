package com.alterdata.calculo.irpf.services.calculo_anual_irpf;

import com.alterdata.calculo.irpf.models.irpf.UserIRPFResponse;
import org.springframework.stereotype.Service;

@Service
public class CalcIRSimplesService {

    private CalcAliqAnualSimplesIRPFService aliqService;
    private double impostoInicial;
    private double impostoRenda;

    public CalcIRSimplesService(CalcAliqAnualSimplesIRPFService aliqService) {
        this.aliqService = new CalcAliqAnualSimplesIRPFService();
    }

    private void calcularImpostoInicial() {
        this.impostoInicial =  aliqService.getPorcentagemAliquota() * aliqService.getBaseDeCalculo() /100 ;
    }

    public UserIRPFResponse calcularIR(CalcAliqAnualSimplesIRPFService calcAliqAnualSimplesWithoutCSVOutService) {
        this.aliqService = calcAliqAnualSimplesWithoutCSVOutService;
        calcularImpostoInicial();
        this.impostoRenda = this.impostoInicial - aliqService.getParcelaADeduzirAliquota();
        return generateCSVOut();
    }

    public UserIRPFResponse generateCSVOut() {
        return UserIRPFResponse.builder()
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