package com.alterdata.calculo.irpf.CalculoINSS;

import com.alterdata.calculo.irpf.models.UserINSSIn;
import com.alterdata.calculo.irpf.services.calcINSS.CalcINSSService;
import org.junit.jupiter.api.*;

import static com.alterdata.calculo.irpf.services.calcINSS.BaseMensalAliquotaINSS.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Executando testes CalcINSS generateINSS ")
class GenerateINSSTests {

    private TestInfo testInfo;
    private TestReporter testReporter;
    private CalcINSSService calcINSSService;
    private UserINSSIn userINSSIn;

    @BeforeEach
    void initEach(TestReporter testReporter, TestInfo testInfo) {
        calcINSSService = new CalcINSSService();
        userINSSIn = new UserINSSIn();

        this.testReporter = testReporter;
        this.testInfo = testInfo;

        testReporter.publishEntry("Executando -> " +
                testInfo.getDisplayName() +
                " com a tag -> " + testInfo.getTags());
    }


    @Test
    @DisplayName("Deve retornar teto inss")
    void deve_retornar_teto_inss(){
        double salarioBruto = 10000;

        UserINSSIn user = new UserINSSIn("victor", salarioBruto);
        double inss = calcINSSService.generateINSS(user.getSalarioMensalBruto());

        assertEquals(tetoINSS, calcINSSService.getInss());
    }

    @Test
    @DisplayName("Nao deve retornar teto inss")
    void nao_deve_retornar_teto_inss(){
        double salarioBruto = tetoINSS - 0.1;

        UserINSSIn user = new UserINSSIn("victor", salarioBruto);
        double inss = calcINSSService.generateINSS(user.getSalarioMensalBruto());

        assertNotEquals(tetoINSS, calcINSSService.getInss());
    }


    @Test
    @DisplayName("Deve retornar calculo INSS")
    void deve_retornar_calculo_inss(){
        double salarioBruto = 2200;

        UserINSSIn user = new UserINSSIn("victor", salarioBruto);
        double inss = calcINSSService.generateINSS(user.getSalarioMensalBruto());
        assertNotNull(inss);
        assertThat(inss).isNotZero();
    }

}
