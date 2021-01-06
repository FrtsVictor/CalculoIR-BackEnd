package com.alterdata.calculo.ir.CalculoIRPF.controllers;

import com.alterdata.calculo.ir.CalculoIRPF.exceptions.UserCVSInValitaionException;
import com.alterdata.calculo.ir.CalculoIRPF.modelsCVS.UserCSVIn;
import com.alterdata.calculo.ir.CalculoIRPF.modelsCVS.UserCSVOut;
import com.alterdata.calculo.ir.CalculoIRPF.services.calcIRPF.AliqAnualSimplesService;
import com.alterdata.calculo.ir.CalculoIRPF.services.calcIRPF.CalcIRSimplesServices;
import com.alterdata.calculo.ir.CalculoIRPF.services.user.DefaultUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(value  = "*")
@RequestMapping("/api/calculate")
public class CalcController {

    @Autowired
    CalcIRSimplesServices calculoIR;

    @Autowired
    DefaultUserService userService;

    @Autowired
    AliqAnualSimplesService calcAliqAnualSimplesWithoutCSVOutService;

    @PostMapping
    public UserCSVOut calcularIR(@Valid @RequestBody UserCSVIn usrIn) throws UserCVSInValitaionException, UserCVSInValitaionException {
        userService.validateUserCsvIn(usrIn);

        calcAliqAnualSimplesWithoutCSVOutService.generateDeducaoAndAliquota2(usrIn);

        calculoIR.calcularIR(calcAliqAnualSimplesWithoutCSVOutService);

        UserCSVOut usrOut = calculoIR.generateCSVOut();
        userService.CopyNameCpfNasc(usrIn,usrOut);

        return usrOut;
    }

}
