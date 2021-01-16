package com.alterdata.calculo.irpf.CalculoINSS;

import com.alterdata.calculo.irpf.models.UserINSSIn;
import com.alterdata.calculo.irpf.services.calcINSS.CalcINSSService;
import org.junit.jupiter.api.*;

import static com.alterdata.calculo.irpf.services.calcINSS.BaseMensalAliquotaINSS.*;

@DisplayName("Executando testes CalcINSS generateFaixaSalarial")
public class GenerateFaixaSalarialTests {

    private TestInfo testInfo;
    private TestReporter testReporter;
    private CalcINSSService calcINSSService;
    private UserINSSIn userINSSIn;

    @BeforeEach
    void generateFaixaSalarial_initEach(TestReporter testReporter, TestInfo testInfo) {
        calcINSSService = new CalcINSSService();
        userINSSIn = new UserINSSIn();

        this.testReporter = testReporter;
        this.testInfo = testInfo;

        testReporter.publishEntry("Executando -> " +
                testInfo.getDisplayName() +
                " com a tag -> " + testInfo.getTags());
    }

    @Nested
    @DisplayName("Faixa salarial 1 INSS")
    class Faixa1 {

        @Test
        @DisplayName("Deve retornar Faixa1 para valor final Faixa1")
        void generateFaixaSalarial_generateFaixaSalarial_deve_retornar_Faixa1_para_valor_final() {
            double salarioBrutoMensal = faixa1_valorFinal;

            UserINSSIn userliq = new UserINSSIn("Victor", salarioBrutoMensal);
            calcINSSService.generateFaixaSalarial(userliq.getSalarioMensalBruto());

            Assertions.assertEquals(faixa1_aliquota, calcINSSService.getPorcentagemAliquota());
            Assertions.assertEquals(faixa1_parcelaADeduzir, calcINSSService.getParcelaADeduzir());
        }

        @Test
        @DisplayName("Nao deve retornar Faixa1 para valores superioes a Faixa1")
        void generateFaixaSalarial_nao_deve_retornar_Faixa1_para_valores_superioes() {
            double salarioBrutoMensal = faixa1_valorFinal + 0.1;

            UserINSSIn userliq = new UserINSSIn("Victor", salarioBrutoMensal);
            calcINSSService.generateFaixaSalarial(userliq.getSalarioMensalBruto());

            Assertions.assertNotEquals(faixa1_aliquota, calcINSSService.getParcelaADeduzir());
            Assertions.assertNotEquals(faixa1_parcelaADeduzir, calcINSSService.getPorcentagemAliquota());
        }

    }

