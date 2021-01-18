package com.alterdata.calculo.irpf.services.calculo_mensal;

import com.alterdata.calculo.irpf.models.irrf.UserIRRFRequest;
import com.alterdata.calculo.irpf.models.irrf.UserIRRFResponse;
import com.alterdata.calculo.irpf.models.salario_liq.UserSalarioLiqRequest;
import com.alterdata.calculo.irpf.models.salario_liq.UserSalarioLiqResponse;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class CalcSalarioLiqService {

    @Autowired
    CalcIRRFService calcIRRF;

    public UserSalarioLiqResponse generateSalarioLiq(UserSalarioLiqRequest user) {
        UserIRRFRequest irrfReq = new UserIRRFRequest();
        irrfReq.setNome(user.getNome());
        irrfReq.setSalarioMensalBruto(user.getSalarioMensalBruto());
        irrfReq.setDependentes(user.getDependentes());
        irrfReq.setPensaoAlimenticia(user.getPensaoAlimenticia());

        UserIRRFResponse irrfResp = calcIRRF.generateIRRF(irrfReq);

        double total = user.getOutrosDescontos() + irrfResp.getIRRF() + irrfResp.getINSS() +
                irrfReq.getPensaoAlimenticia();
        double salarioLiq = user.getSalarioMensalBruto() - total;

        return UserSalarioLiqResponse.builder()
                .nome(irrfResp.getNome())
                .salarioMensalBruto(irrfResp.getSalarioMensalBruto())
                .dependentes(irrfResp.getDependentes())
                .valorDependentes(irrfResp.getValorDependentes())
                .pensaoAlimenticia(irrfResp.getPensaoAlimenticia())
                .inss(irrfResp.getINSS())
                .baseCalculo(irrfResp.getBaseCalculo())
                .porcentagemAliquota(irrfResp.getPorcentagemAliquota())
                .parcelaADeduzir(irrfResp.getParcelaADeduzir())
                .irrf(irrfResp.getIRRF())
                .valorTotalDescontos(total)
                .outrosDescontos(user.getOutrosDescontos())
                .salarioLiquido(salarioLiq)
                .build();
    }


}
