package com.alterdata.calculo.irpf.services.calculo_mensal;

import com.alterdata.calculo.irpf.models.irrf.IrrfRequest;
import com.alterdata.calculo.irpf.models.irrf.IrrfResponse;
import com.alterdata.calculo.irpf.models.salario_liq.SalarioLiqRequest;
import com.alterdata.calculo.irpf.models.salario_liq.SalarioLiqResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@Service
@RequiredArgsConstructor
public class SalarioLiqService {

    private final IrrfService calcIRRF;
    private double outrosDescontos;
    private double totalDescontos;
    private double salarioLiq;

    public IrrfResponse calcularIrrf(SalarioLiqRequest user) {
        this.outrosDescontos = user.getOutrosDescontos();

        IrrfRequest irrfCalculation = new IrrfRequest();
        irrfCalculation.setNome(user.getNome());
        irrfCalculation.setSalarioMensalBruto(user.getSalarioMensalBruto());
        irrfCalculation.setDependentes(user.getDependentes());
        irrfCalculation.setPensaoAlimenticia(user.getPensaoAlimenticia());

        return this.calcIRRF.calcularIrrf(irrfCalculation);
    }

    public void calcularTotalDescontos(IrrfResponse irrfCalculation) {
        this.totalDescontos = this.getOutrosDescontos() + irrfCalculation.getIrrf() + irrfCalculation.getInss() +
                irrfCalculation.getPensaoAlimenticia();
    }

    public void calcularSalarioLiq(SalarioLiqRequest salarioLiqRequest) {
        this.salarioLiq = salarioLiqRequest.getSalarioMensalBruto() - this.totalDescontos;
    }

    public SalarioLiqResponse gerarSalarioLiqResponse(SalarioLiqRequest salarioLiqReq) {
        IrrfResponse irrfResp = calcularIrrf(salarioLiqReq);
        calcularTotalDescontos(irrfResp);
        calcularSalarioLiq(salarioLiqReq);

        return SalarioLiqResponse.builder()
                .nome(irrfResp.getNome())
                .salarioMensalBruto(irrfResp.getSalarioMensalBruto())
                .dependentes(irrfResp.getDependentes())
                .valorDependentes(irrfResp.getValorDependentes())
                .pensaoAlimenticia(irrfResp.getPensaoAlimenticia())
                .inss(irrfResp.getInss())
                .baseDeCalculo(irrfResp.getBaseDeCalculo())
                .porcentagemAliquota(irrfResp.getPorcentagemAliquota())
                .parcelaADeduzir(irrfResp.getParcelaADeduzir())
                .irrf(irrfResp.getIrrf())
                .totalDescontos(totalDescontos)
                .outrosDescontos(this.getOutrosDescontos())
                .salarioLiquido(salarioLiq)
                .build();
    }

}