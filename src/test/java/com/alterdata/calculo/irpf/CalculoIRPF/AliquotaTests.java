package com.alterdata.calculo.irpf.CalculoIRPF;

import com.alterdata.calculo.irpf.models.UserIRPFIn;
import com.alterdata.calculo.irpf.services.calcAnualIRPF.CalcAliqAnualSimplesIRPFService;
import org.junit.jupiter.api.*;

import static com.alterdata.calculo.irpf.services.calcAnualIRPF.BaseAnualIRPF.*;
import static com.alterdata.calculo.irpf.services.calcAnualIRPF.TetoSalarioBrutoALiq.*;

@DisplayName("Executando testes generateAliquota")
public class AliquotaTests {

    private TestInfo testInfo;
    private TestReporter testReporter;

    private CalcAliqAnualSimplesIRPFService aliqAnualService;

    private UserIRPFIn usuarioCSVIn;

    @BeforeEach
    void initEach(TestReporter testReporter, TestInfo testInfo) {
        aliqAnualService = new CalcAliqAnualSimplesIRPFService();
        usuarioCSVIn = new UserIRPFIn();


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
        @DisplayName("Deve retornar TetoDeducaoSimplificada")
        public void deve_retornar_TetoDeducaoSimplificada() {
            double rendimentoAnual = teto_deducao;

            UserIRPFIn usrIn = new UserIRPFIn("victor", rendimentoAnual);
            aliqAnualService.generateAliquota(usrIn);

            Assertions.assertEquals(aliqAnualService.getDeducaoSimplificada(), tetoDeducaoSimplificada);
        }

        @Test
        @DisplayName("Nao deve retornar TetoDeducaoSimplificada")
        public void nao_deve_retornar_TetoDeducaoSimplificada() {
            double rendimentoAnual = teto_deducao - 0.1;

            UserIRPFIn usrIn = new UserIRPFIn("victor", rendimentoAnual);
            aliqAnualService.generateAliquota(usrIn);

            Assertions.assertNotEquals(aliqAnualService.getDeducaoSimplificada(), tetoDeducaoSimplificada);
        }

    }

    @Nested
    @DisplayName("Aliquota 1 IRPF: 0% ")
    class Aliquota1 {

        @Test
        @DisplayName("Deve retornar Aliquota1 para valor inicial Aliquota1")
        void deve_retornar_Aliquota1_para_valor_inicial() {
            double rendimentoAnual = ali1_inicial;

            UserIRPFIn usrIn = new UserIRPFIn("victor", rendimentoAnual);
            aliqAnualService.generateAliquota(usrIn);

            Assertions.assertEquals( base1_aliquota, aliqAnualService.getPorcentagemAliquota());
            Assertions.assertEquals(base1_parcelaADeduzir, aliqAnualService.getParcelaADeduzirAliquota());
        }

        @Test
        @DisplayName("Nao Deve retornar Aliquota1 para valores superioes Aliquota1")
        void nao_Deve_retornar_Aliquota1_para_valores_superioes() {
            double rendimentoAnual = ali1_inicial + 0.1;
            System.out.println(rendimentoAnual);
            UserIRPFIn usrIn = new UserIRPFIn("victor", rendimentoAnual);
            aliqAnualService.generateAliquota(usrIn);

            Assertions.assertNotEquals(base1_aliquota, aliqAnualService.getParcelaADeduzirAliquota());
            Assertions.assertNotEquals(base1_parcelaADeduzir, aliqAnualService.getPorcentagemAliquota());
        }

    }

