package com.alterdata.calculo.irpf.service.calculo_inss;

import com.alterdata.calculo.irpf.models.inss.InssRequest;
import com.alterdata.calculo.irpf.services.calculo_mensal.InssService;
import org.junit.jupiter.api.*;

import static com.alterdata.calculo.irpf.services.calculo_mensal.constants.AliquotaMensalINSS.TETO_INSS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Executando testes unitarios CalcINSS generateINSS()")
class GenerateINSSTests {

    private TestInfo testInfo;
    private TestReporter testReporter;
    private InssService inssService;
    private InssRequest userINSSRequest;

    @BeforeEach
    void initEach(TestReporter testReporter, TestInfo testInfo) {
        inssService = new InssService();
        userINSSRequest = new InssRequest();

        this.testReporter = testReporter;
        this.testInfo = testInfo;

        testReporter.publishEntry("Executando -> " +
                testInfo.getDisplayName() +
                " com a tag -> " + testInfo.getTags());
    }


    @Test
    @DisplayName("Deve retornar teto para descontos inss")
    void deve_retornar_teto_inss() {
        double salarioBruto = 10000;

        InssRequest user = new InssRequest("Test", salarioBruto);
        double inss = inssService.generateINSS(user.getSalarioMensalBruto());

        assertEquals(TETO_INSS.value(), inssService.getInss());
    }

    @Test
    @DisplayName("Nao deve retornar teto para desconto inss")
    void nao_deve_retornar_teto_inss() {
        double salarioBruto = TETO_INSS.value() - 0.1;

        InssRequest user = new InssRequest("Test", salarioBruto);
        double inss = inssService.generateINSS(user.getSalarioMensalBruto());

        assertNotEquals(TETO_INSS.value(), inssService.getInss());
    }


    @Test
    @DisplayName("Deve retornar calculo INSS")
    void deve_retornar_calculo_inss() {
        double salarioBruto = 2200;

        InssRequest user = new InssRequest("Test", salarioBruto);
        double inss = inssService.generateINSS(user.getSalarioMensalBruto());
        assertNotNull(inss);
        assertThat(inss).isNotZero();
    }

}
