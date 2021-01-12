package com.alterdata.calculo.ir.CalculoIRPF.CalculoINSS;

import com.alterdata.calculo.ir.CalculoIRPF.models.UserINSSIn;
import com.alterdata.calculo.ir.CalculoIRPF.services.calcINSS.CalcINSSService;
import org.junit.jupiter.api.*;

import static com.alterdata.calculo.ir.CalculoIRPF.services.calcINSS.BaseMensalAliquotaINSS.*;

@DisplayName("Testing CalcINSS generateFaixaSalarial method")
public class GenerateFaixaSalarialTests {

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

    @Nested
    @DisplayName("Faixa 1")
    class Faixa1 {

        @Test
        void deve_retornar_Faixa1_para_valor_final() {
            double salarioBrutoMensal = faixa1_valorFinal;

            UserINSSIn userliq = new UserINSSIn("Victor", salarioBrutoMensal);
            calcINSSService.generateFaixaSalarial(userliq.getSalarioBruto());

            Assertions.assertEquals(faixa1_aliquota, calcINSSService.getPorcentagemAliquota());
            Assertions.assertEquals(faixa1_parcelaADeduzir, calcINSSService.getParcelaADeduzir());
        }

        @Test
        void nao_deve_retornar_Faixa1_para_valores_superioes() {
            double salarioBrutoMensal = faixa1_valorFinal + 0.1;

            UserINSSIn userliq = new UserINSSIn("Victor", salarioBrutoMensal);
            calcINSSService.generateFaixaSalarial(userliq.getSalarioBruto());

            Assertions.assertNotEquals(faixa1_aliquota, calcINSSService.getParcelaADeduzir());
            Assertions.assertNotEquals(faixa1_parcelaADeduzir, calcINSSService.getPorcentagemAliquota());
        }

    }

    @Nested()
    @DisplayName("Faixa 2")
    class Faixa2 {
        @Test
        void deve_retornar_Faixa2_para_valor_final() {
            double salarioBrutoMensal = faixa2_valorFinal;

            UserINSSIn userliq = new UserINSSIn("Victor", salarioBrutoMensal);
            calcINSSService.generateFaixaSalarial(userliq.getSalarioBruto());

            Assertions.assertEquals(faixa2_parcelaADeduzir, calcINSSService.getParcelaADeduzir());
            Assertions.assertEquals(faixa2_aliquota, calcINSSService.getPorcentagemAliquota());
        }

        @Test
        void deve_retornar_Faixa2_para_valor_inicial() {
            double salarioBrutoMensal = faixa2_valorInicial;

            UserINSSIn userliq = new UserINSSIn("Victor", salarioBrutoMensal);
            calcINSSService.generateFaixaSalarial(userliq.getSalarioBruto());

            Assertions.assertEquals(faixa2_parcelaADeduzir, calcINSSService.getParcelaADeduzir());
            Assertions.assertEquals(faixa2_aliquota, calcINSSService.getPorcentagemAliquota());
        }


        @Test
        void nao_deve_retornar_Faixa2_para_valores_superioes() {
            double salarioBrutoMensal = faixa2_valorFinal + 0.1;

            UserINSSIn userliq = new UserINSSIn("Victor", salarioBrutoMensal);
            calcINSSService.generateFaixaSalarial(userliq.getSalarioBruto());

            Assertions.assertNotEquals(faixa2_parcelaADeduzir, calcINSSService.getParcelaADeduzir());
            Assertions.assertNotEquals(faixa2_aliquota, calcINSSService.getPorcentagemAliquota());
        }

        @Test
        void nao_deve_retornar_Faixa2_para_valores_inferiores() {
            double salarioBrutoMensal = faixa2_valorInicial - 0.1;

            UserINSSIn userliq = new UserINSSIn("Victor", salarioBrutoMensal);
            calcINSSService.generateFaixaSalarial(userliq.getSalarioBruto());

            Assertions.assertNotEquals(faixa2_parcelaADeduzir, calcINSSService.getParcelaADeduzir());
            Assertions.assertNotEquals(faixa2_aliquota, calcINSSService.getPorcentagemAliquota());
        }

    }

