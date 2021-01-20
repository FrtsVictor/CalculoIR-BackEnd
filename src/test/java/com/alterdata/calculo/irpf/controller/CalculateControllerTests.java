package com.alterdata.calculo.irpf.controller;

import com.alterdata.calculo.irpf.controllers.CalculateController;
import com.alterdata.calculo.irpf.models.inss.InssRequest;
import com.alterdata.calculo.irpf.models.irrf.IrrfRequest;
import com.alterdata.calculo.irpf.models.irrf.IrrfResponse;
import com.alterdata.calculo.irpf.models.salario_liq.SalarioLiqRequest;
import com.alterdata.calculo.irpf.services.calculo_anual.AliqAnualIrpfService;
import com.alterdata.calculo.irpf.services.calculo_anual.IrpfService;
import com.alterdata.calculo.irpf.services.calculo_mensal.InssService;
import com.alterdata.calculo.irpf.services.calculo_mensal.IrrfService;
import com.alterdata.calculo.irpf.services.calculo_mensal.SalarioLiqService;
import com.alterdata.calculo.irpf.util.InssResponseCreator;
import com.alterdata.calculo.irpf.util.IrrfRequestCreator;
import com.alterdata.calculo.irpf.util.IrrfResponseCreator;
import com.alterdata.calculo.irpf.util.SalarioLiqResponseCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

@DisplayName("Executando testes unitarios CalculateController")
public class CalculateControllerTests {

    @InjectMocks
    private CalculateController calculateController;

    @Mock
    private IrpfService irpfServiceMock;
    @Mock
    private AliqAnualIrpfService aliqAnualServiceMock;
    @Mock
    private InssService inssServiceMock;
    @Mock
    private IrrfService irrfServiceMock;
    @Mock
    private SalarioLiqService salarioLiqServiceMock;

    @BeforeEach
    void setup() {

        Mockito.when(inssServiceMock.generateInssResponse(ArgumentMatchers.any(InssRequest.class)))
                .thenReturn(InssResponseCreator.createValidINSSResponse());

        Mockito.when(irrfServiceMock.generateIrrfResponse(ArgumentMatchers.any(IrrfRequest.class)))
                .thenReturn(IrrfResponseCreator.createValidIRPFResponse());

        Mockito.when(salarioLiqServiceMock.generateSalarioLiqResponse(ArgumentMatchers.any(SalarioLiqRequest.class)))
                .thenReturn(SalarioLiqResponseCreator.createValidSalarioLiqResponse());
    }

    @Test
    @DisplayName("Calcular irrf deve receber IrrfRequest e retornar IrrfResponse")
    void calcula_irrf_deve_retornar_irrfResponse() {
        IrrfRequest irrfReq = IrrfRequestCreator.createValidIrrfRequest();

        IrrfResponse irrfResp = calculateController.calcularIrrf(irrfReq).getBody();

        Assertions.assertThat(irrfResp).isNotNull();

    }


}
