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


    public SalarioLiqResponse generateSalarioLiqResponse(SalarioLiqRequest salarioLiqReq) {
        IrrfResponse irrfResp = generateIrrfSalarioLiq(salarioLiqReq);
        generateTotalDescontos(irrfResp);
        generateSalarioLiq(salarioLiqReq);

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

    public IrrfResponse generateIrrfSalarioLiq(SalarioLiqRequest user) {
        this.outrosDescontos = user.getOutrosDescontos();

        IrrfRequest irrfCalculation = new IrrfRequest();
        irrfCalculation.setNome(user.getNome());
        irrfCalculation.setSalarioMensalBruto(user.getSalarioMensalBruto());
        irrfCalculation.setDependentes(user.getDependentes());
        irrfCalculation.setPensaoAlimenticia(user.getPensaoAlimenticia());

        return this.calcIRRF.generateIRRF(irrfCalculation);
    }

    public void generateTotalDescontos(IrrfResponse irrfCalculation) {
        this.totalDescontos = this.getOutrosDescontos() + irrfCalculation.getIrrf() + irrfCalculation.getInss() +
                irrfCalculation.getPensaoAlimenticia();
    }

    public void generateSalarioLiq(SalarioLiqRequest salarioLiqRequest) {
        this.salarioLiq = salarioLiqRequest.getSalarioMensalBruto() - this.totalDescontos;
    }
}

//    public SalarioLiqResponse generateSalarioLiq(SalarioLiqRequest user) {
//       IrrfRequest irrfCalculation = new IrrfRequest();
//       irrfCalculation.setNome(user.getNome());
//       irrfCalculation.setSalarioMensalBruto(user.getSalarioMensalBruto());
//       irrfCalculation.setDependentes(user.getDependentes());
//       irrfCalculation.setPensaoAlimenticia(user.getPensaoAlimenticia());
//
//       IrrfResponse irrfResp = calcIRRF.generateIRRF(irrfCalculation);
//
//        double totalDescontos = user.getOutrosDescontos() + irrfResp.getIrrf() + irrfResp.getInss() +
//                irrfCalculation.getPensaoAlimenticia();
//
//        double salarioLiq = user.getSalarioMensalBruto() - totalDescontos;
//
//        return SalarioLiqResponse.builder()
//                .nome(irrfResp.getNome())
//                .salarioMensalBruto(irrfResp.getSalarioMensalBruto())
//                .dependentes(irrfResp.getDependentes())
//                .valorDependentes(irrfResp.getValorDependentes())
//                .pensaoAlimenticia(irrfResp.getPensaoAlimenticia())
//                .inss(irrfResp.getInss())
//                .baseDeCalculo(irrfResp.getBaseDeCalculo())
//                .porcentagemAliquota(irrfResp.getPorcentagemAliquota())
//                .parcelaADeduzir(irrfResp.getParcelaADeduzir())
//                .irrf(irrfResp.getIrrf())
//                .totalDescontos(totalDescontos)
//                .outrosDescontos(user.getOutrosDescontos())
//                .salarioLiquido(salarioLiq)
//                .build();
//    }
//
//
//}
