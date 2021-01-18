package com.alterdata.calculo.irpf.service.calculo_inss;

import com.alterdata.calculo.irpf.models.inss.UserINSSRequest;
import com.alterdata.calculo.irpf.services.calculo_mensal.CalcINSSService;
import org.junit.jupiter.api.*;

import static com.alterdata.calculo.irpf.services.calculo_mensal.constants.AliquotaMensalINSS.*;


@DisplayName("Executando testes unitarios CalcINSS generateFaixaSalarial()")
public class GenerateFaixaSalarialTests {

    private CalcINSSService calcINSSService;
    private UserINSSRequest userINSSRequest;

    @BeforeEach
    void generateFaixaSalarial_initEach() {
        calcINSSService = new CalcINSSService();
        userINSSRequest = new UserINSSRequest();
    }

    @Nested
    @DisplayName("Faixa salarial 1 INSS")
    class Faixa1 {

        @Test
        @DisplayName("Deve retornar Faixa1 para valor final Faixa1")
        void generateFaixaSalarial_generateFaixaSalarial_deve_retornar_Faixa1_para_valor_final() {
            double salarioBrutoMensal = FAIXA_SALARIAL1_VALOR_FINAL.value();

            UserINSSRequest userliq = new UserINSSRequest("Test", salarioBrutoMensal);
            calcINSSService.generateFaixaSalarial(userliq.getSalarioMensalBruto());

            Assertions.assertEquals(FAIXA_SALARIAL1_ALIQUOTA.value(), calcINSSService.getPorcentagemAliquota());
            Assertions.assertEquals(FAIXA_SALARIAL1_PARCELA_DEDUTIVEL.value(), calcINSSService.getParcelaADeduzir());
        }

        @Test
        @DisplayName("Nao deve retornar Faixa1 para valores superioes a Faixa1")
        void generateFaixaSalarial_nao_deve_retornar_Faixa1_para_valores_superioes() {
            double salarioBrutoMensal = FAIXA_SALARIAL1_VALOR_FINAL.value() + 0.1;

            UserINSSRequest userliq = new UserINSSRequest("Test", salarioBrutoMensal);
            calcINSSService.generateFaixaSalarial(userliq.getSalarioMensalBruto());

            Assertions.assertNotEquals(FAIXA_SALARIAL1_ALIQUOTA.value(), calcINSSService.getParcelaADeduzir());
            Assertions.assertNotEquals(FAIXA_SALARIAL1_PARCELA_DEDUTIVEL.value(), calcINSSService.getPorcentagemAliquota());
        }

    }

