package com.alterdata.calculo.irpf.service.calculo_irpf;

import com.alterdata.calculo.irpf.models.irpf.UserIRPFRequest;
import com.alterdata.calculo.irpf.services.calculo_anual.CalcAliqAnualSimplesIRPFService;
import org.junit.jupiter.api.*;

import static com.alterdata.calculo.irpf.services.calculo_anual.constants.TetoSalarialAnualAliquotaIRPF.*;
import static com.alterdata.calculo.irpf.services.calculo_anual.constants.AliquotaAnualIRPF.*;

@DisplayName("Executando testes unitarios CalcIRPF generateAliquota()")
public class AliquotaTests {

    private TestInfo testInfo;
    private TestReporter testReporter;

    private CalcAliqAnualSimplesIRPFService aliqAnualService;

    private UserIRPFRequest usuarioCSVIn;

    @BeforeEach
    void initEach(TestReporter testReporter, TestInfo testInfo) {
        aliqAnualService = new CalcAliqAnualSimplesIRPFService();
        usuarioCSVIn = new UserIRPFRequest();


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
            double rendimentoAnual = TETO_DEDUCAO_SALARIAL.value();

            UserIRPFRequest usrIn = new UserIRPFRequest("Test", rendimentoAnual);
            aliqAnualService.generateAliquota(usrIn);

            Assertions.assertEquals(aliqAnualService.getDeducaoSimplificada(), TETO_DEDUCAO_SIMPLIFICADA.value());
        }

