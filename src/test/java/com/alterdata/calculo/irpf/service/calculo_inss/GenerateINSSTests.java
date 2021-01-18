package com.alterdata.calculo.irpf.service.calculo_inss;

import com.alterdata.calculo.irpf.models.inss.UserINSSRequest;
import com.alterdata.calculo.irpf.services.calculo_mensal.CalcINSSService;
import org.junit.jupiter.api.*;

import static com.alterdata.calculo.irpf.services.calculo_mensal.constants.AliquotaMensalINSS.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Executando testes unitarios CalcINSS generateINSS()")
class GenerateINSSTests {

    private TestInfo testInfo;
    private TestReporter testReporter;
    private CalcINSSService calcINSSService;
    private UserINSSRequest userINSSRequest;

    @BeforeEach
    void initEach(TestReporter testReporter, TestInfo testInfo) {
        calcINSSService = new CalcINSSService();
        userINSSRequest = new UserINSSRequest();

        this.testReporter = testReporter;
        this.testInfo = testInfo;

        testReporter.publishEntry("Executando -> " +
                testInfo.getDisplayName() +
                " com a tag -> " + testInfo.getTags());
    }


    @Test
    @DisplayName("Deve retornar teto para descontos inss")
    void deve_retornar_teto_inss(){
        double salarioBruto = 10000;

        UserINSSRequest user = new UserINSSRequest("Test", salarioBruto);
        double inss = calcINSSService.generateINSS(user.getSalarioMensalBruto());

        assertEquals(TETO_INSS.value(), calcINSSService.getInss());
    }

    @Test
    @DisplayName("Nao deve retornar teto para desconto inss")
    void nao_deve_retornar_teto_inss(){
        double salarioBruto = TETO_INSS.value() - 0.1;

        UserINSSRequest user = new UserINSSRequest("Test", salarioBruto);
        double inss = calcINSSService.generateINSS(user.getSalarioMensalBruto());

        assertNotEquals(TETO_INSS.value(), calcINSSService.getInss());
    }


    @Test
    @DisplayName("Deve retornar calculo INSS")
    void deve_retornar_calculo_inss(){
        double salarioBruto = 2200;

        UserINSSRequest user = new UserINSSRequest("Test", salarioBruto);
        double inss = calcINSSService.generateINSS(user.getSalarioMensalBruto());
        assertNotNull(inss);
        assertThat(inss).isNotZero();
    }

}
