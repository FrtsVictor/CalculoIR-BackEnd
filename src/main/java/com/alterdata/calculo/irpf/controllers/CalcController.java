package com.alterdata.calculo.irpf.controllers;

import com.alterdata.calculo.irpf.config.JwtTokenUtil;
import com.alterdata.calculo.irpf.exceptions.UserCVSInValitaionException;
import com.alterdata.calculo.irpf.models.*;
import com.alterdata.calculo.irpf.services.calcAnualIRPF.CalcAliqAnualSimplesIRPFService;
import com.alterdata.calculo.irpf.services.calcAnualIRPF.CalcIRSimplesService;
import com.alterdata.calculo.irpf.services.calcINSS.CalcINSSService;
import com.alterdata.calculo.irpf.services.calcIRRF.CalcIRRFService;
import com.alterdata.calculo.irpf.services.user.DefaultUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(value  = "*")
@RequestMapping("/api/calculate")
@RequiredArgsConstructor
public class CalcController {

    private final CalcIRSimplesService calculoIR;
    private final DefaultUserService userService;
    private final CalcAliqAnualSimplesIRPFService calcAliqAnualService;
    private final CalcINSSService calcINSSService;
    private final CalcIRRFService calcIRRFService;
    final private JwtTokenUtil jwtTokenUtil;

    @PostMapping
    public UserIRPFOut calcularIR(@Valid @RequestBody UserIRPFIn usrIn) throws UserCVSInValitaionException {
        userService.validateUserRFPF(usrIn);
        calcAliqAnualService.generateAliquota(usrIn);
        calculoIR.calcularIR(calcAliqAnualService);

        UserIRPFOut usrOut = calculoIR.generateCSVOut();
        usrOut.setNome(usrIn.getNome());
        return usrOut;
    }

    @PostMapping("/inss")
    public UserINSSOut calcularINSS(@Valid @RequestBody UserINSSIn usrIn) throws UserCVSInValitaionException {
        userService.validateUser(usrIn);
        calcINSSService.generateINSS(usrIn.getSalarioMensalBruto());
        return calcINSSService.generateUserINSSOut(usrIn);
    }

    @PostMapping("/irrf")
    public UserIRRFOut generateIRRF(@Valid @RequestBody UserIRRFIn usrIn) throws UserCVSInValitaionException {
        userService.validateUser(usrIn);
        return calcIRRFService.generateIRRF(usrIn);
    }

}
