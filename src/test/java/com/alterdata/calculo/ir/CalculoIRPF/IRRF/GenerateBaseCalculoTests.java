package com.alterdata.calculo.ir.CalculoIRPF.IRRF;

import com.alterdata.calculo.ir.CalculoIRPF.models.UserINSSIn;
import com.alterdata.calculo.ir.CalculoIRPF.models.UserIRRFIn;
import com.alterdata.calculo.ir.CalculoIRPF.services.calcINSS.CalcINSSService;
import com.alterdata.calculo.ir.CalculoIRPF.services.calcIRRF.CalcIRRFService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.alterdata.calculo.ir.CalculoIRPF.services.calcAnualIRPF.BaseAnualIRPF.base2_parcelaADeduzir;
import static org.mockito.Mockito.mock;
@SpringBootTest
public class GenerateBaseCalculoTests {


    @Autowired
    private CalcIRRFService calcIRRFService;

    @MockBean
    CalcINSSService calcINSSService;


    @Test
    public void deve_retornar_base_de_calculo() {
        UserIRRFIn userIn = new UserIRRFIn("Victor", 5000, 0,0);

        Mockito.when(calcIRRFService.generateBaseCalculo(userIn)).thenReturn(calcIRRFService.getBaseCalculo());

//        Assertions.assertNotEquals(calcIRRFService.getBaseCalculo(),  0);
    }


}