    @Nested()
    @DisplayName("Faixa 3")
    class Faixa3 {
        @Test
        void deve_retornar_Faixa3_para_valor_final() {
            double salarioBrutoMensal = faixa3_valorFinal;

            UserINSSIn userliq = new UserINSSIn("Victor", salarioBrutoMensal);
            calcINSSService.generateFaixaSalarial(userliq.getSalarioBruto());

            Assertions.assertEquals(faixa3_parcelaADeduzir, calcINSSService.getParcelaADeduzir());
            Assertions.assertEquals(faixa3_aliquota, calcINSSService.getPorcentagemAliquota());
        }

        @Test
        void deve_retornar_Faixa3_para_valor_inicial() {
            double salarioBrutoMensal = faixa3_valorInicial;

            UserINSSIn userliq = new UserINSSIn("Victor", salarioBrutoMensal);
            calcINSSService.generateFaixaSalarial(userliq.getSalarioBruto());

            Assertions.assertEquals(faixa3_parcelaADeduzir, calcINSSService.getParcelaADeduzir());
            Assertions.assertEquals(faixa3_aliquota, calcINSSService.getPorcentagemAliquota());
        }

        @Test
        void nao_deve_retornar_Faixa3_para_valores_superioes() {
            double salarioBrutoMensal = faixa3_valorFinal + 0.1 ;

            UserINSSIn userliq = new UserINSSIn("Victor", salarioBrutoMensal);
            calcINSSService.generateFaixaSalarial(userliq.getSalarioBruto());

            Assertions.assertNotEquals(faixa3_parcelaADeduzir, calcINSSService.getParcelaADeduzir());
            Assertions.assertNotEquals(faixa3_aliquota, calcINSSService.getPorcentagemAliquota());
        }

        @Test
        void nao_deve_retornar_Faixa3_para_valores_inferiores() {
            double salarioBrutoMensal = faixa3_valorInicial - 0.1 ;

            UserINSSIn userliq = new UserINSSIn("Victor", salarioBrutoMensal);
            calcINSSService.generateFaixaSalarial(userliq.getSalarioBruto());

            Assertions.assertNotEquals(faixa3_parcelaADeduzir, calcINSSService.getParcelaADeduzir());
            Assertions.assertNotEquals(faixa3_aliquota, calcINSSService.getPorcentagemAliquota());
        }

    }

    @Nested()
    @DisplayName("Faixa 4")
    class Faixa4 {
        @Test
        void deve_retornar_Faixa4_para_valor_final() {
            double salarioBrutoMensal = faixa4_valorFinal;

            UserINSSIn userliq = new UserINSSIn("Victor", salarioBrutoMensal);
            calcINSSService.generateFaixaSalarial(userliq.getSalarioBruto());

            Assertions.assertEquals(faixa4_parcelaADeduzir, calcINSSService.getParcelaADeduzir());
            Assertions.assertEquals(faixa4_aliquota, calcINSSService.getPorcentagemAliquota());
        }

        @Test
        void deve_retornar_Faixa4_para_valor_inicial() {
            double salarioBrutoMensal = faixa4_valorInicial;

            UserINSSIn userliq = new UserINSSIn("Victor", salarioBrutoMensal);
            calcINSSService.generateFaixaSalarial(userliq.getSalarioBruto());

            Assertions.assertEquals(faixa4_parcelaADeduzir, calcINSSService.getParcelaADeduzir());
            Assertions.assertEquals(faixa4_aliquota, calcINSSService.getPorcentagemAliquota());
        }


//        @Test
//        void nao_deve_retornar_Faixa4_para_valores_superioes() {
//            double salarioBrutoMensal = faixa4_aliquota + 0.1;
//
//            UserLiq userliq = new UserLiq("Victor", salarioBrutoMensal);
//            calcINSS.generateFaixaSalarial(userliq.getSalarioBruto());
//
//            Assertions.assertNotEquals(0.0, calcINSS.getParcelaADeduzir());
//            Assertions.assertNotEquals(0.0, calcINSS.getPorcentagemAliquota());
//        }

        @Test
        void nao_deve_retornar_Faixa4_para_valores_inferiores() {
            double salarioBrutoMensal = faixa4_valorInicial - 0.1;

            UserINSSIn userliq = new UserINSSIn("Victor", salarioBrutoMensal);
            calcINSSService.generateFaixaSalarial(userliq.getSalarioBruto());

            Assertions.assertNotEquals(faixa4_parcelaADeduzir, calcINSSService.getParcelaADeduzir());
            Assertions.assertNotEquals(faixa4_aliquota, calcINSSService.getPorcentagemAliquota());
        }
    }


}
