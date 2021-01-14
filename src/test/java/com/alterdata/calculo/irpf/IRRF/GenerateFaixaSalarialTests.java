package com.alterdata.calculo.irpf.IRRF;

import com.alterdata.calculo.irpf.models.UserIRRFIn;
import com.alterdata.calculo.irpf.services.calcIRRF.CalcIRRFService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.alterdata.calculo.irpf.services.calcIRRF.BaseMensalIRPF.*;
import static com.alterdata.calculo.irpf.services.calcIRRF.TetoAliqMensal.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Executando testes para generateFaixaSalarial")
public class GenerateFaixaSalarialTests {

    @InjectMocks
    CalcIRRFService calcIRRF;

    @Nested
    @DisplayName("Teste Faixa 1 IRRF: 0%")
    class Faixa1 {
        @Test
        @DisplayName("Deve retornar Faixa1 para valor final Faixa1")
        void deve_retornar_Faixa1_para_valor_final_faixa1() {
            double salarioBrutoMensal = ali1_final;
            double pensaoAlimenticia = 0;
            double dependentes = 0;

            UserIRRFIn userliq = new UserIRRFIn("Victor", salarioBrutoMensal, pensaoAlimenticia, dependentes);
            calcIRRF.generateBaseCalculo(userliq);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertEquals(faixa1_aliquota, calcIRRF.getPorcentagemAliquota());
            Assertions.assertEquals(faixa1_parcelaADeduzir, calcIRRF.getParcelaADeduzir());
        }

        @Test
        @DisplayName("Nao deve retornar Faixa1 para valores superioes Faixa1")
        void nao_deve_retornar_Faixa1_para_valores_superioes_faixa1() {
            double salarioBrutoMensal = ali1_final + 0.1;
            double pensaoAlimenticia = 0;
            double dependentes = 0;

            UserIRRFIn userliq = new UserIRRFIn("Victor", salarioBrutoMensal, pensaoAlimenticia, dependentes);
            calcIRRF.generateBaseCalculo(userliq);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertNotEquals(faixa1_parcelaADeduzir, calcIRRF.getParcelaADeduzir());
            Assertions.assertNotEquals(faixa1_aliquota, calcIRRF.getPorcentagemAliquota());
        }

    }

