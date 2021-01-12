package com.alterdata.calculo.ir.CalculoIRPF.controllers;

import com.alterdata.calculo.ir.CalculoIRPF.exceptions.UserCVSInValitaionException;
import com.alterdata.calculo.ir.CalculoIRPF.models.UserINSSIn;
import com.alterdata.calculo.ir.CalculoIRPF.models.UserINSSOut;
import com.alterdata.calculo.ir.CalculoIRPF.models.UserIRRFIn;
import com.alterdata.calculo.ir.CalculoIRPF.models.UserIRRFOut;
import com.alterdata.calculo.ir.CalculoIRPF.modelsCVS.CalcUserIn;
import com.alterdata.calculo.ir.CalculoIRPF.modelsCVS.CalcUserOut;
import com.alterdata.calculo.ir.CalculoIRPF.services.calcAnualIRPF.CalcAliqAnualSimplesIRPFService;
import com.alterdata.calculo.ir.CalculoIRPF.services.calcAnualIRPF.CalcIRSimplesService;
import com.alterdata.calculo.ir.CalculoIRPF.services.calcINSS.CalcINSSService;
import com.alterdata.calculo.ir.CalculoIRPF.services.calcIRRF.CalcIRRFService;
import com.alterdata.calculo.ir.CalculoIRPF.services.user.DefaultUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(value  = "*")
@RequestMapping("/api/calculate")
public class CalcController {

    @Autowired
    CalcIRSimplesService calculoIR;

    @Autowired
    DefaultUserService userService;

    @Autowired
    CalcAliqAnualSimplesIRPFService calcAliqAnualService;

    @Autowired
    CalcINSSService calcINSSService;

    @Autowired
    CalcIRRFService calcIRRFService;

    @PostMapping
    public CalcUserOut calcularIR(@Valid @RequestBody CalcUserIn usrIn) throws UserCVSInValitaionException {
        userService.validateUser(usrIn);
        calcAliqAnualService.generateAliquota(usrIn);
        calculoIR.calcularIR(calcAliqAnualService);
        CalcUserOut usrOut = calculoIR.generateCSVOut();
        usrOut.setNome(usrIn.getNome());
        return usrOut;
    }


    @PostMapping("/inss")
    public UserINSSOut calcularINSS(@Valid @RequestBody UserINSSIn usrIn) throws UserCVSInValitaionException {
        userService.validateUserINSS(usrIn);
        calcINSSService.generateINSS(usrIn.getSalarioBruto());

        return calcINSSService.generateUserINSSOut(usrIn);
    }

    @PostMapping("/irrf")
    public UserIRRFOut generateIRRF(@Valid @RequestBody UserIRRFIn usrIn) throws UserCVSInValitaionException {
        userService.validateUserIRRF(usrIn);

        return calcIRRFService.generateIRRF(usrIn);
    }

}
