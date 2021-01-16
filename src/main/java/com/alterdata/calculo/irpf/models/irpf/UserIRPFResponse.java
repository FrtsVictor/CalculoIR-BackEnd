package com.alterdata.calculo.irpf.models.irpf;

import com.alterdata.calculo.irpf.models.default_users.UserSalarioAnual;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class UserIRPFResponse extends UserSalarioAnual {

    private double rendimentoAnualBruto;
    private double porcentagemAliquota;
    private double baseDeCalculo;
    private double deducaoSimplificada;
    private double impostoInicial;
    private double parcelaDedutivel;
    private double impostoRenda;

}
