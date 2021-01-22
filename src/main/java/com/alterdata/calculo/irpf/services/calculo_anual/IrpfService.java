package com.alterdata.calculo.irpf.services.calculo_anual;

import com.alterdata.calculo.irpf.models.irpf.IrpfResponse;
import org.springframework.stereotype.Service;

@Service
public class IrpfService {

    private AliqAnualIrpfService aliqService;
    private double impostoInicial;
    private double impostoRenda;

    public IrpfService(AliqAnualIrpfService aliqService) {
        this.aliqService = new AliqAnualIrpfService();
    }

    private void calcularImpostoInicial() {
        this.impostoInicial = aliqService.getPorcentagemAliquota() * aliqService.getBaseDeCalculo() / 100;
    }

    public IrpfResponse calcularIrpf(AliqAnualIrpfService aliqIrpfService) {
        this.aliqService = aliqIrpfService;
        calcularImpostoInicial();
        this.impostoRenda = this.impostoInicial - aliqService.getParcelaADeduzirAliquota();
        return gerarIrpfResponse();
    }

    public IrpfResponse gerarIrpfResponse() {
        return IrpfResponse.builder()
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