package com.alterdata.calculo.irpf.models.irpf;

import com.alterdata.calculo.irpf.models.abtract_default_users.SalarioAnualRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IrpfRequest extends SalarioAnualRequest {

    public IrpfRequest(String nome, double rendimentoAnualBruto) {
        super(nome, rendimentoAnualBruto);
    }

    public boolean validateRendimentoAnualBruto(IrpfRequest usrIn) {
        return usrIn.getRendimentoAnualBruto() < 1000;
    }

}
