package com.alterdata.calculo.ir.CalculoIRPF.IRRF;

import com.alterdata.calculo.ir.CalculoIRPF.models.UserIRRFIn;
import com.alterdata.calculo.ir.CalculoIRPF.services.calcIRRF.CalcIRRFService;
import org.junit.jupiter.api.*;

import static com.alterdata.calculo.ir.CalculoIRPF.services.calcINSS.BaseMensalAliquotaINSS.*;

public class GenerateFaixaSalarialTests {

    private TestInfo testInfo;
    private TestReporter testReporter;

    private CalcIRRFService calcIRRF;
    private UserIRRFIn userIn;



    @BeforeEach
    void initEach(TestReporter testReporter, TestInfo testInfo) {
        calcIRRF = new CalcIRRFService();
        userIn = new UserIRRFIn();

        this.testReporter = testReporter;
        this.testInfo = testInfo;

        testReporter.publishEntry("Running -> " +
                testInfo.getDisplayName() +
                " with tag -> " + testInfo.getTags());
    }


    @Nested
    @DisplayName("Faixa 1")
    class Faixa1 {

        @Test
        void deve_retornar_Faixa1_para_valor_final() {
            double salarioBrutoMensal = faixa1_valorFinal;
            double pensaoAlimenticia = 0;
            double dependentes = 0;

            UserIRRFIn userliq = new UserIRRFIn("Victor", salarioBrutoMensal, pensaoAlimenticia, dependentes);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertEquals(faixa1_aliquota, calcIRRF.getPorcentagemAliquota());
            Assertions.assertEquals(faixa1_parcelaADeduzir, calcIRRF.getParcelaADeduzir());
        }

        @Test
        void nao_deve_retornar_Faixa1_para_valores_superioes() {
            double salarioBrutoMensal = faixa1_valorFinal + 0.1;
            double pensaoAlimenticia = 0;
                    double dependentes = 0;

            UserIRRFIn userliq = new UserIRRFIn("Victor", salarioBrutoMensal, pensaoAlimenticia, dependentes);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertNotEquals(faixa1_aliquota, calcIRRF.getParcelaADeduzir());
            Assertions.assertNotEquals(faixa1_parcelaADeduzir, calcIRRF.getPorcentagemAliquota());
        }

    }

    @Nested()
    @DisplayName("Faixa 2")
    class Faixa2 {
        @Test
        void deve_retornar_Faixa2_para_valor_final() {
            double salarioBrutoMensal = faixa2_valorFinal;
            double pensaoAlimenticia = 0;
                    double dependentes = 0;

            UserIRRFIn userliq = new UserIRRFIn("Victor", salarioBrutoMensal, pensaoAlimenticia, dependentes);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertEquals(faixa2_parcelaADeduzir, calcIRRF.getParcelaADeduzir());
            Assertions.assertEquals(faixa2_aliquota, calcIRRF.getPorcentagemAliquota());
        }

        @Test
        void deve_retornar_Faixa2_para_valor_inicial() {
            double salarioBrutoMensal = faixa2_valorInicial;
            double pensaoAlimenticia = 0;
                    double dependentes = 0;

            UserIRRFIn userliq = new UserIRRFIn("Victor", salarioBrutoMensal, pensaoAlimenticia, dependentes);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertEquals(faixa2_parcelaADeduzir, calcIRRF.getParcelaADeduzir());
            Assertions.assertEquals(faixa2_aliquota, calcIRRF.getPorcentagemAliquota());
        }


        @Test
        void nao_deve_retornar_Faixa2_para_valores_superioes() {
            double salarioBrutoMensal = faixa2_valorFinal + 0.1;
            double pensaoAlimenticia = 0;
                    double dependentes = 0;

            UserIRRFIn userliq = new UserIRRFIn("Victor", salarioBrutoMensal, pensaoAlimenticia, dependentes);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertNotEquals(faixa2_parcelaADeduzir, calcIRRF.getParcelaADeduzir());
            Assertions.assertNotEquals(faixa2_aliquota, calcIRRF.getPorcentagemAliquota());
        }

        @Test
        void nao_deve_retornar_Faixa2_para_valores_inferiores() {
            double salarioBrutoMensal = faixa2_valorInicial - 0.1;
            double pensaoAlimenticia = 0;
                    double dependentes = 0;

            UserIRRFIn userliq = new UserIRRFIn("Victor", salarioBrutoMensal, pensaoAlimenticia, dependentes);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertNotEquals(faixa2_parcelaADeduzir, calcIRRF.getParcelaADeduzir());
            Assertions.assertNotEquals(faixa2_aliquota, calcIRRF.getPorcentagemAliquota());
        }

    }

