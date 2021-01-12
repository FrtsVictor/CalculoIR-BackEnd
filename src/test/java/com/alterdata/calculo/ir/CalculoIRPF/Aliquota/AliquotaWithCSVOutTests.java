package com.alterdata.calculo.ir.CalculoIRPF.Aliquota;

import com.alterdata.calculo.ir.CalculoIRPF.modelsCVS.UserCSVIn;
import com.alterdata.calculo.ir.CalculoIRPF.modelsCVS.UserCSVOut;

import com.alterdata.calculo.ir.CalculoIRPF.services.calc.CalcBaseAliqAnualSimplesServiceWithCSVOut;
import org.junit.jupiter.api.*;

import static com.alterdata.calculo.ir.CalculoIRPF.services.calc.CalcBaseAliquota.*;

public class AliquotaWithCSVOutTests {

    private TestInfo testInfo;
    private TestReporter testReporter;
    private CalcBaseAliqAnualSimplesServiceWithCSVOut aliquotaAnualService;
    private UserCSVIn usuarioCSVIn;
    private UserCSVOut usuarioCSVOut;

    @BeforeEach
    void initEach(TestReporter testReporter, TestInfo testInfo) {
        aliquotaAnualService = new CalcBaseAliqAnualSimplesServiceWithCSVOut();
        usuarioCSVIn = new UserCSVIn();
        usuarioCSVOut = new UserCSVOut();

        this.testReporter = testReporter;
        this.testInfo = testInfo;

        testReporter.publishEntry("Running -> " +
                testInfo.getDisplayName() +
                " with tag -> " + testInfo.getTags());
    }



    @Nested
    @DisplayName("Aliquota 1 using This - 0%")
    class Aliquota1 {

        @Test
        void deveRetornarAliquota1() {
            double rendimentoAnual = 28000;

            UserCSVIn usrIn = new UserCSVIn("victor", "1325645", rendimentoAnual);
            UserCSVOut usrOut = aliquotaAnualService.generateDeducaoAndAliquota(usrIn);

            Assertions.assertEquals( base1_aliquota, usrOut.getParcelaDedutivel());
            Assertions.assertEquals(base1_parcelaADeduzir, usrOut.getPorcentagemAliquota());
        }

        @Test
        void naoDeveRetornarAliquota1() {
            double rendimentoAnual = 30000;

            UserCSVIn usrIn = new UserCSVIn("victor", "1325645", rendimentoAnual);
            UserCSVOut usrOut = aliquotaAnualService.generateDeducaoAndAliquota(usrIn);

            Assertions.assertNotEquals(base1_aliquota, usrOut.getParcelaDedutivel());
            Assertions.assertNotEquals(base1_parcelaADeduzir, usrOut.getPorcentagemAliquota());
        }
    }

    @Nested()
    @DisplayName("Aliquota 2 using This - 7.5%")
    class Aliquota2 {
        @Test
        void deveRetornarAliquota2() {
            double rendimentoMinimoAnual = 40000;

            UserCSVIn usrIn = new UserCSVIn("victor", "1325645", rendimentoMinimoAnual);
            UserCSVOut usrOut = aliquotaAnualService.generateDeducaoAndAliquota(usrIn);

            Assertions.assertEquals(base2_parcelaADeduzir, usrOut.getParcelaDedutivel());
            Assertions.assertEquals(base2_aliquota, usrOut.getPorcentagemAliquota());
        }

        @Test
        void naodeveRetornarAliquota2() {
            double rendimentoMinimoAnual = 60000;

            UserCSVIn usrIn = new UserCSVIn("victor", "1325645", rendimentoMinimoAnual);
            UserCSVOut usrOut = aliquotaAnualService.generateDeducaoAndAliquota(usrIn);

            Assertions.assertNotEquals(base2_parcelaADeduzir, usrOut.getParcelaDedutivel());
            Assertions.assertNotEquals(base2_aliquota, usrOut.getPorcentagemAliquota());
        }

    }

    @Nested()
    @DisplayName("Aliquota 3 using This - 15%")
    class Aliquota3 {
        @Test
        void deveRetornarAliquota3() {
            double rendimentoMinimoAnual = 50000;

            UserCSVIn usrIn = new UserCSVIn("victor", "1325645", rendimentoMinimoAnual);
            UserCSVOut usrOut = aliquotaAnualService.generateDeducaoAndAliquota(usrIn);

            Assertions.assertEquals(base3_parcelaADeduzir, usrOut.getParcelaDedutivel());
            Assertions.assertEquals(base3_aliquota, usrOut.getPorcentagemAliquota());
        }

        @Test
        void naodeveRetornarAliquota3() {
            double rendimentoMinimoAnual = 80000;

            UserCSVIn usrIn = new UserCSVIn("victor", "1325645", rendimentoMinimoAnual);
            UserCSVOut usrOut = aliquotaAnualService.generateDeducaoAndAliquota(usrIn);

            Assertions.assertNotEquals(base3_parcelaADeduzir, usrOut.getParcelaDedutivel());
            Assertions.assertNotEquals(base3_aliquota, usrOut.getPorcentagemAliquota());
        }

    }

    @Nested()
    @DisplayName("Aliquota 4 using This - 22.5%")
    class Aliquota4 {
        @Test
        void deveRetornarAliquota4() {
            double rendimentoMinimoAnual = 60000;

            UserCSVIn usrIn = new UserCSVIn("victor", "1325645", rendimentoMinimoAnual);
            UserCSVOut usrOut = aliquotaAnualService.generateDeducaoAndAliquota(usrIn);

            Assertions.assertEquals(base4_parcelaADeduzir, usrOut.getParcelaDedutivel());
            Assertions.assertEquals(base4_aliquota, usrOut.getPorcentagemAliquota());
        }

        @Test
        void naodeveRetornarAliquota4() {
            double rendimentoMinimoAnual = 100000;

            UserCSVIn usrIn = new UserCSVIn("victor", "1325645", rendimentoMinimoAnual);
            UserCSVOut usrOut = aliquotaAnualService.generateDeducaoAndAliquota(usrIn);

            Assertions.assertNotEquals(base4_parcelaADeduzir, usrOut.getParcelaDedutivel());
            Assertions.assertNotEquals(base4_aliquota, usrOut.getPorcentagemAliquota());
        }

        @Nested()
        @DisplayName("Aliquota 5 using This - 27.5%")
        class Aliquota5 {
            @Test
            void deveRetornarAliquota5() {
                double rendimentoMinimoAnual = 100000;

                UserCSVIn usrIn = new UserCSVIn("victor", "1325645", rendimentoMinimoAnual);
                UserCSVOut usrOut = aliquotaAnualService.generateDeducaoAndAliquota(usrIn);

                Assertions.assertEquals(base5_parcelaADeduzir, usrOut.getParcelaDedutivel());
                Assertions.assertEquals(base5_aliquota, usrOut.getPorcentagemAliquota());
            }

            @Test
            void naodeveRetornarAliquota5() {
                double rendimentoMinimoAnual = 1000000000;

                UserCSVIn usrIn = new UserCSVIn("victor", "1325645", rendimentoMinimoAnual);
                UserCSVOut usrOut = aliquotaAnualService.generateDeducaoAndAliquota(usrIn);

                Assertions.assertNotEquals(base4_parcelaADeduzir, usrOut.getParcelaDedutivel());
                Assertions.assertNotEquals(base4_aliquota, usrOut.getPorcentagemAliquota());
            }

        }
    }
}