package com.alterdata.calculo.irpf.controllers;

import com.alterdata.calculo.irpf.models.inss.InssRequest;
import com.alterdata.calculo.irpf.models.inss.InssResponse;
import com.alterdata.calculo.irpf.models.irpf.IrpfRequest;
import com.alterdata.calculo.irpf.models.irpf.IrpfResponse;
import com.alterdata.calculo.irpf.models.irrf.IrrfRequest;
import com.alterdata.calculo.irpf.models.irrf.IrrfResponse;
import com.alterdata.calculo.irpf.models.salario_liq.SalarioLiqRequest;
import com.alterdata.calculo.irpf.models.salario_liq.SalarioLiqResponse;
import com.alterdata.calculo.irpf.services.calculo_anual.AliqAnualIrpfService;
import com.alterdata.calculo.irpf.services.calculo_anual.IrpfService;
import com.alterdata.calculo.irpf.services.calculo_mensal.InssService;
import com.alterdata.calculo.irpf.services.calculo_mensal.IrrfService;
import com.alterdata.calculo.irpf.services.calculo_mensal.SalarioLiqService;
import com.alterdata.calculo.irpf.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.Validator;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/v1/calculate")
@RequiredArgsConstructor
public class CalculateController {

    private final IrpfService calculoIR;
    private final AliqAnualIrpfService calcAliqAnualService;
    private final InssService inssService;
    private final IrrfService irrfService;
    private final SalarioLiqService salarioLiqService;
    private final UserService userService;

    @PostMapping("/irpf")
    public ResponseEntity<IrpfResponse> calcularIR(@Valid @RequestBody IrpfRequest usrIn)  {
        calcAliqAnualService.generateAliquota(usrIn);
        IrpfResponse irpfResp = calculoIR.calcularIR(calcAliqAnualService);
        irpfResp.setNome(usrIn.getNome());
        userService.validateResponse(irpfResp);
        return ResponseEntity.ok(irpfResp);
    }

    @PostMapping("/inss")
    public ResponseEntity<InssResponse> calcularINSS(@Valid @RequestBody InssRequest usrIn)  {
        InssResponse inssResp = inssService.generateInssResponse(usrIn);
        userService.validateResponse(inssResp);
        return ResponseEntity.ok(inssResp);
    }

    @PostMapping("/irrf")
    public ResponseEntity<IrrfResponse> generateIRRF(@Valid @RequestBody IrrfRequest usrIn)  {
        IrrfResponse irrfResp = irrfService.generateIRRF(usrIn);
        userService.validateResponse(irrfResp);
        return ResponseEntity.ok(irrfResp);
    }

    @PostMapping("/salario-liq")
    public ResponseEntity<SalarioLiqResponse> generateSalarioLiq(@Valid @RequestBody SalarioLiqRequest usrIn)  {
        SalarioLiqResponse salarioLiqResp = salarioLiqService.generateSalarioLiqResponse(usrIn);
        userService.validateResponse(salarioLiqResp);
        return ResponseEntity.ok(salarioLiqResp);
    }
}
