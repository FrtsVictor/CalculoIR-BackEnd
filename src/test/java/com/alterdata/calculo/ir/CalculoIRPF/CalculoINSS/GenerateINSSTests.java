package com.alterdata.calculo.ir.CalculoIRPF.CalculoINSS;

import com.alterdata.calculo.ir.CalculoIRPF.models.UserINSSIn;
import com.alterdata.calculo.ir.CalculoIRPF.services.calcINSS.CalcINSSService;
import org.junit.jupiter.api.*;

import static com.alterdata.calculo.ir.CalculoIRPF.services.calcINSS.BaseMensalAliquotaINSS.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testing CalcINSS generateINSS method")
public class GenerateINSSTests {

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
    public void deve_retornar_teto_inss(){
        double salarioBruto = 10000;

        UserINSSIn user = new UserINSSIn("victor", salarioBruto);
        double inss = calcINSSService.generateINSS(user.getSalarioBruto());

        assertEquals(tetoINSS, calcINSSService.getInss());
    }

    @Test
    public void nao_deve_retornar_teto_inss(){
        double salarioBruto = 3000;

        UserINSSIn user = new UserINSSIn("victor", salarioBruto);
        double inss = calcINSSService.generateINSS(user.getSalarioBruto());

        assertNotEquals(tetoINSS, calcINSSService.getInss());
    }


    @Test
    public void salario1900(){
        double salarioBruto = 2200;

        UserINSSIn user = new UserINSSIn("victor", salarioBruto);
        double inss = calcINSSService.generateINSS(user.getSalarioBruto());

        System.out.println(calcINSSService.toString());
        System.out.println(inss);
    }

    @Test
    public void salario2089(){
        double salarioBruto = 3000;

        UserINSSIn user = new UserINSSIn("victor", salarioBruto);
        double inss = calcINSSService.generateINSS(user.getSalarioBruto());

        System.out.println(calcINSSService.toString());
        System.out.println(inss);
    }


    @Test
    public void salario3134(){
        double salarioBruto = 3134.41;

        UserINSSIn user = new UserINSSIn("victor", salarioBruto);
        double inss = calcINSSService.generateINSS(user.getSalarioBruto());

        System.out.println(calcINSSService.toString());
        System.out.println(inss);
    }

    @Test
    public void salario6101(){
        double salarioBruto = 15000;

        UserINSSIn user = new UserINSSIn("victor", salarioBruto);
        double inss = calcINSSService.generateINSS(user.getSalarioBruto());

        System.out.println(calcINSSService.toString());
        System.out.println(inss);
    }
}
