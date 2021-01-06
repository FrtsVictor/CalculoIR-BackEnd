package com.alterdata.calculo.ir.CalculoIRPF.CalculoIRPF.Aliquota;

import com.alterdata.calculo.ir.CalculoIRPF.modelsCVS.UserCSVIn;
import com.alterdata.calculo.ir.CalculoIRPF.services.calcIRPF.AliqAnualSimplesService;
import org.junit.jupiter.api.*;

import static com.alterdata.calculo.ir.CalculoIRPF.services.calcIRPF.BaseAliquotaIRPFAnual.*;

public class AliquotaTests {

    private TestInfo testInfo;
    private TestReporter testReporter;

    private AliqAnualSimplesService aliquotaAnualService2;

    private UserCSVIn usuarioCSVIn;

    @BeforeEach
    void initEach(TestReporter testReporter, TestInfo testInfo) {
        aliquotaAnualService2 = new AliqAnualSimplesService();
        usuarioCSVIn = new UserCSVIn();


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
        public void deve_retornar_RetoDeducaoSimplificada() {
            double rendimentoAnual = 1000000;

            UserCSVIn usrIn = new UserCSVIn("victor", "1325645", rendimentoAnual);
            aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

            Assertions.assertEquals(aliquotaAnualService2.getDeducaoSimplificada(), tetoDeducaoSimplificada);
        }

        @Test
        public void nao_DeveRetornarTetoDeducaoSimplificada() {
            double rendimentoAnual = 40000;

            UserCSVIn usrIn = new UserCSVIn("victor", "1325645", rendimentoAnual);
            aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

            Assertions.assertNotEquals(aliquotaAnualService2.getDeducaoSimplificada(), tetoDeducaoSimplificada);
        }

    }

    @Nested
    @DisplayName("Aliquota 1 - 0%")
    class Aliquota1 {

        @Test
        void deve_retornar_Aliquota1_para_valor_inicial() {
            double rendimentoAnual = base1_valorInicial * 1.30;

            UserCSVIn usrIn = new UserCSVIn("victor", "1325645", rendimentoAnual);
            aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

            Assertions.assertEquals( base1_aliquota, aliquotaAnualService2.getPorcentagemAliquota());
            Assertions.assertEquals(base1_parcelaADeduzir, aliquotaAnualService2.getParcelaADeduzirAliquota());
        }

        @Test
        void nao_Deve_retornar_Aliquota1_para_valores_superioes() {
            double rendimentoAnual = (base1_valorInicial * 1.30) + 0.1;
            System.out.println(rendimentoAnual);
            UserCSVIn usrIn = new UserCSVIn("victor", "1325645", rendimentoAnual);
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
            double rendimentoAnual = base2_valorInicial * 1.30;

            UserCSVIn usrIn = new UserCSVIn("victor", "1325645", rendimentoAnual);
            aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

            Assertions.assertEquals(base2_parcelaADeduzir, aliquotaAnualService2.getParcelaADeduzirAliquota());
            Assertions.assertEquals(base2_aliquota, aliquotaAnualService2.getPorcentagemAliquota());
        }

        @Test
        void deve_retornar_Aliquota2_para_valor_final() {
            double rendimentoAnual = base2_valorFinal * 1.30;

            UserCSVIn usrIn = new UserCSVIn("victor", "1325645", rendimentoAnual);
            aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

            Assertions.assertEquals(base2_parcelaADeduzir, aliquotaAnualService2.getParcelaADeduzirAliquota());
            Assertions.assertEquals(base2_aliquota, aliquotaAnualService2.getPorcentagemAliquota());
        }

        @Test
        void nao_deve_retornar_Aliquota2_para_valores_superioes() {
            double rendimentoAnual = base2_valorFinal * 1.30 + 0.1;

            UserCSVIn usrIn = new UserCSVIn("victor", "1325645", rendimentoAnual);
            aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

            Assertions.assertNotEquals(base2_parcelaADeduzir, aliquotaAnualService2.getParcelaADeduzirAliquota());
            Assertions.assertNotEquals(base2_aliquota, aliquotaAnualService2.getPorcentagemAliquota());
        }

        @Test
        void nao_deve_retornar_Aliquota2_para_valores_inferioes() {
            double rendimentoAnual = base2_valorInicial * 1.30 - 0.1;

            UserCSVIn usrIn = new UserCSVIn("victor", "1325645", rendimentoAnual);
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
            double rendimentoAnual = base3_valorInicial * 1.30;

            UserCSVIn usrIn = new UserCSVIn("victor", "1325645", rendimentoAnual);
            aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

            Assertions.assertEquals(base3_parcelaADeduzir, aliquotaAnualService2.getParcelaADeduzirAliquota());
            Assertions.assertEquals(base3_aliquota, aliquotaAnualService2.getPorcentagemAliquota());
        }

