package com.alterdata.calculo.ir.CalculoIRPF.services.user;

import com.alterdata.calculo.ir.CalculoIRPF.exceptions.UserCVSInValitaionException;
import com.alterdata.calculo.ir.CalculoIRPF.modelsCVS.CalcUserIn;
import com.alterdata.calculo.ir.CalculoIRPF.modelsCVS.CalcUserOut;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService {

    public void validateUserCsvIn(CalcUserIn usrIn) throws UserCVSInValitaionException {
        if (usrIn.getNome().isBlank() || usrIn.getNome().length() < 3){
            throw new UserCVSInValitaionException("nome", "Nome invalido deve possuir min 3 caracteres");
        }

        if (usrIn.getCpf().isBlank() || usrIn.getCpf().length() < 11){
            throw new UserCVSInValitaionException("cpf", "Cpf invalido deve possuir min 11 caracteres");
        }

        if (usrIn.getRendimentoAnualBruto() < 1){
            throw new UserCVSInValitaionException("Rendimento Bruto", "Rendimento anual bruto invalido");
        }

    }

    public void CopyNameCpfNasc(CalcUserIn usrIn, CalcUserOut usrOut) {
        usrOut.setNome(usrIn.getNome());
        usrOut.setCpf(usrIn.getCpf());
    }


}
