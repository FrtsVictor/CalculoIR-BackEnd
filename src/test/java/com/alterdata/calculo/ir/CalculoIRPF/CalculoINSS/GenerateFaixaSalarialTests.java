package com.alterdata.calculo.ir.CalculoIRPF.CalculoINSS;

import com.alterdata.calculo.ir.CalculoIRPF.models.UserINSSIn;
import com.alterdata.calculo.ir.CalculoIRPF.services.calcINSS.CalcINSS;
import org.junit.jupiter.api.*;

import static com.alterdata.calculo.ir.CalculoIRPF.services.calcINSS.BaseMensalAliquotaINSS.*;

@DisplayName("Testing CalcINSS generateFaixaSalarial method")
public class GenerateFaixaSalarialTests {

    private TestInfo testInfo;
    private TestReporter testReporter;
    private CalcINSS calcINSS;
    private UserINSSIn userINSSIn;

    @BeforeEach
    void initEach(TestReporter testReporter, TestInfo testInfo) {
        calcINSS = new CalcINSS();
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
            calcINSS.generateFaixaSalarial(userliq.getSalarioBruto());

            Assertions.assertEquals(faixa1_aliquota, calcINSS.getPorcentagemAliquota());
            Assertions.assertEquals(faixa1_parcelaADeduzir, calcINSS.getParcelaADeduzir());
        }

