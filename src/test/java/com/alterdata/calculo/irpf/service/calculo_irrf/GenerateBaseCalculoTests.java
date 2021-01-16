package com.alterdata.calculo.irpf.service.calculo_irrf;

import com.alterdata.calculo.irpf.models.irrf.UserIRRFRequest;
import com.alterdata.calculo.irpf.services.calculo_mensal_inss.CalcINSSService;
import com.alterdata.calculo.irpf.services.calculo_mensal_irrf.CalcIRRFService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Executando testes para generateBaseCalculo")
@ExtendWith(MockitoExtension.class)
public class GenerateBaseCalculoTests {

    @Mock
    CalcINSSService calcINSSService;

    @InjectMocks
    CalcIRRFService calcIRRFService;

    @Test
    @DisplayName("Nao deve retornar baseCalculo nula para valores validos")
    public void nao_deve_retornar_nulo_para_base_de_calculo_com_valores_validos() {
        UserIRRFRequest userIn = new UserIRRFRequest("Victor", 5000, 0,0);
        calcIRRFService.generateBaseCalculo(userIn);
        Assertions.assertNotNull(calcIRRFService.getBaseCalculo());
    }

    @Test
    @DisplayName("Nao deve retornar baseCalculo zero para valores validos")
    public void nao_deve_retornar_zero_para_baseCalculo_com_valores_validos() {
        UserIRRFRequest userIn = new UserIRRFRequest("Victor", 5000, 0,0);
        calcIRRFService.generateBaseCalculo(userIn);
        assertThat(calcIRRFService.getBaseCalculo()).isNotEqualTo(0);
    }

    @Test
    @DisplayName("Deve retornar baseCalculo correta para valores validos")
    public void deve_retornar_baseCalculo_com_valores_validos() {
        UserIRRFRequest userIn = new UserIRRFRequest("Victor", 5000, 0,0);
        calcIRRFService.generateBaseCalculo(userIn);
        assertThat(calcIRRFService.getBaseCalculo()).isNotZero();
        Assertions.assertNotNull(calcIRRFService.getBaseCalculo());
    }

    @Test
    @DisplayName("Deve retornar baseCalculo zero para valores 0")
    public void deve_retornar_null_para_baseCalculo_com_valores_nulos() {
        UserIRRFRequest userIn = new UserIRRFRequest("Victor", 0, 0,0);
        calcIRRFService.generateBaseCalculo(userIn);
        Assertions.assertEquals(calcIRRFService.getBaseCalculo(), 0.0);
    }



}