    @Nested()
    @DisplayName("Faixa 3")
    class Faixa3 {
        @Test
        void deve_retornar_Faixa3_para_valor_final() {
            double salarioBrutoMensal = faixa3_valorFinal;
            double pensaoAlimenticia = 0;
                    double dependentes = 0;

            UserIRRFIn userliq = new UserIRRFIn("Victor", salarioBrutoMensal, pensaoAlimenticia, dependentes);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertEquals(faixa3_parcelaADeduzir, calcIRRF.getParcelaADeduzir());
            Assertions.assertEquals(faixa3_aliquota, calcIRRF.getPorcentagemAliquota());
        }

        @Test
        void deve_retornar_Faixa3_para_valor_inicial() {
            double salarioBrutoMensal = faixa3_valorInicial;
            double pensaoAlimenticia = 0;
                    double dependentes = 0;

            UserIRRFIn userliq = new UserIRRFIn("Victor", salarioBrutoMensal, pensaoAlimenticia, dependentes);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertEquals(faixa3_parcelaADeduzir, calcIRRF.getParcelaADeduzir());
            Assertions.assertEquals(faixa3_aliquota, calcIRRF.getPorcentagemAliquota());
        }

        @Test
        void nao_deve_retornar_Faixa3_para_valores_superioes() {
            double salarioBrutoMensal = faixa3_valorFinal + 0.1 ;
            double pensaoAlimenticia = 0;
                    double dependentes = 0;

            UserIRRFIn userliq = new UserIRRFIn("Victor", salarioBrutoMensal, pensaoAlimenticia, dependentes);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertNotEquals(faixa3_parcelaADeduzir, calcIRRF.getParcelaADeduzir());
            Assertions.assertNotEquals(faixa3_aliquota, calcIRRF.getPorcentagemAliquota());
        }

        @Test
        void nao_deve_retornar_Faixa3_para_valores_inferiores() {
            double salarioBrutoMensal = faixa3_valorInicial - 0.1 ;
            double pensaoAlimenticia = 0;
                    double dependentes = 0;

            UserIRRFIn userliq = new UserIRRFIn("Victor", salarioBrutoMensal, pensaoAlimenticia, dependentes);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertNotEquals(faixa3_parcelaADeduzir, calcIRRF.getParcelaADeduzir());
            Assertions.assertNotEquals(faixa3_aliquota, calcIRRF.getPorcentagemAliquota());
        }

    }

    @Nested()
    @DisplayName("Faixa 4")
    class Faixa4 {
        @Test
        void deve_retornar_Faixa4_para_valor_final() {
            double salarioBrutoMensal = faixa4_valorFinal;
            double pensaoAlimenticia = 0;
                    double dependentes = 0;

            UserIRRFIn userliq = new UserIRRFIn("Victor", salarioBrutoMensal, pensaoAlimenticia, dependentes);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertEquals(faixa4_parcelaADeduzir, calcIRRF.getParcelaADeduzir());
            Assertions.assertEquals(faixa4_aliquota, calcIRRF.getPorcentagemAliquota());
        }

        @Test
        void deve_retornar_Faixa4_para_valor_inicial() {
            double salarioBrutoMensal = faixa4_valorInicial;
            double pensaoAlimenticia = 0;
                    double dependentes = 0;

            UserIRRFIn userliq = new UserIRRFIn("Victor", salarioBrutoMensal, pensaoAlimenticia, dependentes);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertEquals(faixa4_parcelaADeduzir, calcIRRF.getParcelaADeduzir());
            Assertions.assertEquals(faixa4_aliquota, calcIRRF.getPorcentagemAliquota());
        }


//        @Test
//        void nao_deve_retornar_Faixa4_para_valores_superioes() {
//            double salarioBrutoMensal = faixa4_aliquota + 0.1;
        double pensaoAlimenticia = 0;
        double dependentes = 0;
//
//            UserLiq userliq = new UserLiq("Victor", salarioBrutoMensal, pensaoAlimenticia, dependentes);
//            calcINSS.generateFaixaSalarial();
//
//            Assertions.assertNotEquals(0.0, calcINSS.getParcelaADeduzir());
//            Assertions.assertNotEquals(0.0, calcINSS.getPorcentagemAliquota());
//        }

        @Test
        void nao_deve_retornar_Faixa4_para_valores_inferiores() {
            double salarioBrutoMensal = faixa4_valorInicial - 0.1;
            double pensaoAlimenticia = 0;
                    double dependentes = 0;

            UserIRRFIn userliq = new UserIRRFIn("Victor", salarioBrutoMensal, pensaoAlimenticia, dependentes);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertNotEquals(faixa4_parcelaADeduzir, calcIRRF.getParcelaADeduzir());
            Assertions.assertNotEquals(faixa4_aliquota, calcIRRF.getPorcentagemAliquota());
        }
    }





}