        @Test
        void nao_deve_retornar_Faixa1_para_valores_superioes() {
            double salarioBrutoMensal = faixa1_valorFinal + 0.1;

            UserINSSIn userliq = new UserINSSIn("Victor", salarioBrutoMensal);
            calcINSS.generateFaixaSalarial(userliq.getSalarioBruto());

            Assertions.assertNotEquals(faixa1_aliquota, calcINSS.getParcelaADeduzir());
            Assertions.assertNotEquals(faixa1_parcelaADeduzir, calcINSS.getPorcentagemAliquota());
        }

    }

    @Nested()
    @DisplayName("Faixa 2")
    class Faixa2 {
        @Test
        void deve_retornar_Faixa2_para_valor_final() {
            double salarioBrutoMensal = faixa2_valorFinal;

            UserINSSIn userliq = new UserINSSIn("Victor", salarioBrutoMensal);
            calcINSS.generateFaixaSalarial(userliq.getSalarioBruto());

            Assertions.assertEquals(faixa2_parcelaADeduzir, calcINSS.getParcelaADeduzir());
            Assertions.assertEquals(faixa2_aliquota, calcINSS.getPorcentagemAliquota());
        }

        @Test
        void deve_retornar_Faixa2_para_valor_inicial() {
            double salarioBrutoMensal = faixa2_valorInicial;

            UserINSSIn userliq = new UserINSSIn("Victor", salarioBrutoMensal);
            calcINSS.generateFaixaSalarial(userliq.getSalarioBruto());

            Assertions.assertEquals(faixa2_parcelaADeduzir, calcINSS.getParcelaADeduzir());
            Assertions.assertEquals(faixa2_aliquota, calcINSS.getPorcentagemAliquota());
        }


        @Test
        void nao_deve_retornar_Faixa2_para_valores_superioes() {
            double salarioBrutoMensal = faixa2_valorFinal + 0.1;

            UserINSSIn userliq = new UserINSSIn("Victor", salarioBrutoMensal);
            calcINSS.generateFaixaSalarial(userliq.getSalarioBruto());

            Assertions.assertNotEquals(faixa2_parcelaADeduzir, calcINSS.getParcelaADeduzir());
            Assertions.assertNotEquals(faixa2_aliquota, calcINSS.getPorcentagemAliquota());
        }

        @Test
        void nao_deve_retornar_Faixa2_para_valores_inferiores() {
            double salarioBrutoMensal = faixa2_valorInicial - 0.1;

            UserINSSIn userliq = new UserINSSIn("Victor", salarioBrutoMensal);
            calcINSS.generateFaixaSalarial(userliq.getSalarioBruto());

            Assertions.assertNotEquals(faixa2_parcelaADeduzir, calcINSS.getParcelaADeduzir());
            Assertions.assertNotEquals(faixa2_aliquota, calcINSS.getPorcentagemAliquota());
        }

    }

    @Nested()
    @DisplayName("Faixa 3")
    class Faixa3 {
        @Test
        void deve_retornar_Faixa3_para_valor_final() {
            double salarioBrutoMensal = faixa3_valorFinal;

            UserINSSIn userliq = new UserINSSIn("Victor", salarioBrutoMensal);
            calcINSS.generateFaixaSalarial(userliq.getSalarioBruto());

            Assertions.assertEquals(faixa3_parcelaADeduzir, calcINSS.getParcelaADeduzir());
            Assertions.assertEquals(faixa3_aliquota, calcINSS.getPorcentagemAliquota());
        }

        @Test
        void deve_retornar_Faixa3_para_valor_inicial() {
            double salarioBrutoMensal = faixa3_valorInicial;

            UserINSSIn userliq = new UserINSSIn("Victor", salarioBrutoMensal);
            calcINSS.generateFaixaSalarial(userliq.getSalarioBruto());

            Assertions.assertEquals(faixa3_parcelaADeduzir, calcINSS.getParcelaADeduzir());
            Assertions.assertEquals(faixa3_aliquota, calcINSS.getPorcentagemAliquota());
        }

        @Test
        void nao_deve_retornar_Faixa3_para_valores_superioes() {
            double salarioBrutoMensal = faixa3_valorFinal + 0.1 ;

            UserINSSIn userliq = new UserINSSIn("Victor", salarioBrutoMensal);
            calcINSS.generateFaixaSalarial(userliq.getSalarioBruto());

            Assertions.assertNotEquals(faixa3_parcelaADeduzir, calcINSS.getParcelaADeduzir());
            Assertions.assertNotEquals(faixa3_aliquota, calcINSS.getPorcentagemAliquota());
        }

        @Test
        void nao_deve_retornar_Faixa3_para_valores_inferiores() {
            double salarioBrutoMensal = faixa3_valorInicial - 0.1 ;

            UserINSSIn userliq = new UserINSSIn("Victor", salarioBrutoMensal);
            calcINSS.generateFaixaSalarial(userliq.getSalarioBruto());

            Assertions.assertNotEquals(faixa3_parcelaADeduzir, calcINSS.getParcelaADeduzir());
            Assertions.assertNotEquals(faixa3_aliquota, calcINSS.getPorcentagemAliquota());
        }

    }

    @Nested()
    @DisplayName("Faixa 4")
    class Faixa4 {
        @Test
        void deve_retornar_Faixa4_para_valor_final() {
            double salarioBrutoMensal = faixa4_valorFinal;

            UserINSSIn userliq = new UserINSSIn("Victor", salarioBrutoMensal);
            calcINSS.generateFaixaSalarial(userliq.getSalarioBruto());

            Assertions.assertEquals(faixa4_parcelaADeduzir, calcINSS.getParcelaADeduzir());
            Assertions.assertEquals(faixa4_aliquota, calcINSS.getPorcentagemAliquota());
        }

        @Test
        void deve_retornar_Faixa4_para_valor_inicial() {
            double salarioBrutoMensal = faixa4_valorInicial;

            UserINSSIn userliq = new UserINSSIn("Victor", salarioBrutoMensal);
            calcINSS.generateFaixaSalarial(userliq.getSalarioBruto());

            Assertions.assertEquals(faixa4_parcelaADeduzir, calcINSS.getParcelaADeduzir());
            Assertions.assertEquals(faixa4_aliquota, calcINSS.getPorcentagemAliquota());
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
            calcINSS.generateFaixaSalarial(userliq.getSalarioBruto());

            Assertions.assertNotEquals(faixa4_parcelaADeduzir, calcINSS.getParcelaADeduzir());
            Assertions.assertNotEquals(faixa4_aliquota, calcINSS.getPorcentagemAliquota());
        }
    }


}
