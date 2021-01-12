package com.alterdata.calculo.ir.CalculoIRPF.services.user;

import com.alterdata.calculo.ir.CalculoIRPF.exceptions.UserCVSInValitaionException;
import com.alterdata.calculo.ir.CalculoIRPF.models.UserINSSIn;
import com.alterdata.calculo.ir.CalculoIRPF.models.UserIRRFIn;
import com.alterdata.calculo.ir.CalculoIRPF.modelsCVS.CalcUserIn;

import org.springframework.stereotype.Service;

@Service
public class DefaultUserService {

    public void validateUser(CalcUserIn usrIn) throws UserCVSInValitaionException {
        if (usrIn.getNome().isBlank() || usrIn.getNome().length() < 3) {
            throw new UserCVSInValitaionException("nome", "Nome invalido deve possuir min 3 caracteres");
        }

        if (usrIn.getRendimentoAnualBruto() < 1) {
            throw new UserCVSInValitaionException("Rendimento Bruto", "Rendimento anual bruto invalido");
        }
    }

    public void validateUserINSS(UserINSSIn usrIn) throws UserCVSInValitaionException {
        if (usrIn.getNome().isBlank() || usrIn.getNome().length() < 3) {
            throw new UserCVSInValitaionException("nome", "Nome invalido deve possuir min 3 caracteres");
        }

        if (usrIn.getSalarioBruto() < 1) {
            throw new UserCVSInValitaionException("Salario Bruto", "Salario mensal bruto invalido");
        }
    }

    public void validateUserIRRF(UserIRRFIn usrIn) throws UserCVSInValitaionException {
        if (usrIn.getNome().isBlank() || usrIn.getNome().length() < 3) {
            throw new UserCVSInValitaionException("nome", "Nome invalido deve possuir min 3 caracteres");
        }

        if (usrIn.getSalarioBruto() < 1) {
            throw new UserCVSInValitaionException("Salario Bruto", "Salario mensal bruto invalido");
        }
    }

    }