    @Nested()
    @DisplayName("Faixa salarial 2 INSS")
    class Faixa2 {
        @Test
        @DisplayName("Deve retornar Faixa2 para valor final Faixa2")
        void generateFaixaSalarial_deve_retornar_Faixa2_para_valor_final() {
            double salarioBrutoMensal = faixa2_valorFinal;

            UserINSSIn userliq = new UserINSSIn("Victor", salarioBrutoMensal);
            calcINSSService.generateFaixaSalarial(userliq.getSalarioMensalBruto());

            Assertions.assertEquals(faixa2_parcelaADeduzir, calcINSSService.getParcelaADeduzir());
            Assertions.assertEquals(faixa2_aliquota, calcINSSService.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Deve retornar Faixa2 para valor inicial Faixa2")
        void generateFaixaSalarial_deve_retornar_Faixa2_para_valor_inicial() {
            double salarioBrutoMensal = faixa2_valorInicial;

            UserINSSIn userliq = new UserINSSIn("Victor", salarioBrutoMensal);
            calcINSSService.generateFaixaSalarial(userliq.getSalarioMensalBruto());

            Assertions.assertEquals(faixa2_parcelaADeduzir, calcINSSService.getParcelaADeduzir());
            Assertions.assertEquals(faixa2_aliquota, calcINSSService.getPorcentagemAliquota());
        }


        @Test
        @DisplayName("Nao deve retornar Faixa2 paravalores superioes a Faixa2")
        void generateFaixaSalarial_nao_deve_retornar_Faixa2_para_valores_superioes() {
            double salarioBrutoMensal = faixa2_valorFinal + 0.1;

            UserINSSIn userliq = new UserINSSIn("Victor", salarioBrutoMensal);
            calcINSSService.generateFaixaSalarial(userliq.getSalarioMensalBruto());

            Assertions.assertNotEquals(faixa2_parcelaADeduzir, calcINSSService.getParcelaADeduzir());
            Assertions.assertNotEquals(faixa2_aliquota, calcINSSService.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Nao deve retornar Faixa2 para valores inferiores a Faixa2")
        void generateFaixaSalarial_nao_deve_retornar_Faixa2_para_valores_inferiores() {
            double salarioBrutoMensal = faixa2_valorInicial - 0.1;

            UserINSSIn userliq = new UserINSSIn("Victor", salarioBrutoMensal);
            calcINSSService.generateFaixaSalarial(userliq.getSalarioMensalBruto());

            Assertions.assertNotEquals(faixa2_parcelaADeduzir, calcINSSService.getParcelaADeduzir());
            Assertions.assertNotEquals(faixa2_aliquota, calcINSSService.getPorcentagemAliquota());
        }

    }

    @Nested()
    @DisplayName("Faixa Salarial 3 INSS")
    class Faixa3 {
        @Test
        @DisplayName("Deve retornar Faixa3 para valor final Faixa3")
        void generateFaixaSalarial_deve_retornar_Faixa3_para_valor_final() {
            double salarioBrutoMensal = faixa3_valorFinal;

            UserINSSIn userliq = new UserINSSIn("Victor", salarioBrutoMensal);
            calcINSSService.generateFaixaSalarial(userliq.getSalarioMensalBruto());

            Assertions.assertEquals(faixa3_parcelaADeduzir, calcINSSService.getParcelaADeduzir());
            Assertions.assertEquals(faixa3_aliquota, calcINSSService.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Deve retornar Faixa3 para valor inicial faixa3")
        void generateFaixaSalarial_deve_retornar_Faixa3_para_valor_inicial() {
            double salarioBrutoMensal = faixa3_valorInicial;

            UserINSSIn userliq = new UserINSSIn("Victor", salarioBrutoMensal);
            calcINSSService.generateFaixaSalarial(userliq.getSalarioMensalBruto());

            Assertions.assertEquals(faixa3_parcelaADeduzir, calcINSSService.getParcelaADeduzir());
            Assertions.assertEquals(faixa3_aliquota, calcINSSService.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Nao deve retornar Faixa3 para valores superioes a Faixa3")
        void generateFaixaSalarial_nao_deve_retornar_Faixa3_para_valores_superioes() {
            double salarioBrutoMensal = faixa3_valorFinal + 0.1 ;

            UserINSSIn userliq = new UserINSSIn("Victor", salarioBrutoMensal);
            calcINSSService.generateFaixaSalarial(userliq.getSalarioMensalBruto());

            Assertions.assertNotEquals(faixa3_parcelaADeduzir, calcINSSService.getParcelaADeduzir());
            Assertions.assertNotEquals(faixa3_aliquota, calcINSSService.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Nao deve retornar Faixa3 para valores inferiores a Faixa3")
        void generateFaixaSalarial_nao_deve_retornar_Faixa3_para_valores_inferiores() {
            double salarioBrutoMensal = faixa3_valorInicial - 0.1 ;

            UserINSSIn userliq = new UserINSSIn("Victor", salarioBrutoMensal);
            calcINSSService.generateFaixaSalarial(userliq.getSalarioMensalBruto());

            Assertions.assertNotEquals(faixa3_parcelaADeduzir, calcINSSService.getParcelaADeduzir());
            Assertions.assertNotEquals(faixa3_aliquota, calcINSSService.getPorcentagemAliquota());
        }

    }

    @Nested()
    @DisplayName("Faixa salarial 4 INSS")
    class Faixa4 {
        @Test
        @DisplayName("Deve retornar Faixa4 para valor final Faixa4")
        void generateFaixaSalarial_deve_retornar_Faixa4_para_valor_final() {
            double salarioBrutoMensal = faixa4_valorFinal;

            UserINSSIn userliq = new UserINSSIn("Victor", salarioBrutoMensal);
            calcINSSService.generateFaixaSalarial(userliq.getSalarioMensalBruto());

            Assertions.assertEquals(faixa4_parcelaADeduzir, calcINSSService.getParcelaADeduzir());
            Assertions.assertEquals(faixa4_aliquota, calcINSSService.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Deve retornar Faixa4 para valor inicial Faixa4")
        void generateFaixaSalarial_deve_retornar_Faixa4_para_valor_inicial() {
            double salarioBrutoMensal = faixa4_valorInicial;

            UserINSSIn userliq = new UserINSSIn("Victor", salarioBrutoMensal);
            calcINSSService.generateFaixaSalarial(userliq.getSalarioMensalBruto());

            Assertions.assertEquals(faixa4_parcelaADeduzir, calcINSSService.getParcelaADeduzir());
            Assertions.assertEquals(faixa4_aliquota, calcINSSService.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Nao deve retornar Faixa4 para valores inferiores a Faixa4")
        void generateFaixaSalarial_nao_deve_retornar_Faixa4_para_valores_inferiores() {
            double salarioBrutoMensal = faixa4_valorInicial - 0.1;

            UserINSSIn userliq = new UserINSSIn("Victor", salarioBrutoMensal);
            calcINSSService.generateFaixaSalarial(userliq.getSalarioMensalBruto());

            Assertions.assertNotEquals(faixa4_parcelaADeduzir, calcINSSService.getParcelaADeduzir());
            Assertions.assertNotEquals(faixa4_aliquota, calcINSSService.getPorcentagemAliquota());
        }
    }

}