    @Nested()
    @DisplayName("Teste Faixa 2 IRRF: 7.5&")
    class Faixa2 {
        @Test
        @DisplayName("Deve retornar Faixa2 para valor final Faixa2")
        void deve_retornar_Faixa2_para_valor_final_faixa2() {
            double salarioBrutoMensal = ali2_final;
            double pensaoAlimenticia = 0;
                    double dependentes = 0;

            UserIRRFIn userliq = new UserIRRFIn("Victor", salarioBrutoMensal, pensaoAlimenticia, dependentes);
            calcIRRF.generateBaseCalculo(userliq);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertEquals(faixa2_parcelaADeduzir, calcIRRF.getParcelaADeduzir());
            Assertions.assertEquals(faixa2_aliquota, calcIRRF.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Deve retornar Faixa2 para valor inicial Faixa2")
        void deve_retornar_Faixa2_para_valor_inicial_faixa2() {
            double salarioBrutoMensal = ali2_inicial;
            double pensaoAlimenticia = 0;
                    double dependentes = 0;

            UserIRRFIn userliq = new UserIRRFIn("Victor", salarioBrutoMensal, pensaoAlimenticia, dependentes);
            calcIRRF.generateBaseCalculo(userliq);
            calcIRRF.generateBaseCalculo(userliq);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertEquals(faixa2_parcelaADeduzir, calcIRRF.getParcelaADeduzir());
            Assertions.assertEquals(faixa2_aliquota, calcIRRF.getPorcentagemAliquota());
        }


        @Test
        @DisplayName("Nao deve retornar Faixa2 para valores superioes Faixa2")
        void nao_deve_retornar_Faixa2_para_valores_superioes_faixa2() {
            double salarioBrutoMensal = ali2_final + 0.1;
            double pensaoAlimenticia = 0;
                    double dependentes = 0;

            UserIRRFIn userliq = new UserIRRFIn("Victor", salarioBrutoMensal, pensaoAlimenticia, dependentes);
            calcIRRF.generateBaseCalculo(userliq);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertNotEquals(faixa2_parcelaADeduzir, calcIRRF.getParcelaADeduzir());
            Assertions.assertNotEquals(faixa2_aliquota, calcIRRF.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Nao deve retornar Faixa2 para valores inferiores Faixa2")
        void nao_deve_retornar_Faixa2_para_valores_inferiores_faixa2() {
            double salarioBrutoMensal = ali2_inicial - 0.1;
            double pensaoAlimenticia = 0;
                    double dependentes = 0;

            UserIRRFIn userliq = new UserIRRFIn("Victor", salarioBrutoMensal, pensaoAlimenticia, dependentes);
            calcIRRF.generateBaseCalculo(userliq);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertNotEquals(faixa2_parcelaADeduzir, calcIRRF.getParcelaADeduzir());
            Assertions.assertNotEquals(faixa2_aliquota, calcIRRF.getPorcentagemAliquota());
        }

    }

    @Nested()
    @DisplayName("Faixa 3 IRRF: 15%")
    class Faixa3 {
        @Test
        @DisplayName("Deve retornar Faixa3 para valor final Faixa3")
        void deve_retornar_Faixa3_para_valor_final() {
            double salarioBrutoMensal = ali3_final;
            double pensaoAlimenticia = 0;
                    double dependentes = 0;

            UserIRRFIn userliq = new UserIRRFIn("Victor", salarioBrutoMensal, pensaoAlimenticia, dependentes);
            calcIRRF.generateBaseCalculo(userliq);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertEquals(faixa3_parcelaADeduzir, calcIRRF.getParcelaADeduzir());
            Assertions.assertEquals(faixa3_aliquota, calcIRRF.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Deve retornar Faixa3 para valor inicial Faixa3")
        void deve_retornar_Faixa3_para_valor_inicial() {
            double salarioBrutoMensal = ali3_inicial;
            double pensaoAlimenticia = 0;
                    double dependentes = 0;

            UserIRRFIn userliq = new UserIRRFIn("Victor", salarioBrutoMensal, pensaoAlimenticia, dependentes);
            calcIRRF.generateBaseCalculo(userliq);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertEquals(faixa3_parcelaADeduzir, calcIRRF.getParcelaADeduzir());
            Assertions.assertEquals(faixa3_aliquota, calcIRRF.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Nao deve retornar Faixa3 para valores superioes Faixa3")
        void nao_deve_retornar_Faixa3_para_valores_superioes() {
            double salarioBrutoMensal = ali3_final + 0.1 ;
            double pensaoAlimenticia = 0;
                    double dependentes = 0;

            UserIRRFIn userliq = new UserIRRFIn("Victor", salarioBrutoMensal, pensaoAlimenticia, dependentes);
            calcIRRF.generateBaseCalculo(userliq);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertNotEquals(faixa3_parcelaADeduzir, calcIRRF.getParcelaADeduzir());
            Assertions.assertNotEquals(faixa3_aliquota, calcIRRF.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Nao deve retornar Faixa3 para valores inferiores Faixa3")
        void nao_deve_retornar_Faixa3_para_valores_inferiores() {
            double salarioBrutoMensal = ali3_inicial - 0.1 ;
            double pensaoAlimenticia = 0;
                    double dependentes = 0;

            UserIRRFIn userliq = new UserIRRFIn("Victor", salarioBrutoMensal, pensaoAlimenticia, dependentes);
            calcIRRF.generateBaseCalculo(userliq);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertNotEquals(faixa3_parcelaADeduzir, calcIRRF.getParcelaADeduzir());
            Assertions.assertNotEquals(faixa3_aliquota, calcIRRF.getPorcentagemAliquota());
        }

    }

    @Nested()
    @DisplayName("Faixa 4 IRRF: 22.5%")
    class Faixa4 {

        @Test
        @DisplayName("Deve retornar Faixa4 para valor final Fixa4")
        void deve_retornar_Faixa4_para_valor_final() {
            double salarioBrutoMensal = ali4_final;
            double pensaoAlimenticia = 0;
                    double dependentes = 0;

            UserIRRFIn userliq = new UserIRRFIn("Victor", salarioBrutoMensal, pensaoAlimenticia, dependentes);
            calcIRRF.generateBaseCalculo(userliq);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertEquals(faixa4_parcelaADeduzir, calcIRRF.getParcelaADeduzir());
            Assertions.assertEquals(faixa4_aliquota, calcIRRF.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Deve retornar Faixa4 para valor inicial Faixa4")
        void deve_retornar_Faixa4_para_valor_inicial() {
            double salarioBrutoMensal = ali4_inicial;
            double pensaoAlimenticia = 0;
                    double dependentes = 0;

            UserIRRFIn userliq = new UserIRRFIn("Victor", salarioBrutoMensal, pensaoAlimenticia, dependentes);
            calcIRRF.generateBaseCalculo(userliq);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertEquals(faixa4_parcelaADeduzir, calcIRRF.getParcelaADeduzir());
            Assertions.assertEquals(faixa4_aliquota, calcIRRF.getPorcentagemAliquota());
        }


        @Test
        @DisplayName("Nao deve retornar Faixa4 para valores superioes Faixa4")
        void nao_deve_retornar_Faixa4_para_valores_superioes() {
        double salarioBrutoMensal = ali4_final + 0.1;
        double pensaoAlimenticia = 0;
        double dependentes = 0;

            UserIRRFIn userliq = new UserIRRFIn("Victor", salarioBrutoMensal, pensaoAlimenticia, dependentes);
            calcIRRF.generateBaseCalculo(userliq);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertNotEquals(faixa4_parcelaADeduzir, calcIRRF.getParcelaADeduzir());
            Assertions.assertNotEquals(faixa4_aliquota, calcIRRF.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Nao deve retornar Faixa4 para valores inferiores Faixa4")
        void nao_deve_retornar_Faixa4_para_valores_inferiores() {
            double salarioBrutoMensal = ali4_inicial - 0.1;
            double pensaoAlimenticia = 0;
                    double dependentes = 0;

            UserIRRFIn userliq = new UserIRRFIn("Victor", salarioBrutoMensal, pensaoAlimenticia, dependentes);
            calcIRRF.generateBaseCalculo(userliq);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertNotEquals(faixa4_parcelaADeduzir, calcIRRF.getParcelaADeduzir());
            Assertions.assertNotEquals(faixa4_aliquota, calcIRRF.getPorcentagemAliquota());
        }
    }
}