    @Nested()
    @DisplayName("Faixa salarial 2 INSS")
    class Faixa2 {
        @Test
        @DisplayName("Deve retornar Faixa2 para valor final Faixa2")
        void generateFaixaSalarial_deve_retornar_Faixa2_para_valor_final() {
            double salarioBrutoMensal = FAIXA_SALARIAL2_VALOR_FINAL.value();

            UserINSSRequest userliq = new UserINSSRequest("Test", salarioBrutoMensal);
            calcINSSService.generateFaixaSalarial(userliq.getSalarioMensalBruto());

            Assertions.assertEquals(FAIXA_SALARIAL2_PARCELA_DEDUTIVEL.value(), calcINSSService.getParcelaADeduzir());
            Assertions.assertEquals(FAIXA_SALARIAL2_ALIQUOTA.value(), calcINSSService.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Deve retornar Faixa2 para valor inicial Faixa2")
        void generateFaixaSalarial_deve_retornar_Faixa2_para_valor_inicial() {
            double salarioBrutoMensal = FAIXA_SALARIAL2_VALOR_INICIAL.value();

            UserINSSRequest userliq = new UserINSSRequest("Test", salarioBrutoMensal);
            calcINSSService.generateFaixaSalarial(userliq.getSalarioMensalBruto());

            Assertions.assertEquals(FAIXA_SALARIAL2_PARCELA_DEDUTIVEL.value(), calcINSSService.getParcelaADeduzir());
            Assertions.assertEquals(FAIXA_SALARIAL2_ALIQUOTA.value(), calcINSSService.getPorcentagemAliquota());
        }


        @Test
        @DisplayName("Nao deve retornar Faixa2 paravalores superioes a Faixa2")
        void generateFaixaSalarial_nao_deve_retornar_Faixa2_para_valores_superioes() {
            double salarioBrutoMensal = FAIXA_SALARIAL2_VALOR_FINAL.value() + 0.1;

            UserINSSRequest userliq = new UserINSSRequest("Test", salarioBrutoMensal);
            calcINSSService.generateFaixaSalarial(userliq.getSalarioMensalBruto());

            Assertions.assertNotEquals(FAIXA_SALARIAL2_PARCELA_DEDUTIVEL.value(), calcINSSService.getParcelaADeduzir());
            Assertions.assertNotEquals(FAIXA_SALARIAL2_ALIQUOTA.value(), calcINSSService.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Nao deve retornar Faixa2 para valores inferiores a Faixa2")
        void generateFaixaSalarial_nao_deve_retornar_Faixa2_para_valores_inferiores() {
            double salarioBrutoMensal = FAIXA_SALARIAL2_VALOR_INICIAL.value() - 0.1;

            UserINSSRequest userliq = new UserINSSRequest("Test", salarioBrutoMensal);
            calcINSSService.generateFaixaSalarial(userliq.getSalarioMensalBruto());

            Assertions.assertNotEquals(FAIXA_SALARIAL2_PARCELA_DEDUTIVEL.value(), calcINSSService.getParcelaADeduzir());
            Assertions.assertNotEquals(FAIXA_SALARIAL2_ALIQUOTA.value(), calcINSSService.getPorcentagemAliquota());
        }

    }

    @Nested()
    @DisplayName("Faixa Salarial 3 INSS")
    class Faixa3 {
        @Test
        @DisplayName("Deve retornar Faixa3 para valor final Faixa3")
        void generateFaixaSalarial_deve_retornar_Faixa3_para_valor_final() {
            double salarioBrutoMensal = FAIXA_SALARIAL3_VALOR_FINAL.value();

            UserINSSRequest userliq = new UserINSSRequest("Test", salarioBrutoMensal);
            calcINSSService.generateFaixaSalarial(userliq.getSalarioMensalBruto());

            Assertions.assertEquals(FAIXA_SALARIAL3_PARCELA_DEDUTIVEL.value(), calcINSSService.getParcelaADeduzir());
            Assertions.assertEquals(FAIXA_SALARIAL3_ALIQUOTA.value(), calcINSSService.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Deve retornar Faixa3 para valor inicial FAIXA_SALARIAL3")
        void generateFaixaSalarial_deve_retornar_Faixa3_para_valor_inicial() {
            double salarioBrutoMensal = FAIXA_SALARIAL3_VALOR_INICIAL.value();

            UserINSSRequest userliq = new UserINSSRequest("Test", salarioBrutoMensal);
            calcINSSService.generateFaixaSalarial(userliq.getSalarioMensalBruto());

            Assertions.assertEquals(FAIXA_SALARIAL3_PARCELA_DEDUTIVEL.value(), calcINSSService.getParcelaADeduzir());
            Assertions.assertEquals(FAIXA_SALARIAL3_ALIQUOTA.value(), calcINSSService.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Nao deve retornar Faixa3 para valores superioes a Faixa3")
        void generateFaixaSalarial_nao_deve_retornar_Faixa3_para_valores_superioes() {
            double salarioBrutoMensal = FAIXA_SALARIAL3_VALOR_FINAL.value() + 0.1 ;

            UserINSSRequest userliq = new UserINSSRequest("Test", salarioBrutoMensal);
            calcINSSService.generateFaixaSalarial(userliq.getSalarioMensalBruto());

            Assertions.assertNotEquals(FAIXA_SALARIAL3_PARCELA_DEDUTIVEL.value(), calcINSSService.getParcelaADeduzir());
            Assertions.assertNotEquals(FAIXA_SALARIAL3_ALIQUOTA.value(), calcINSSService.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Nao deve retornar Faixa3 para valores inferiores a Faixa3")
        void generateFaixaSalarial_nao_deve_retornar_Faixa3_para_valores_inferiores() {
            double salarioBrutoMensal = FAIXA_SALARIAL3_VALOR_INICIAL.value() - 0.1 ;

            UserINSSRequest userliq = new UserINSSRequest("Test", salarioBrutoMensal);
            calcINSSService.generateFaixaSalarial(userliq.getSalarioMensalBruto());

            Assertions.assertNotEquals(FAIXA_SALARIAL3_PARCELA_DEDUTIVEL.value(), calcINSSService.getParcelaADeduzir());
            Assertions.assertNotEquals(FAIXA_SALARIAL3_ALIQUOTA.value(), calcINSSService.getPorcentagemAliquota());
        }

    }

    @Nested()
    @DisplayName("Faixa salarial 4 INSS")
    class Faixa4 {
        @Test
        @DisplayName("Deve retornar Faixa4 para valor final Faixa4")
        void generateFaixaSalarial_deve_retornar_Faixa4_para_valor_final() {
            double salarioBrutoMensal = FAIXA_SALARIAL4_VALOR_FINAL.value();

            UserINSSRequest userliq = new UserINSSRequest("Test", salarioBrutoMensal);
            calcINSSService.generateFaixaSalarial(userliq.getSalarioMensalBruto());

            Assertions.assertEquals(FAIXA_SALARIAL4_PARCELA_DEDUTIVEL.value(), calcINSSService.getParcelaADeduzir());
            Assertions.assertEquals(FAIXA_SALARIAL4_ALIQUOTA.value(), calcINSSService.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Deve retornar Faixa4 para valor inicial Faixa4")
        void generateFaixaSalarial_deve_retornar_Faixa4_para_valor_inicial() {
            double salarioBrutoMensal = FAIXA_SALARIAL4_VALOR_INICIAL.value();

            UserINSSRequest userliq = new UserINSSRequest("Test", salarioBrutoMensal);
            calcINSSService.generateFaixaSalarial(userliq.getSalarioMensalBruto());

            Assertions.assertEquals(FAIXA_SALARIAL4_PARCELA_DEDUTIVEL.value(), calcINSSService.getParcelaADeduzir());
            Assertions.assertEquals(FAIXA_SALARIAL4_ALIQUOTA.value(), calcINSSService.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Nao deve retornar Faixa4 para valores inferiores a Faixa4")
        void generateFaixaSalarial_nao_deve_retornar_Faixa4_para_valores_inferiores() {
            double salarioBrutoMensal = FAIXA_SALARIAL4_VALOR_INICIAL.value() - 0.1;

            UserINSSRequest userliq = new UserINSSRequest("Test", salarioBrutoMensal);
            calcINSSService.generateFaixaSalarial(userliq.getSalarioMensalBruto());

            Assertions.assertNotEquals(FAIXA_SALARIAL4_PARCELA_DEDUTIVEL.value(), calcINSSService.getParcelaADeduzir());
            Assertions.assertNotEquals(FAIXA_SALARIAL4_ALIQUOTA.value(), calcINSSService.getPorcentagemAliquota());
        }
    }

}