        @Test
        @DisplayName("Nao deve retornar TetoDeducaoSimplificada")
        public void nao_deve_retornar_TetoDeducaoSimplificada() {
            double rendimentoAnual = TETO_DEDUCAO_SALARIAL.value() - 0.1;

            UserIRPFRequest usrIn = new UserIRPFRequest("Test", rendimentoAnual);
            aliqAnualService.generateAliquota(usrIn);

            Assertions.assertNotEquals(aliqAnualService.getDeducaoSimplificada(), TETO_DEDUCAO_SIMPLIFICADA.value());
        }

    }

    @Nested
    @DisplayName("Aliquota 1 IRPF: 0% ")
    class Aliquota1 {

        @Test
        @DisplayName("Deve retornar Aliquota1 para valor inicial Aliquota1")
        void deve_retornar_Aliquota1_para_valor_inicial() {
            double rendimentoAnual = TETO_SALARIAL1_INICIAL.value();

            UserIRPFRequest usrIn = new UserIRPFRequest("Test", rendimentoAnual);
            aliqAnualService.generateAliquota(usrIn);

            Assertions.assertEquals(FAIXA_SALARIAL1_ALIQUOTA.value(), aliqAnualService.getPorcentagemAliquota());
            Assertions.assertEquals(FAIXA_SALARIAL1_PARCELA_DEDUTIVEL.value(), aliqAnualService.getParcelaADeduzirAliquota());
        }

        @Test
        @DisplayName("Nao Deve retornar Aliquota1 para valores superioes Aliquota1")
        void nao_Deve_retornar_Aliquota1_para_valores_superioes() {
            double rendimentoAnual = TETO_SALARIAL1_INICIAL.value() + 0.1;
            System.out.println(rendimentoAnual);
            UserIRPFRequest usrIn = new UserIRPFRequest("Test", rendimentoAnual);
            aliqAnualService.generateAliquota(usrIn);

            Assertions.assertNotEquals(FAIXA_SALARIAL1_ALIQUOTA.value(), aliqAnualService.getParcelaADeduzirAliquota());
            Assertions.assertNotEquals(FAIXA_SALARIAL1_PARCELA_DEDUTIVEL.value(), aliqAnualService.getPorcentagemAliquota());
        }

    }

    @Nested()
    @DisplayName("Aliquota 2 IRPF: 7.5%")
    class Aliquota2 {
        @Test
        @DisplayName("Deve retornar Aliquota2 para valor inicial Aliquota2")
        void deve_retornar_Aliquota2_para_valor_inicial() {
            double rendimentoAnual = TETO_SALARIAL2_INICIAL.value();

            UserIRPFRequest usrIn = new UserIRPFRequest("Test", rendimentoAnual);
            aliqAnualService.generateAliquota(usrIn);

            Assertions.assertEquals(FAIXA_SALARIAL2_PARCELA_DEDUTIVEL.value(),
                    aliqAnualService.getParcelaADeduzirAliquota());
            Assertions.assertEquals(FAIXA_SALARIAL2_ALIQUOTA.value(), aliqAnualService.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Deve retornar Aliquota2 para valor final Aliquota2")
        void deve_retornar_Aliquota2_para_valor_final() {
            double rendimentoAnual = TETO_SALARIAL2_FINAL.value();

            UserIRPFRequest usrIn = new UserIRPFRequest("Test", rendimentoAnual);
            aliqAnualService.generateAliquota(usrIn);

            Assertions.assertEquals(FAIXA_SALARIAL2_PARCELA_DEDUTIVEL.value(),
                    aliqAnualService.getParcelaADeduzirAliquota());
            Assertions.assertEquals(FAIXA_SALARIAL2_ALIQUOTA.value(), aliqAnualService.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Nao deve retornar Aliquota2 para valores superioes Aliquota2")
        void nao_deve_retornar_Aliquota2_para_valores_superioes() {
            double rendimentoAnual = TETO_SALARIAL2_FINAL.value() + 0.1;

            UserIRPFRequest usrIn = new UserIRPFRequest("Test", rendimentoAnual);
            aliqAnualService.generateAliquota(usrIn);

            Assertions.assertNotEquals(FAIXA_SALARIAL2_PARCELA_DEDUTIVEL.value(),
                    aliqAnualService.getParcelaADeduzirAliquota());
            Assertions.assertNotEquals(FAIXA_SALARIAL2_ALIQUOTA.value(), aliqAnualService.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Nao deve retornar Aliquota2 para valores inferioes Aliquota2")
        void nao_deve_retornar_Aliquota2_para_valores_inferioes() {
            double rendimentoAnual = TETO_SALARIAL2_INICIAL.value() - 0.1;

            UserIRPFRequest usrIn = new UserIRPFRequest("Test", rendimentoAnual);
            aliqAnualService.generateAliquota(usrIn);

            Assertions.assertNotEquals(FAIXA_SALARIAL2_PARCELA_DEDUTIVEL.value(),
                    aliqAnualService.getParcelaADeduzirAliquota());
            Assertions.assertNotEquals(FAIXA_SALARIAL2_ALIQUOTA.value(), aliqAnualService.getPorcentagemAliquota());
        }

    }

    @Nested()
    @DisplayName("Aliquota 3 IRPF: 15%")
    class Aliquota3 {
        @Test
        @DisplayName("Deve retornar Aliquota3 para valor inicial Aliquota3")
        void deve_retornar_Aliquota3_para_valor_inicial() {
            double rendimentoAnual = TETO_SALARIAL3_INICIAL.value();

            UserIRPFRequest usrIn = new UserIRPFRequest("Test", rendimentoAnual);
            aliqAnualService.generateAliquota(usrIn);

            Assertions.assertEquals(FAIXA_SALARIAL3_PARCELA_DEDUTIVEL.value(),
                    aliqAnualService.getParcelaADeduzirAliquota());
            Assertions.assertEquals(FAIXA_SALARIAL3_ALIQUOTA.value(), aliqAnualService.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Deve retornar Aliquota3 para valor final Aliquota3")
        void deve_retornar_Aliquota3_para_valor_final() {
            double rendimentoAnual = TETO_SALARIAL3_FINAL.value();

            UserIRPFRequest usrIn = new UserIRPFRequest("Test", rendimentoAnual);
            aliqAnualService.generateAliquota(usrIn);

            Assertions.assertEquals(FAIXA_SALARIAL3_PARCELA_DEDUTIVEL.value(),
                    aliqAnualService.getParcelaADeduzirAliquota());
            Assertions.assertEquals(FAIXA_SALARIAL3_ALIQUOTA.value(), aliqAnualService.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Nao deve retornar Aliquota3 para valores superioes Aliquota3")
        void nao_deve_retornar_Aliquota3_para_valores_superioes() {
            double rendimentoAnual = TETO_SALARIAL3_FINAL.value() + 0.1;

            UserIRPFRequest usrIn = new UserIRPFRequest("Test", rendimentoAnual);
            aliqAnualService.generateAliquota(usrIn);

            Assertions.assertNotEquals(FAIXA_SALARIAL3_PARCELA_DEDUTIVEL.value(),
                    aliqAnualService.getParcelaADeduzirAliquota());
            Assertions.assertNotEquals(FAIXA_SALARIAL3_ALIQUOTA.value(), aliqAnualService.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Nao deve retornar Aliquota3 para valores inferioes Aliquota3")
        void nao_deve_retornar_Aliquota3_para_valores_inferioes() {
            double rendimentoAnual = TETO_SALARIAL3_INICIAL.value() - 0.1;

            UserIRPFRequest usrIn = new UserIRPFRequest("Test", rendimentoAnual);
            aliqAnualService.generateAliquota(usrIn);

            Assertions.assertNotEquals(FAIXA_SALARIAL3_PARCELA_DEDUTIVEL.value(),
                    aliqAnualService.getParcelaADeduzirAliquota());
            Assertions.assertNotEquals(FAIXA_SALARIAL3_ALIQUOTA.value(), aliqAnualService.getPorcentagemAliquota());
        }

    }

    @Nested()
    @DisplayName("Aliquota 4 IRPF 22.5%")
    class Aliquota4 {
        @Test
        @DisplayName("Deve retornar Aliquota4 para valor inicial Aliquota4")
        void deve_retornar_Aliquota4_para_valor_inicial() {
            double rendimentoAnual = TETO_SALARIAL4_INICIAL.value();

            UserIRPFRequest usrIn = new UserIRPFRequest("Test", rendimentoAnual);
            aliqAnualService.generateAliquota(usrIn);

            Assertions.assertEquals(FAIXA_SALARIAL4_PARCELA_DEDUTIVEL.value(), aliqAnualService.getParcelaADeduzirAliquota());
            Assertions.assertEquals(FAIXA_SALARIAL4_ALIQUOTA.value(), aliqAnualService.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Deve retornar Aliquota4 para valor final Aliquota4")
        void deve_retornar_Aliquota4_para_valor_final() {
            double rendimentoAnual = TETO_SALARIAL4_FINAL.value();

            UserIRPFRequest usrIn = new UserIRPFRequest("Test", rendimentoAnual);
            aliqAnualService.generateAliquota(usrIn);

            Assertions.assertEquals(FAIXA_SALARIAL4_PARCELA_DEDUTIVEL.value(), aliqAnualService.getParcelaADeduzirAliquota());
            Assertions.assertEquals(FAIXA_SALARIAL4_ALIQUOTA.value(), aliqAnualService.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Nao deve retornar Aliquota4 para valores superioes Aliquota4")
        void nao_deve_retornar_Aliquota4_para_valores_superioes() {
            double rendimentoAnual = TETO_SALARIAL4_FINAL.value() + 0.1;

            UserIRPFRequest usrIn = new UserIRPFRequest("Test", rendimentoAnual);
            aliqAnualService.generateAliquota(usrIn);

            Assertions.assertNotEquals(FAIXA_SALARIAL4_PARCELA_DEDUTIVEL.value(),
                    aliqAnualService.getParcelaADeduzirAliquota());
            Assertions.assertNotEquals(FAIXA_SALARIAL4_ALIQUOTA.value(), aliqAnualService.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Nao deve retornar Aliquota4 para valores inferioes Aliquota4")
        void nao_deve_retornar_Aliquota4_para_valores_inferioes() {
            double rendimentoAnual = TETO_SALARIAL4_INICIAL.value() - 0.1;

            UserIRPFRequest usrIn = new UserIRPFRequest("Test", rendimentoAnual);
            aliqAnualService.generateAliquota(usrIn);

            Assertions.assertNotEquals(FAIXA_SALARIAL4_PARCELA_DEDUTIVEL.value(),
                    aliqAnualService.getParcelaADeduzirAliquota());
            Assertions.assertNotEquals(FAIXA_SALARIAL4_ALIQUOTA.value(), aliqAnualService.getPorcentagemAliquota());
        }

        @Nested()
        @DisplayName("Aliquota 5 IRPF: 27.5%")
        class Aliquota5 {
            @Test
            @DisplayName("Deve retornar Aliquota5 para valor inicial Aliquota5")
            void deve_retornar_Aliquota5_para_valor_inicial() {
                double rendimentoAnual = TETO_SALARIAL5_INICIAL.value();

                UserIRPFRequest usrIn = new UserIRPFRequest("Test", rendimentoAnual);
                aliqAnualService.generateAliquota(usrIn);

                Assertions.assertEquals(FAIXA_SALARIAL5_PARCELA_DEDUTIVEL.value(),
                        aliqAnualService.getParcelaADeduzirAliquota());
                Assertions.assertEquals(FAIXA_SALARIAL5_ALIQUOTA.value(), aliqAnualService.getPorcentagemAliquota());
            }

            @Test
            @DisplayName("Nao deve retornar Aliquota5 para valores inferioes Aliquota5")
            void nao_deve_retornar_Aliquota5_para_valores_inferioes() {
                double rendimentoAnual = TETO_SALARIAL5_INICIAL.value() - 0.1;

                UserIRPFRequest usrIn = new UserIRPFRequest("Test", rendimentoAnual);
                aliqAnualService.generateAliquota(usrIn);

                Assertions.assertNotEquals(FAIXA_SALARIAL5_PARCELA_DEDUTIVEL.value(),
                        aliqAnualService.getParcelaADeduzirAliquota());

                Assertions.assertNotEquals(FAIXA_SALARIAL5_ALIQUOTA.value(), aliqAnualService.getPorcentagemAliquota());
            }
        }
    }
}