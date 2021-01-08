package com.alterdata.calculo.ir.CalculoIRPF.CalculoIRPF.Aliquota;

import com.alterdata.calculo.ir.CalculoIRPF.modelsCVS.CalcUserIn;
import com.alterdata.calculo.ir.CalculoIRPF.services.calcAnualIRPF.CalcAliqAnualSimplesIRPFService;
import org.junit.jupiter.api.*;

import static com.alterdata.calculo.ir.CalculoIRPF.services.calcAnualIRPF.BaseAnualIRPF.*;
import static com.alterdata.calculo.ir.CalculoIRPF.services.calcAnualIRPF.TetoSalarioBrutoALiq.*;

public class AliquotaTests {

    private TestInfo testInfo;
    private TestReporter testReporter;

    private CalcAliqAnualSimplesIRPFService aliquotaAnualService2;

    private CalcUserIn usuarioCSVIn;

    @BeforeEach
    void initEach(TestReporter testReporter, TestInfo testInfo) {
        aliquotaAnualService2 = new CalcAliqAnualSimplesIRPFService();
        usuarioCSVIn = new CalcUserIn();


        this.testReporter = testReporter;
        this.testInfo = testInfo;

        testReporter.publishEntry("Running -> " +
                testInfo.getDisplayName() +
                " with tag -> " + testInfo.getTags());
    }

    @Nested
    @DisplayName("Deducao simplificada, teto = 16754.34")
    class DeducaoSimplificada {
        @Test
        public void deve_retornar_TetoDeducaoSimplificada() {
            double rendimentoAnual = teto_deducao;

            CalcUserIn usrIn = new CalcUserIn("victor", "1325645", rendimentoAnual);
            aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

            Assertions.assertEquals(aliquotaAnualService2.getDeducaoSimplificada(), tetoDeducaoSimplificada);
        }

        @Test
        public void nao_deve_retornar_TetoDeducaoSimplificada() {
            double rendimentoAnual = teto_deducao - 0.1;

            CalcUserIn usrIn = new CalcUserIn("victor", "1325645", rendimentoAnual);
            aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

            Assertions.assertNotEquals(aliquotaAnualService2.getDeducaoSimplificada(), tetoDeducaoSimplificada);
        }

    }

    @Nested
    @DisplayName("Aliquota 1 - 0%")
    class Aliquota1 {

        @Test
        void deve_retornar_Aliquota1_para_valor_inicial() {
            double rendimentoAnual = ali1_inicial;

            CalcUserIn usrIn = new CalcUserIn("victor", "1325645", rendimentoAnual);
            aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

            Assertions.assertEquals( base1_aliquota, aliquotaAnualService2.getPorcentagemAliquota());
            Assertions.assertEquals(base1_parcelaADeduzir, aliquotaAnualService2.getParcelaADeduzirAliquota());
        }

        @Test
        void nao_Deve_retornar_Aliquota1_para_valores_superioes() {
            double rendimentoAnual = ali1_inicial + 0.1;
            System.out.println(rendimentoAnual);
            CalcUserIn usrIn = new CalcUserIn("victor", "1325645", rendimentoAnual);
            aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

            Assertions.assertNotEquals(base1_aliquota, aliquotaAnualService2.getParcelaADeduzirAliquota());
            Assertions.assertNotEquals(base1_parcelaADeduzir, aliquotaAnualService2.getPorcentagemAliquota());
        }

    }

    @Nested()
    @DisplayName("Aliquota 2 - 7.5%")
    class Aliquota2 {
        @Test
        void deve_retornar_Aliquota2_para_valor_inicial() {
            double rendimentoAnual = ali2_inicial;

            CalcUserIn usrIn = new CalcUserIn("victor", "1325645", rendimentoAnual);
            aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

            Assertions.assertEquals(base2_parcelaADeduzir, aliquotaAnualService2.getParcelaADeduzirAliquota());
            Assertions.assertEquals(base2_aliquota, aliquotaAnualService2.getPorcentagemAliquota());
        }

        @Test
        void deve_retornar_Aliquota2_para_valor_final() {
            double rendimentoAnual = ali2_final;

            CalcUserIn usrIn = new CalcUserIn("victor", "1325645", rendimentoAnual);
            aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

            Assertions.assertEquals(base2_parcelaADeduzir, aliquotaAnualService2.getParcelaADeduzirAliquota());
            Assertions.assertEquals(base2_aliquota, aliquotaAnualService2.getPorcentagemAliquota());
        }

        @Test
        void nao_deve_retornar_Aliquota2_para_valores_superioes() {
            double rendimentoAnual = ali2_final + 0.1;

            CalcUserIn usrIn = new CalcUserIn("victor", "1325645", rendimentoAnual);
            aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

            Assertions.assertNotEquals(base2_parcelaADeduzir, aliquotaAnualService2.getParcelaADeduzirAliquota());
            Assertions.assertNotEquals(base2_aliquota, aliquotaAnualService2.getPorcentagemAliquota());
        }

        @Test
        void nao_deve_retornar_Aliquota2_para_valores_inferioes() {
            double rendimentoAnual = ali2_inicial - 0.1;

            CalcUserIn usrIn = new CalcUserIn("victor", "1325645", rendimentoAnual);
            aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

            Assertions.assertNotEquals(base2_parcelaADeduzir, aliquotaAnualService2.getParcelaADeduzirAliquota());
            Assertions.assertNotEquals(base2_aliquota, aliquotaAnualService2.getPorcentagemAliquota());
        }

    }

