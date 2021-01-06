package com.alterdata.calculo.ir.CalculoIRPF.services.user;

import com.alterdata.calculo.ir.CalculoIRPF.exceptions.UserCVSInValitaionException;
import com.alterdata.calculo.ir.CalculoIRPF.modelsCVS.UserCSVIn;
import com.alterdata.calculo.ir.CalculoIRPF.modelsCVS.UserCSVOut;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService {

    public void validateUserCsvIn(UserCSVIn usrIn) throws UserCVSInValitaionException {
        if (usrIn.getNome().isBlank() || usrIn.getNome().length() < 3){
            throw new UserCVSInValitaionException("nome", "Nome invalido deve possuir min 3 caracteres");
        }

        if (usrIn.getCpf().isBlank() || usrIn.getCpf().length() < 11){
            throw new UserCVSInValitaionException("cpf", "Cpf invalido deve possuir min 11 caracteres");
        }

    }

    public void CopyNameCpfNasc(UserCSVIn usrIn, UserCSVOut usrOut) {
        usrOut.setNome(usrIn.getNome());
        usrOut.setCpf(usrIn.getCpf());
    }


}
