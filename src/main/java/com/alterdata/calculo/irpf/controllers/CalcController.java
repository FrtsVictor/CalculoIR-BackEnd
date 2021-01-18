package com.alterdata.calculo.irpf.controllers;

import com.alterdata.calculo.irpf.models.inss.UserINSSRequest;
import com.alterdata.calculo.irpf.models.inss.UserINSSResponse;
import com.alterdata.calculo.irpf.models.irpf.UserIRPFRequest;
import com.alterdata.calculo.irpf.models.irpf.UserIRPFResponse;
import com.alterdata.calculo.irpf.models.irrf.UserIRRFRequest;
import com.alterdata.calculo.irpf.models.irrf.UserIRRFResponse;
import com.alterdata.calculo.irpf.models.salario_liq.UserSalarioLiqRequest;
import com.alterdata.calculo.irpf.models.salario_liq.UserSalarioLiqResponse;
import com.alterdata.calculo.irpf.services.calculo_anual.CalcAliqAnualSimplesIRPFService;
import com.alterdata.calculo.irpf.services.calculo_anual.CalcIRSimplesService;
import com.alterdata.calculo.irpf.services.calculo_mensal.CalcINSSService;
import com.alterdata.calculo.irpf.services.calculo_mensal.CalcIRRFService;
import com.alterdata.calculo.irpf.services.calculo_mensal.CalcSalarioLiqService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/api/calculate")
@RequiredArgsConstructor
public class CalcController {

    private final CalcIRSimplesService calculoIR;
    private final CalcAliqAnualSimplesIRPFService calcAliqAnualService;
    private final CalcINSSService calcINSSService;
    private final CalcIRRFService calcIRRFService;
    private final CalcSalarioLiqService calcSalarioLiqService;

    @PostMapping("/irpf")
    public ResponseEntity<UserIRPFResponse> calcularIR(@Valid @RequestBody UserIRPFRequest usrIn)  {
        calcAliqAnualService.generateAliquota(usrIn);
        UserIRPFResponse usrOut = calculoIR.calcularIR(calcAliqAnualService);
        usrOut.setNome(usrIn.getNome());
        return ResponseEntity.ok(usrOut);
    }

    @PostMapping("/inss")
    public ResponseEntity<UserINSSResponse> calcularINSS(@Valid @RequestBody UserINSSRequest usrIn)  {
        calcINSSService.generateINSS(usrIn.getSalarioMensalBruto());
        return ResponseEntity.ok(calcINSSService.generateUserINSSOut(usrIn));
    }

    @PostMapping("/irrf")
    public ResponseEntity<UserIRRFResponse> generateIRRF(@Valid @RequestBody UserIRRFRequest usrIn)  {
        return ResponseEntity.ok(calcIRRFService.generateIRRF(usrIn));
    }

    @PostMapping("/salario-liq")
    public ResponseEntity<UserSalarioLiqResponse> generateSalarioLiq(@Valid @RequestBody UserSalarioLiqRequest usrIn)  {
        return ResponseEntity.ok(calcSalarioLiqService.generateIRRF(usrIn));
    }

}
