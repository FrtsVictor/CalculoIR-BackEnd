package com.alterdata.calculo.irpf.controllers;


import com.alterdata.calculo.irpf.exceptions.UserCVSInValitaionException;
import com.alterdata.calculo.irpf.models.inss.UserINSSRequest;
import com.alterdata.calculo.irpf.models.inss.UserINSSResponse;
import com.alterdata.calculo.irpf.models.irpf.UserIRPFRequest;
import com.alterdata.calculo.irpf.models.irpf.UserIRPFResponse;
import com.alterdata.calculo.irpf.models.irrf.UserIRRFRequest;
import com.alterdata.calculo.irpf.models.irrf.UserIRRFResponse;
import com.alterdata.calculo.irpf.services.calculo_anual_irpf.CalcAliqAnualSimplesIRPFService;
import com.alterdata.calculo.irpf.services.calculo_anual_irpf.CalcIRSimplesService;
import com.alterdata.calculo.irpf.services.calculo_mensal_inss.CalcINSSService;
import com.alterdata.calculo.irpf.services.calculo_mensal_irrf.CalcIRRFService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(value  = "*")
@RequestMapping("/api/calculate")
@RequiredArgsConstructor
public class CalcController {

    private final CalcIRSimplesService calculoIR;

    private final CalcAliqAnualSimplesIRPFService calcAliqAnualService;
    private final CalcINSSService calcINSSService;
    private final CalcIRRFService calcIRRFService;

    @PostMapping
    public ResponseEntity<UserIRPFResponse> calcularIR(@Valid @RequestBody UserIRPFRequest usrIn) throws UserCVSInValitaionException {
        calcAliqAnualService.generateAliquota(usrIn);
        UserIRPFResponse usrOut = calculoIR.calcularIR(calcAliqAnualService);
        usrOut.setNome(usrIn.getNome());
        return ResponseEntity.ok(usrOut);
    }

    @PostMapping("/inss")
    public ResponseEntity<UserINSSResponse> calcularINSS(@Valid @RequestBody UserINSSRequest usrIn) throws UserCVSInValitaionException {
        calcINSSService.generateINSS(usrIn.getSalarioMensalBruto());
        return ResponseEntity.ok(calcINSSService.generateUserINSSOut(usrIn));
    }

    @PostMapping("/irrf")
    public ResponseEntity<UserIRRFResponse> generateIRRF(@Valid @RequestBody UserIRRFRequest usrIn) throws UserCVSInValitaionException {
        return ResponseEntity.ok(calcIRRFService.generateIRRF(usrIn));
   }


}