        @Test
        void deve_retornar_Aliquota3_para_valor_final() {
            double rendimentoAnual = base3_valorFinal * 1.30;

            UserCSVIn usrIn = new UserCSVIn("victor", "1325645", rendimentoAnual);
            aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

            Assertions.assertEquals(base3_parcelaADeduzir, aliquotaAnualService2.getParcelaADeduzirAliquota());
            Assertions.assertEquals(base3_aliquota, aliquotaAnualService2.getPorcentagemAliquota());
        }

        @Test
        void nao_deve_retornar_Aliquota3_para_valores_superioes() {
            double rendimentoAnual = base3_valorFinal * 1.30 + 0.1;

            UserCSVIn usrIn = new UserCSVIn("victor", "1325645", rendimentoAnual);
            aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

            Assertions.assertNotEquals(base3_parcelaADeduzir, aliquotaAnualService2.getParcelaADeduzirAliquota());
            Assertions.assertNotEquals(base3_aliquota, aliquotaAnualService2.getPorcentagemAliquota());
        }

        @Test
        void nao_deve_retornar_Aliquota3_para_valores_inferioes() {
            double rendimentoAnual = base3_valorInicial * 1.30 - 0.1;

            UserCSVIn usrIn = new UserCSVIn("victor", "1325645", rendimentoAnual);
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
            double rendimentoAnual = base4_valorInicial * 1.30;

            UserCSVIn usrIn = new UserCSVIn("victor", "1325645", rendimentoAnual);
            aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

            Assertions.assertEquals(base4_parcelaADeduzir, aliquotaAnualService2.getParcelaADeduzirAliquota());
            Assertions.assertEquals(base4_aliquota, aliquotaAnualService2.getPorcentagemAliquota());
        }

        @Test
        void deve_retornar_Aliquota4_para_valor_final() {
            double rendimentoAnual = base4_valorFinal * 1.30;

            UserCSVIn usrIn = new UserCSVIn("victor", "1325645", rendimentoAnual);
            aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

            Assertions.assertEquals(base4_parcelaADeduzir, aliquotaAnualService2.getParcelaADeduzirAliquota());
            Assertions.assertEquals(base4_aliquota, aliquotaAnualService2.getPorcentagemAliquota());
        }

        @Test
        void nao_deve_retornar_Aliquota4_para_valores_superioes() {
            double rendimentoAnual = base4_valorFinal * 1.30 + 0.1;

            UserCSVIn usrIn = new UserCSVIn("victor", "1325645", rendimentoAnual);
            aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

            Assertions.assertNotEquals(base4_parcelaADeduzir, aliquotaAnualService2.getParcelaADeduzirAliquota());
            Assertions.assertNotEquals(base4_aliquota, aliquotaAnualService2.getPorcentagemAliquota());
        }

        @Test
        void nao_deve_retornar_Aliquota4_para_valores_inferioes() {
            double rendimentoAnual = base4_valorInicial * 1.30 - 0.1;

            UserCSVIn usrIn = new UserCSVIn("victor", "1325645", rendimentoAnual);
            aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

            Assertions.assertNotEquals(base4_parcelaADeduzir, aliquotaAnualService2.getParcelaADeduzirAliquota());
            Assertions.assertNotEquals(base4_aliquota, aliquotaAnualService2.getPorcentagemAliquota());
        }

        @Nested()
        @DisplayName("Aliquota 5 - 27.5%")
        class Aliquota5 {
            @Test
            void deve_retornar_Aliquota5_para_valor_inicial() {
                double rendimentoAnual = base5_valorInicial * 1.30;

                UserCSVIn usrIn = new UserCSVIn("victor", "1325645", rendimentoAnual);
                aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

                Assertions.assertEquals(base5_parcelaADeduzir, aliquotaAnualService2.getParcelaADeduzirAliquota());
                Assertions.assertEquals(base5_aliquota, aliquotaAnualService2.getPorcentagemAliquota());
            }

            @Test
            void nao_deve_retornar_Aliquota5_para_valores_inferioes() {
                double rendimentoAnual = base5_valorInicial * 1.30 - 0.1;

                UserCSVIn usrIn = new UserCSVIn("victor", "1325645", rendimentoAnual);
                aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

                Assertions.assertNotEquals(base4_parcelaADeduzir, aliquotaAnualService2.getParcelaADeduzirAliquota());
                Assertions.assertNotEquals(base4_aliquota, aliquotaAnualService2.getPorcentagemAliquota());
            }
        }
    }
}