    @Nested()
    @DisplayName("Aliquota 3 - 15%")
    class Aliquota3 {
        @Test
        void deve_retornar_Aliquota3_para_valor_inicial() {
            double rendimentoAnual = aliq3_inicial;

            CalcUserIn usrIn = new CalcUserIn("victor", "1325645", rendimentoAnual);
            aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

            Assertions.assertEquals(base3_parcelaADeduzir, aliquotaAnualService2.getParcelaADeduzirAliquota());
            Assertions.assertEquals(base3_aliquota, aliquotaAnualService2.getPorcentagemAliquota());
        }

        @Test
        void deve_retornar_Aliquota3_para_valor_final() {
            double rendimentoAnual = aliq3_final;

            CalcUserIn usrIn = new CalcUserIn("victor", "1325645", rendimentoAnual);
            aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

            Assertions.assertEquals(base3_parcelaADeduzir, aliquotaAnualService2.getParcelaADeduzirAliquota());
            Assertions.assertEquals(base3_aliquota, aliquotaAnualService2.getPorcentagemAliquota());
        }

        @Test
        void nao_deve_retornar_Aliquota3_para_valores_superioes() {
            double rendimentoAnual = aliq3_final + 0.1;

            CalcUserIn usrIn = new CalcUserIn("victor", "1325645", rendimentoAnual);
            aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

            Assertions.assertNotEquals(base3_parcelaADeduzir, aliquotaAnualService2.getParcelaADeduzirAliquota());
            Assertions.assertNotEquals(base3_aliquota, aliquotaAnualService2.getPorcentagemAliquota());
        }

        @Test
        void nao_deve_retornar_Aliquota3_para_valores_inferioes() {
            double rendimentoAnual = aliq3_inicial - 0.1;

            CalcUserIn usrIn = new CalcUserIn("victor", "1325645", rendimentoAnual);
            aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

            Assertions.assertNotEquals(base3_parcelaADeduzir, aliquotaAnualService2.getParcelaADeduzirAliquota());
            Assertions.assertNotEquals(base3_aliquota, aliquotaAnualService2.getPorcentagemAliquota());
        }

    }

    @Nested()
    @DisplayName("Aliquota 4 - 22.5%")
    class Aliquota4 {
        @Test
        void deve_retornar_Aliquota4_para_valor_inicial() {
            double rendimentoAnual = aliq4_inicial;

            CalcUserIn usrIn = new CalcUserIn("victor", "1325645", rendimentoAnual);
            aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

            Assertions.assertEquals(base4_parcelaADeduzir, aliquotaAnualService2.getParcelaADeduzirAliquota());
            Assertions.assertEquals(base4_aliquota, aliquotaAnualService2.getPorcentagemAliquota());
        }

        @Test
        void deve_retornar_Aliquota4_para_valor_final() {
            double rendimentoAnual = aliq4_final;

            CalcUserIn usrIn = new CalcUserIn("victor", "1325645", rendimentoAnual);
            aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

            Assertions.assertEquals(base4_parcelaADeduzir, aliquotaAnualService2.getParcelaADeduzirAliquota());
            Assertions.assertEquals(base4_aliquota, aliquotaAnualService2.getPorcentagemAliquota());
        }

        @Test
        void nao_deve_retornar_Aliquota4_para_valores_superioes() {
            double rendimentoAnual = aliq4_final + 0.1;

            CalcUserIn usrIn = new CalcUserIn("victor", "1325645", rendimentoAnual);
            aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

            Assertions.assertNotEquals(base4_parcelaADeduzir, aliquotaAnualService2.getParcelaADeduzirAliquota());
            Assertions.assertNotEquals(base4_aliquota, aliquotaAnualService2.getPorcentagemAliquota());
        }

        @Test
        void nao_deve_retornar_Aliquota4_para_valores_inferioes() {
            double rendimentoAnual = aliq4_inicial - 0.1;

            CalcUserIn usrIn = new CalcUserIn("victor", "1325645", rendimentoAnual);
            aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

            Assertions.assertNotEquals(base4_parcelaADeduzir, aliquotaAnualService2.getParcelaADeduzirAliquota());
            Assertions.assertNotEquals(base4_aliquota, aliquotaAnualService2.getPorcentagemAliquota());
        }

        @Nested()
        @DisplayName("Aliquota 5 - 27.5%")
        class Aliquota5 {
            @Test
            void deve_retornar_Aliquota5_para_valor_inicial() {
                double rendimentoAnual = aliq5_inicial;

                CalcUserIn usrIn = new CalcUserIn("victor", "1325645", rendimentoAnual);
                aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

                Assertions.assertEquals(base5_parcelaADeduzir, aliquotaAnualService2.getParcelaADeduzirAliquota());
                Assertions.assertEquals(base5_aliquota, aliquotaAnualService2.getPorcentagemAliquota());
            }

            @Test
            void nao_deve_retornar_Aliquota5_para_valores_inferioes() {
                double rendimentoAnual = aliq5_inicial - 0.1;

                CalcUserIn usrIn = new CalcUserIn("victor", "1325645", rendimentoAnual);
                aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

                Assertions.assertNotEquals(base5_parcelaADeduzir, aliquotaAnualService2.getParcelaADeduzirAliquota());
                Assertions.assertNotEquals(base5_aliquota, aliquotaAnualService2.getPorcentagemAliquota());
            }
        }
    }
}