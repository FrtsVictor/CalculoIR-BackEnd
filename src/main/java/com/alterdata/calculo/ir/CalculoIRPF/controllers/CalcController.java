package com.alterdata.calculo.ir.CalculoIRPF.controllers;

import com.alterdata.calculo.ir.CalculoIRPF.entity.User;
import com.alterdata.calculo.ir.CalculoIRPF.exceptions.UserCVSInValitaionException;
import com.alterdata.calculo.ir.CalculoIRPF.models.UserINSSIn;
import com.alterdata.calculo.ir.CalculoIRPF.models.UserINSSOut;
import com.alterdata.calculo.ir.CalculoIRPF.models.UserIRRFIn;
import com.alterdata.calculo.ir.CalculoIRPF.models.UserIRRFOut;
import com.alterdata.calculo.ir.CalculoIRPF.modelsCVS.CalcUserIn;
import com.alterdata.calculo.ir.CalculoIRPF.modelsCVS.CalcUserOut;
import com.alterdata.calculo.ir.CalculoIRPF.services.calcAnualIRPF.CalcAliqAnualSimplesIRPFService;
import com.alterdata.calculo.ir.CalculoIRPF.services.calcAnualIRPF.CalcIRSimplesService;
import com.alterdata.calculo.ir.CalculoIRPF.services.calcINSS.CalcINSS;
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
    CalcINSS calcINSS;

    @Autowired
    CalcIRRFService calcIRRFService;

    @PostMapping
    public CalcUserOut calcularIR(@Valid @RequestBody CalcUserIn usrIn) throws UserCVSInValitaionException {
        userService.validateUserCsvIn(usrIn);

        calcAliqAnualService.generateDeducaoAndAliquota2(usrIn);

        calculoIR.calcularIR(calcAliqAnualService);

        CalcUserOut usrOut = calculoIR.generateCSVOut();
        userService.CopyNameCpfNasc(usrIn,usrOut);

        return usrOut;
    }


    @PostMapping("/inss")
    public UserINSSOut calcularINSS(@Valid @RequestBody UserINSSIn usrIn) throws UserCVSInValitaionException {
        calcINSS.generateINSS(usrIn.getSalarioBruto());

        return UserINSSOut.builder()
                .salarioBruto(usrIn.getSalarioBruto())
                .nome(usrIn.getNome())
                .parcelaADeduzir(calcINSS.getParcelaADeduzir())
                .porcentagemAliquota(calcINSS.getPorcentagemAliquota())
                .inss(calcINSS.getInss())
                .deducaoAliquota(calcINSS.getDeducaoAliquota())
                .build();
    }

    @PostMapping("/irrf")
    public UserIRRFOut generateIRRF(@Valid @RequestBody UserIRRFIn usrIn) throws UserCVSInValitaionException {
        calcIRRFService.generateIRRF(usrIn);

        return UserIRRFOut.builder()
                .nome(usrIn.getNome())
                .salarioBruto(usrIn.getSalarioBruto())
                .INSS(calcINSS.generateINSS(usrIn.getSalarioBruto()))
                .dependentes(usrIn.getDependentes())
                .valorDependentes(calcIRRFService.getValorDependentes())
                .pensaoAlimenticia(usrIn.getPensaoAlimenticia())
                .valorTotalDescontos(calcIRRFService.getValorTotalDescontos())
                .baseCalculo(calcIRRFService.getBaseCalculo())
                .porcentagemAliquota(calcIRRFService.getPorcentagemAliquota())
                .parcelaADeduzir(calcIRRFService.getParcelaADeduzir())
                .IRRF(calcIRRFService.getIRRF())
                .build();
    }



}