    @Nested()
    @DisplayName("Aliquota 2 IRPF: 7.5%")
    class Aliquota2 {
        @Test
        @DisplayName("Deve retornar Aliquota2 para valor inicial Aliquota2")
        void deve_retornar_Aliquota2_para_valor_inicial() {
            double rendimentoAnual = ali2_inicial;

            UserIRPFIn usrIn = new UserIRPFIn("victor", rendimentoAnual);
            aliqAnualService.generateAliquota(usrIn);

            Assertions.assertEquals(base2_parcelaADeduzir, aliqAnualService.getParcelaADeduzirAliquota());
            Assertions.assertEquals(base2_aliquota, aliqAnualService.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Deve retornar Aliquota2 para valor final Aliquota2")
        void deve_retornar_Aliquota2_para_valor_final() {
            double rendimentoAnual = ali2_final;

            UserIRPFIn usrIn = new UserIRPFIn("victor", rendimentoAnual);
            aliqAnualService.generateAliquota(usrIn);

            Assertions.assertEquals(base2_parcelaADeduzir, aliqAnualService.getParcelaADeduzirAliquota());
            Assertions.assertEquals(base2_aliquota, aliqAnualService.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Nao deve retornar Aliquota2 para valores superioes Aliquota2")
        void nao_deve_retornar_Aliquota2_para_valores_superioes() {
            double rendimentoAnual = ali2_final + 0.1;

            UserIRPFIn usrIn = new UserIRPFIn("victor", rendimentoAnual);
            aliqAnualService.generateAliquota(usrIn);

            Assertions.assertNotEquals(base2_parcelaADeduzir, aliqAnualService.getParcelaADeduzirAliquota());
            Assertions.assertNotEquals(base2_aliquota, aliqAnualService.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Nao deve retornar Aliquota2 para valores inferioes Aliquota2")
        void nao_deve_retornar_Aliquota2_para_valores_inferioes() {
            double rendimentoAnual = ali2_inicial - 0.1;

            UserIRPFIn usrIn = new UserIRPFIn("victor", rendimentoAnual);
            aliqAnualService.generateAliquota(usrIn);

            Assertions.assertNotEquals(base2_parcelaADeduzir, aliqAnualService.getParcelaADeduzirAliquota());
            Assertions.assertNotEquals(base2_aliquota, aliqAnualService.getPorcentagemAliquota());
        }

    }

    @Nested()
    @DisplayName("Aliquota 3 IRPF: 15%")
    class Aliquota3 {
        @Test
        @DisplayName("Deve retornar Aliquota3 para valor inicial Aliquota3")
        void deve_retornar_Aliquota3_para_valor_inicial() {
            double rendimentoAnual = aliq3_inicial;

            UserIRPFIn usrIn = new UserIRPFIn("victor", rendimentoAnual);
            aliqAnualService.generateAliquota(usrIn);

            Assertions.assertEquals(base3_parcelaADeduzir, aliqAnualService.getParcelaADeduzirAliquota());
            Assertions.assertEquals(base3_aliquota, aliqAnualService.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Deve retornar Aliquota3 para valor final Aliquota3")
        void deve_retornar_Aliquota3_para_valor_final() {
            double rendimentoAnual = aliq3_final;

            UserIRPFIn usrIn = new UserIRPFIn("victor", rendimentoAnual);
            aliqAnualService.generateAliquota(usrIn);

            Assertions.assertEquals(base3_parcelaADeduzir, aliqAnualService.getParcelaADeduzirAliquota());
            Assertions.assertEquals(base3_aliquota, aliqAnualService.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Nao deve retornar Aliquota3 para valores superioes Aliquota3")
        void nao_deve_retornar_Aliquota3_para_valores_superioes() {
            double rendimentoAnual = aliq3_final + 0.1;

            UserIRPFIn usrIn = new UserIRPFIn("victor", rendimentoAnual);
            aliqAnualService.generateAliquota(usrIn);

            Assertions.assertNotEquals(base3_parcelaADeduzir, aliqAnualService.getParcelaADeduzirAliquota());
            Assertions.assertNotEquals(base3_aliquota, aliqAnualService.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Nao deve retornar Aliquota3 para valores inferioes Aliquota3")
        void nao_deve_retornar_Aliquota3_para_valores_inferioes() {
            double rendimentoAnual = aliq3_inicial - 0.1;

            UserIRPFIn usrIn = new UserIRPFIn("victor", rendimentoAnual);
            aliqAnualService.generateAliquota(usrIn);

            Assertions.assertNotEquals(base3_parcelaADeduzir, aliqAnualService.getParcelaADeduzirAliquota());
            Assertions.assertNotEquals(base3_aliquota, aliqAnualService.getPorcentagemAliquota());
        }

    }

    @Nested()
    @DisplayName("Aliquota 4 IRPF 22.5%")
    class Aliquota4 {
        @Test
        @DisplayName("Deve retornar Aliquota4 para valor inicial Aliquota4")
        void deve_retornar_Aliquota4_para_valor_inicial() {
            double rendimentoAnual = aliq4_inicial;

            UserIRPFIn usrIn = new UserIRPFIn("victor", rendimentoAnual);
            aliqAnualService.generateAliquota(usrIn);

            Assertions.assertEquals(base4_parcelaADeduzir, aliqAnualService.getParcelaADeduzirAliquota());
            Assertions.assertEquals(base4_aliquota, aliqAnualService.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Deve retornar Aliquota4 para valor final Aliquota4")
        void deve_retornar_Aliquota4_para_valor_final() {
            double rendimentoAnual = aliq4_final;

            UserIRPFIn usrIn = new UserIRPFIn("victor", rendimentoAnual);
            aliqAnualService.generateAliquota(usrIn);

            Assertions.assertEquals(base4_parcelaADeduzir, aliqAnualService.getParcelaADeduzirAliquota());
            Assertions.assertEquals(base4_aliquota, aliqAnualService.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Nao deve retornar Aliquota4 para valores superioes Aliquota4")
        void nao_deve_retornar_Aliquota4_para_valores_superioes() {
            double rendimentoAnual = aliq4_final + 0.1;

            UserIRPFIn usrIn = new UserIRPFIn("victor", rendimentoAnual);
            aliqAnualService.generateAliquota(usrIn);

            Assertions.assertNotEquals(base4_parcelaADeduzir, aliqAnualService.getParcelaADeduzirAliquota());
            Assertions.assertNotEquals(base4_aliquota, aliqAnualService.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Nao deve retornar Aliquota4 para valores inferioes Aliquota4")
        void nao_deve_retornar_Aliquota4_para_valores_inferioes() {
            double rendimentoAnual = aliq4_inicial - 0.1;

            UserIRPFIn usrIn = new UserIRPFIn("victor", rendimentoAnual);
            aliqAnualService.generateAliquota(usrIn);

            Assertions.assertNotEquals(base4_parcelaADeduzir, aliqAnualService.getParcelaADeduzirAliquota());
            Assertions.assertNotEquals(base4_aliquota, aliqAnualService.getPorcentagemAliquota());
        }

        @Nested()
        @DisplayName("Aliquota 5 IRPF: 27.5%")
        class Aliquota5 {
            @Test
            @DisplayName("Deve retornar Aliquota5 para valor inicial Aliquota5")
            void deve_retornar_Aliquota5_para_valor_inicial() {
                double rendimentoAnual = aliq5_inicial;

                UserIRPFIn usrIn = new UserIRPFIn("victor", rendimentoAnual);
                aliqAnualService.generateAliquota(usrIn);

                Assertions.assertEquals(base5_parcelaADeduzir, aliqAnualService.getParcelaADeduzirAliquota());
                Assertions.assertEquals(base5_aliquota, aliqAnualService.getPorcentagemAliquota());
            }

            @Test
            @DisplayName("Nao deve retornar Aliquota5 para valores inferioes Aliquota5")
            void nao_deve_retornar_Aliquota5_para_valores_inferioes() {
                double rendimentoAnual = aliq5_inicial - 0.1;

                UserIRPFIn usrIn = new UserIRPFIn("victor", rendimentoAnual);
                aliqAnualService.generateAliquota(usrIn);

                Assertions.assertNotEquals(base5_parcelaADeduzir, aliqAnualService.getParcelaADeduzirAliquota());
                Assertions.assertNotEquals(base5_aliquota, aliqAnualService.getPorcentagemAliquota());
            }
        }
    }
}