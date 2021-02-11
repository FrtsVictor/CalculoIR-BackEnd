package com.alterdata.calculo.irpf.service.calculo_inss;

import com.alterdata.calculo.irpf.models.inss.InssRequest;
import com.alterdata.calculo.irpf.services.calculo_mensal.InssService;
import org.junit.jupiter.api.*;

import static com.alterdata.calculo.irpf.services.calculo_mensal.constants.AliquotaMensalINSS.*;


@DisplayName("Executando testes unitarios CalcINSS gerarFaixaSalarial()")
public class GerarFaixaSalarialTests {

    private InssService inssService;
    private InssRequest inssRequest;

    @BeforeEach
    void gerarFaixaSalarial_initEach() {
        inssService = new InssService();
        inssRequest = new InssRequest();
    }

    @Nested
    @DisplayName("Faixa salarial 1 INSS")
    class Faixa1 {

        @Test
        @DisplayName("Deve retornar Faixa1 para valor final Faixa1")
        void gerarFaixaSalarial_gerarFaixaSalarial_deve_retornar_Faixa1_para_valor_final() {
            double salarioBrutoMensal = FAIXA_SALARIAL1_VALOR_FINAL.value();

            InssRequest userliq = new InssRequest("Test", salarioBrutoMensal);
            inssService.gerarFaixaSalarial(userliq.getSalarioMensalBruto());

            Assertions.assertEquals(FAIXA_SALARIAL1_ALIQUOTA.value(), inssService.getPorcentagemAliquota());
            Assertions.assertEquals(FAIXA_SALARIAL1_PARCELA_DEDUTIVEL.value(), inssService.getParcelaADeduzir());
        }

        @Test
        @DisplayName("Nao deve retornar Faixa1 para valores superioes a Faixa1")
        void gerarFaixaSalarial_nao_deve_retornar_Faixa1_para_valores_superioes() {
            double salarioBrutoMensal = FAIXA_SALARIAL1_VALOR_FINAL.value() + 0.1;

            InssRequest userliq = new InssRequest("Test", salarioBrutoMensal);
            inssService.gerarFaixaSalarial(userliq.getSalarioMensalBruto());

            Assertions.assertNotEquals(FAIXA_SALARIAL1_ALIQUOTA.value(), inssService.getParcelaADeduzir());
            Assertions.assertNotEquals(FAIXA_SALARIAL1_PARCELA_DEDUTIVEL.value(), inssService.getPorcentagemAliquota());
        }

    }

