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
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/v1/calculate")
@RequiredArgsConstructor
public class CalculadoraController {

    private final IrpfService calculoIR;
    private final AliqAnualIrpfService calcAliqAnualService;
    private final InssService inssService;
    private final IrrfService irrfService;
    private final SalarioLiqService salarioLiqService;
    private final UserService userService;

    @PostMapping("/irpf")
    @Operation(summary = "Calcular irpf", description = "Calculo de imposto de renda anual modelo simples ")
    public ResponseEntity<IrpfResponse> calcularIrpfAnual(@Valid @RequestBody IrpfRequest usrIn) {
        calcAliqAnualService.gerarAliquota(usrIn);
        IrpfResponse irpfResp = calculoIR.calcularIrpf(calcAliqAnualService);
        irpfResp.setNome(usrIn.getNome());
        userService.validarResponse(irpfResp);
        return ResponseEntity.ok(irpfResp);
    }

    @PostMapping("/inss")
    @Operation(summary = "Calcular Inss", description = "Calculo de inss pessoa fisica")
    public ResponseEntity<InssResponse> calcularInss(@Valid @RequestBody InssRequest usrIn) {
        InssResponse inssResp = inssService.gerarInssResponse(usrIn);
        userService.validarResponse(inssResp);
        return ResponseEntity.ok(inssResp);
    }

    @PostMapping("/irrf")
    @Operation(summary = "Calcular irrf", description = "Calculo de imposto de renda retido na fonte pessoa fisica")
    public ResponseEntity<IrrfResponse> calcularIrrf(@Valid @RequestBody IrrfRequest usrIn) {
        IrrfResponse irrfResp = irrfService.calcularIrrf(usrIn);
        userService.validarResponse(irrfResp);
        return ResponseEntity.ok(irrfResp);
    }

    @PostMapping("/salario-liq")
    @Operation(summary = "Calcular salario liquido", description = "Calculo salario liquido para pessoa fisica")
    public ResponseEntity<SalarioLiqResponse> calcularSalarioLiq(@Valid @RequestBody SalarioLiqRequest usrIn) {
        SalarioLiqResponse salarioLiqResp = salarioLiqService.gerarSalarioLiqResponse(usrIn);
        userService.validarResponse(salarioLiqResp);
        return ResponseEntity.ok(salarioLiqResp);
    }
}
