package com.alterdata.calculo.irpf.IRRF;

import com.alterdata.calculo.irpf.models.UserIRRFIn;
import com.alterdata.calculo.irpf.services.calcINSS.CalcINSSService;
import com.alterdata.calculo.irpf.services.calcIRRF.CalcIRRFService;
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
    @DisplayName("Deve retornar baseCalculo para salario valido")
    public void deve_retornar_base_de_calculo() {
        UserIRRFIn userIn = new UserIRRFIn("Victor", 5000, 0,0);
        calcIRRFService.generateBaseCalculo(userIn);
        calcIRRFService.generateFaixaSalarial();
        Assertions.assertNotNull(calcIRRFService.getBaseCalculo());
    }

    @Test
    @DisplayName("Deve retornar 0 para salario invalido")
    public void deve_retornar_zero_para_baseCalculo() {
        UserIRRFIn userIn = new UserIRRFIn("Victor", 0, 0,0);
        calcIRRFService.generateBaseCalculo(userIn);
        calcIRRFService.generateFaixaSalarial();
        assertThat(calcIRRFService.getBaseCalculo()).isEqualTo(0);
    }


}
