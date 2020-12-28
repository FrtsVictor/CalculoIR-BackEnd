package com.alterdata.calculo.ir.CalculoIRPF.Aliquota;

import com.alterdata.calculo.ir.CalculoIRPF.modelsCVS.UserCSVIn;
import com.alterdata.calculo.ir.CalculoIRPF.services.calc.CalcBaseAliqAnualSimplesWithoutCSVOutService;
import org.junit.jupiter.api.*;

import static com.alterdata.calculo.ir.CalculoIRPF.services.calc.CalcBaseAliquota.*;

public class AliquotaWithoutCSV {

    private TestInfo testInfo;
    private TestReporter testReporter;

    private CalcBaseAliqAnualSimplesWithoutCSVOutService aliquotaAnualService2;

    private UserCSVIn usuarioCSVIn;


    @BeforeEach
    void initEach(TestReporter testReporter, TestInfo testInfo) {
        aliquotaAnualService2 = new CalcBaseAliqAnualSimplesWithoutCSVOutService();
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
        public void deveRetornarTetoDeducaoSimplificada() {
            double rendimentoAnual = 1000000;
            UserCSVIn usrIn = new UserCSVIn("victor", "1325645", rendimentoAnual);
            aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

            Assertions.assertEquals(aliquotaAnualService2.getDeducaoSimplificada(), tetoDeducaoSimplificada);
        }

        @Test
        public void naoDeveRetornarTetoDeducaoSimplificada() {
            double rendimentoAnual = 40000;
            UserCSVIn usrIn = new UserCSVIn("victor", "1325645", rendimentoAnual);
            aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

            Assertions.assertNotEquals(aliquotaAnualService2.getDeducaoSimplificada(), tetoDeducaoSimplificada);
        }

    }


    @Nested
    @DisplayName("Aliquota 1 using This - 0%")
    class Aliquota1 {

        @Test
        void deveRetornarAliquota1() {
            double rendimentoAnual = 28000;
            UserCSVIn usrIn = new UserCSVIn("victor", "1325645", rendimentoAnual);
            aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

            Assertions.assertEquals( base1_aliquota, aliquotaAnualService2.getParcelaADeduzirAliquota());
            Assertions.assertEquals(base1_parcelaADeduzir, aliquotaAnualService2.getPorcentagemAliquota());
        }

        @Test
        void naoDeveRetornarAliquota1() {
            double rendimentoAnual = 30000;

            UserCSVIn usrIn = new UserCSVIn("victor", "1325645", rendimentoAnual);
           aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

            Assertions.assertNotEquals(base1_aliquota, aliquotaAnualService2.getParcelaADeduzirAliquota());
            Assertions.assertNotEquals(base1_parcelaADeduzir, aliquotaAnualService2.getPorcentagemAliquota());
        }
    }

    @Nested()
    @DisplayName("Aliquota 2 using This - 7.5%")
    class Aliquota2 {
        @Test
        void deveRetornarAliquota2() {
            double rendimentoMinimoAnual = 40000;

            UserCSVIn usrIn = new UserCSVIn("victor", "1325645", rendimentoMinimoAnual);
            aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

            Assertions.assertEquals(base2_parcelaADeduzir, aliquotaAnualService2.getParcelaADeduzirAliquota());
            Assertions.assertEquals(base2_aliquota, aliquotaAnualService2.getPorcentagemAliquota());

        }

        @Test
        void naodeveRetornarAliquota2() {
            double rendimentoMinimoAnual = 60000;

            UserCSVIn usrIn = new UserCSVIn("victor", "1325645", rendimentoMinimoAnual);
            aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

            Assertions.assertNotEquals(base2_parcelaADeduzir, aliquotaAnualService2.getParcelaADeduzirAliquota());
            Assertions.assertNotEquals(base2_aliquota, aliquotaAnualService2.getPorcentagemAliquota());

        }

    }

    @Nested()
    @DisplayName("Aliquota 3 using This - 15%")
    class Aliquota3 {
        @Test
        void deveRetornarAliquota3() {
            double rendimentoMinimoAnual = 50000;

            UserCSVIn usrIn = new UserCSVIn("victor", "1325645", rendimentoMinimoAnual);
            aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

            Assertions.assertEquals(base3_parcelaADeduzir, aliquotaAnualService2.getParcelaADeduzirAliquota());
            Assertions.assertEquals(base3_aliquota, aliquotaAnualService2.getPorcentagemAliquota());

        }

        @Test
        void naodeveRetornarAliquota3() {
            double rendimentoMinimoAnual = 80000;

            UserCSVIn usrIn = new UserCSVIn("victor", "1325645", rendimentoMinimoAnual);
            aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

            Assertions.assertNotEquals(base3_parcelaADeduzir, aliquotaAnualService2.getParcelaADeduzirAliquota());
            Assertions.assertNotEquals(base3_aliquota, aliquotaAnualService2.getPorcentagemAliquota());

        }

    }

    @Nested()
    @DisplayName("Aliquota 4 using This - 22.5%")
    class Aliquota4 {
        @Test
        void deveRetornarAliquota4() {
            double rendimentoMinimoAnual = 60000;

            UserCSVIn usrIn = new UserCSVIn("victor", "1325645", rendimentoMinimoAnual);
            aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

            Assertions.assertEquals(base4_parcelaADeduzir, aliquotaAnualService2.getParcelaADeduzirAliquota());
            Assertions.assertEquals(base4_aliquota, aliquotaAnualService2.getPorcentagemAliquota());

        }

        @Test
        void naodeveRetornarAliquota4() {
            double rendimentoMinimoAnual = 100000;

            UserCSVIn usrIn = new UserCSVIn("victor", "1325645", rendimentoMinimoAnual);
           aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

            Assertions.assertNotEquals(base4_parcelaADeduzir, aliquotaAnualService2.getParcelaADeduzirAliquota());
            Assertions.assertNotEquals(base4_aliquota, aliquotaAnualService2.getPorcentagemAliquota());

        }

        @Nested()
        @DisplayName("Aliquota 5 using This - 27.5%")
        class Aliquota5 {
            @Test
            void deveRetornarAliquota5() {
                double rendimentoMinimoAnual = 100000;

                UserCSVIn usrIn = new UserCSVIn("victor", "1325645", rendimentoMinimoAnual);
               aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

                Assertions.assertEquals(base5_parcelaADeduzir, aliquotaAnualService2.getParcelaADeduzirAliquota());
                Assertions.assertEquals(base5_aliquota, aliquotaAnualService2.getPorcentagemAliquota());

            }

            @Test
            void naodeveRetornarAliquota5() {
                double rendimentoMinimoAnual = 1000000000;

                UserCSVIn usrIn = new UserCSVIn("victor", "1325645", rendimentoMinimoAnual);
                aliquotaAnualService2.generateDeducaoAndAliquota2(usrIn);

                Assertions.assertNotEquals(base4_parcelaADeduzir, aliquotaAnualService2.getParcelaADeduzirAliquota());
                Assertions.assertNotEquals(base4_aliquota, aliquotaAnualService2.getPorcentagemAliquota());

            }

        }
    }
}