    @Nested()
    @DisplayName("Faixa salarial 2 INSS")
    class Faixa2 {
        @Test
        @DisplayName("Deve retornar Faixa2 para valor final Faixa2")
        void gerarFaixaSalarial_deve_retornar_Faixa2_para_valor_final() {
            double salarioBrutoMensal = FAIXA_SALARIAL2_VALOR_FINAL.value();

            InssRequest userliq = new InssRequest("Test", salarioBrutoMensal);
            inssService.gerarFaixaSalarial(userliq.getSalarioMensalBruto());

            Assertions.assertEquals(FAIXA_SALARIAL2_PARCELA_DEDUTIVEL.value(), inssService.getParcelaADeduzir());
            Assertions.assertEquals(FAIXA_SALARIAL2_ALIQUOTA.value(), inssService.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Deve retornar Faixa2 para valor inicial Faixa2")
        void gerarFaixaSalarial_deve_retornar_Faixa2_para_valor_inicial() {
            double salarioBrutoMensal = FAIXA_SALARIAL2_VALOR_INICIAL.value();

            InssRequest userliq = new InssRequest("Test", salarioBrutoMensal);
            inssService.gerarFaixaSalarial(userliq.getSalarioMensalBruto());

            Assertions.assertEquals(FAIXA_SALARIAL2_PARCELA_DEDUTIVEL.value(), inssService.getParcelaADeduzir());
            Assertions.assertEquals(FAIXA_SALARIAL2_ALIQUOTA.value(), inssService.getPorcentagemAliquota());
        }


        @Test
        @DisplayName("Nao deve retornar Faixa2 paravalores superioes a Faixa2")
        void gerarFaixaSalarial_nao_deve_retornar_Faixa2_para_valores_superioes() {
            double salarioBrutoMensal = FAIXA_SALARIAL2_VALOR_FINAL.value() + 0.1;

            InssRequest userliq = new InssRequest("Test", salarioBrutoMensal);
            inssService.gerarFaixaSalarial(userliq.getSalarioMensalBruto());

            Assertions.assertNotEquals(FAIXA_SALARIAL2_PARCELA_DEDUTIVEL.value(), inssService.getParcelaADeduzir());
            Assertions.assertNotEquals(FAIXA_SALARIAL2_ALIQUOTA.value(), inssService.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Nao deve retornar Faixa2 para valores inferiores a Faixa2")
        void gerarFaixaSalarial_nao_deve_retornar_Faixa2_para_valores_inferiores() {
            double salarioBrutoMensal = FAIXA_SALARIAL2_VALOR_INICIAL.value() - 0.1;

            InssRequest userliq = new InssRequest("Test", salarioBrutoMensal);
            inssService.gerarFaixaSalarial(userliq.getSalarioMensalBruto());

            Assertions.assertNotEquals(FAIXA_SALARIAL2_PARCELA_DEDUTIVEL.value(), inssService.getParcelaADeduzir());
            Assertions.assertNotEquals(FAIXA_SALARIAL2_ALIQUOTA.value(), inssService.getPorcentagemAliquota());
        }

    }

    @Nested()
    @DisplayName("Faixa Salarial 3 INSS")
    class Faixa3 {
        @Test
        @DisplayName("Deve retornar Faixa3 para valor final Faixa3")
        void gerarFaixaSalarial_deve_retornar_Faixa3_para_valor_final() {
            double salarioBrutoMensal = FAIXA_SALARIAL3_VALOR_FINAL.value();

            InssRequest userliq = new InssRequest("Test", salarioBrutoMensal);
            inssService.gerarFaixaSalarial(userliq.getSalarioMensalBruto());

            Assertions.assertEquals(FAIXA_SALARIAL3_PARCELA_DEDUTIVEL.value(), inssService.getParcelaADeduzir());
            Assertions.assertEquals(FAIXA_SALARIAL3_ALIQUOTA.value(), inssService.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Deve retornar Faixa3 para valor inicial FAIXA_SALARIAL3")
        void gerarFaixaSalarial_deve_retornar_Faixa3_para_valor_inicial() {
            double salarioBrutoMensal = FAIXA_SALARIAL3_VALOR_INICIAL.value();

            InssRequest userliq = new InssRequest("Test", salarioBrutoMensal);
            inssService.gerarFaixaSalarial(userliq.getSalarioMensalBruto());

            Assertions.assertEquals(FAIXA_SALARIAL3_PARCELA_DEDUTIVEL.value(), inssService.getParcelaADeduzir());
            Assertions.assertEquals(FAIXA_SALARIAL3_ALIQUOTA.value(), inssService.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Nao deve retornar Faixa3 para valores superioes a Faixa3")
        void gerarFaixaSalarial_nao_deve_retornar_Faixa3_para_valores_superioes() {
            double salarioBrutoMensal = FAIXA_SALARIAL3_VALOR_FINAL.value() + 0.1;

            InssRequest userliq = new InssRequest("Test", salarioBrutoMensal);
            inssService.gerarFaixaSalarial(userliq.getSalarioMensalBruto());

            Assertions.assertNotEquals(FAIXA_SALARIAL3_PARCELA_DEDUTIVEL.value(), inssService.getParcelaADeduzir());
            Assertions.assertNotEquals(FAIXA_SALARIAL3_ALIQUOTA.value(), inssService.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Nao deve retornar Faixa3 para valores inferiores a Faixa3")
        void gerarFaixaSalarial_nao_deve_retornar_Faixa3_para_valores_inferiores() {
            double salarioBrutoMensal = FAIXA_SALARIAL3_VALOR_INICIAL.value() - 0.1;

            InssRequest userliq = new InssRequest("Test", salarioBrutoMensal);
            inssService.gerarFaixaSalarial(userliq.getSalarioMensalBruto());

            Assertions.assertNotEquals(FAIXA_SALARIAL3_PARCELA_DEDUTIVEL.value(), inssService.getParcelaADeduzir());
            Assertions.assertNotEquals(FAIXA_SALARIAL3_ALIQUOTA.value(), inssService.getPorcentagemAliquota());
        }

    }

    @Nested()
    @DisplayName("Faixa salarial 4 INSS")
    class Faixa4 {
        @Test
        @DisplayName("Deve retornar Faixa4 para valor final Faixa4")
        void gerarFaixaSalarial_deve_retornar_Faixa4_para_valor_final() {
            double salarioBrutoMensal = FAIXA_SALARIAL4_VALOR_FINAL.value();

            InssRequest userliq = new InssRequest("Test", salarioBrutoMensal);
            inssService.gerarFaixaSalarial(userliq.getSalarioMensalBruto());

            Assertions.assertEquals(FAIXA_SALARIAL4_PARCELA_DEDUTIVEL.value(), inssService.getParcelaADeduzir());
            Assertions.assertEquals(FAIXA_SALARIAL4_ALIQUOTA.value(), inssService.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Deve retornar Faixa4 para valor inicial Faixa4")
        void gerarFaixaSalarial_deve_retornar_Faixa4_para_valor_inicial() {
            double salarioBrutoMensal = FAIXA_SALARIAL4_VALOR_INICIAL.value();

            InssRequest userliq = new InssRequest("Test", salarioBrutoMensal);
            inssService.gerarFaixaSalarial(userliq.getSalarioMensalBruto());

            Assertions.assertEquals(FAIXA_SALARIAL4_PARCELA_DEDUTIVEL.value(), inssService.getParcelaADeduzir());
            Assertions.assertEquals(FAIXA_SALARIAL4_ALIQUOTA.value(), inssService.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Nao deve retornar Faixa4 para valores inferiores a Faixa4")
        void gerarFaixaSalarial_nao_deve_retornar_Faixa4_para_valores_inferiores() {
            double salarioBrutoMensal = FAIXA_SALARIAL4_VALOR_INICIAL.value() - 0.1;

            InssRequest userliq = new InssRequest("Test", salarioBrutoMensal);
            inssService.gerarFaixaSalarial(userliq.getSalarioMensalBruto());

            Assertions.assertNotEquals(FAIXA_SALARIAL4_PARCELA_DEDUTIVEL.value(), inssService.getParcelaADeduzir());
            Assertions.assertNotEquals(FAIXA_SALARIAL4_ALIQUOTA.value(), inssService.getPorcentagemAliquota());
        }
    }

}
