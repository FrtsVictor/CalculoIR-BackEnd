package com.alterdata.calculo.irpf.models.inss;

import com.alterdata.calculo.irpf.models.abtract_default_users.SalarioMensalRequest;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class InssRequest extends SalarioMensalRequest {

    public InssRequest(String nome, double salarioBruto) {
        super(nome, salarioBruto);
    }

}
