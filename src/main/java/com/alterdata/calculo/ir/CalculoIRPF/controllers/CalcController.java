package com.alterdata.calculo.ir.CalculoIRPF.controllers;

import com.alterdata.calculo.ir.CalculoIRPF.modelsCVS.UserCSVIn;
import com.alterdata.calculo.ir.CalculoIRPF.modelsCVS.UserCSVOut;
import com.alterdata.calculo.ir.CalculoIRPF.services.calc.CalcBaseAliqAnualSimplesWithoutCSVOutService;
import com.alterdata.calculo.ir.CalculoIRPF.services.calc.CalcImpostoRenda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/calc")
public class CalcController {

    @Autowired
    CalcImpostoRenda calculoIR;

    @Autowired
    CalcBaseAliqAnualSimplesWithoutCSVOutService calcAliqAnualSimplesWithoutCSVOutService;

    @PostMapping
    public UserCSVOut calcularIR(@Valid @RequestBody UserCSVIn usr) {
        calcAliqAnualSimplesWithoutCSVOutService.generateDeducaoAndAliquota2(usr);
        calculoIR.calcularIR(calcAliqAnualSimplesWithoutCSVOutService);
        UserCSVOut out = calculoIR.generateCSVOut();
        return out;
    }

}
