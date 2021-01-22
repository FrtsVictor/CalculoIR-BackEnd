package com.alterdata.calculo.irpf.controller;

import com.alterdata.calculo.irpf.controllers.CalculadoraController;
import com.alterdata.calculo.irpf.models.irrf.IrrfRequest;
import com.alterdata.calculo.irpf.models.irrf.IrrfResponse;
import com.alterdata.calculo.irpf.services.calculo_mensal.IrrfService;
import com.alterdata.calculo.irpf.services.calculo_mensal.SalarioLiqService;
import com.alterdata.calculo.irpf.util.CriarIrrfRequest;
import com.alterdata.calculo.irpf.util.CriarIrrfResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

@DisplayName("Executando testes unitarios CalculadoraController")
class CalculadoraControllerTests {

//    @InjectMocks
//    private CalculadoraController calculadoraController;
//
//    @Mock
//    private IrrfService irrfServiceMock;
//    @Mock
//    private SalarioLiqService salarioLiqServiceMock;
//
//    @Test
//    @DisplayName("Calcular irrf deve receber IrrfRequest e retornar IrrfResponse")
//    void calcula_irrf_deve_retornar_irrfResponse() {
//        BDDMockito.when(irrfServiceMock.gerarIrrfResponse(ArgumentMatchers.any(IrrfRequest.class)))
//                .thenReturn(CriarIrrfResponse.createValidIRPFResponse());
//
//        IrrfRequest irrfReq = CriarIrrfRequest.createValidIrrfRequest();
//
//        IrrfResponse irrfResp = calculadoraController.calcularIrrf(irrfReq).getBody();
//
//        Assertions.assertThat(irrfResp).isNotNull();
//
//    }


}
