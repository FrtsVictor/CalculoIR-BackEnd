package com.alterdata.calculo.irpf.services.user;

import com.alterdata.calculo.irpf.exceptions.UserCVSInValitaionException;
import com.alterdata.calculo.irpf.models.UserIRRFIn;
import com.alterdata.calculo.irpf.models.UserInSalMensal;
import com.alterdata.calculo.irpf.models.UserIRPFIn;

import org.springframework.stereotype.Service;

@Service
public class DefaultUserService {

    public void validateUserRFPF(UserIRPFIn usrIn) throws UserCVSInValitaionException {
        UserIRPFIn userIRPFIn = new UserIRPFIn();
        if (userIRPFIn.validateNome(usrIn) == false ) {
            throw new UserCVSInValitaionException("nome", "Nome invalido deve possuir min 3 caracteres");
        }

        if (userIRPFIn.validateRendimentoAnualBruto(usrIn) == false) {
            throw new UserCVSInValitaionException("Rendimento Bruto", "Rendimento anual bruto invalido");
        }
    }

    public void validateUser(UserInSalMensal usrIn) throws UserCVSInValitaionException {
        UserIRRFIn userIRRFIn = new UserIRRFIn();
        if (userIRRFIn.validateNome(usrIn) == false) {
            throw new UserCVSInValitaionException("nome", "Nome invalido deve possuir min 3 caracteres");
        }

        if (userIRRFIn.validateSalarioBruto(usrIn) == false) {
            throw new UserCVSInValitaionException("Salario Bruto", "Salario mensal bruto invalido");
        }
    }


    }