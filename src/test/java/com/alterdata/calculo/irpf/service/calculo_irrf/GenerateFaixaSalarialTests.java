package com.alterdata.calculo.irpf.service.calculo_irrf;

import com.alterdata.calculo.irpf.models.irrf.UserIRRFRequest;
import com.alterdata.calculo.irpf.services.calculo_mensal_irrf.CalcIRRFService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.alterdata.calculo.irpf.services.calculo_mensal_irrf.BaseMensalIRPF.*;
import static com.alterdata.calculo.irpf.services.calculo_mensal_irrf.TetoAliqMensal.*;

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
            double salarioBrutoMensal = aliquota1_valor_final;
            double pensaoAlimenticia = 0;
            double dependentes = 0;

            UserIRRFRequest userliq = new UserIRRFRequest("Victor", salarioBrutoMensal, pensaoAlimenticia, dependentes);
            calcIRRF.generateBaseCalculo(userliq);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertEquals(faixa_salarial_1_aliquota, calcIRRF.getPorcentagemAliquota());
            Assertions.assertEquals(faixa_salarial_1_parcela_dedutivel, calcIRRF.getParcelaADeduzir());
        }

        @Test
        @DisplayName("Nao deve retornar Faixa1 para valores superioes Faixa1")
        void nao_deve_retornar_Faixa1_para_valores_superioes_faixa1() {
            double salarioBrutoMensal = aliquota1_valor_final + 0.1;
            double pensaoAlimenticia = 0;
            double dependentes = 0;

            UserIRRFRequest userliq = new UserIRRFRequest("Victor", salarioBrutoMensal, pensaoAlimenticia, dependentes);
            calcIRRF.generateBaseCalculo(userliq);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertNotEquals(faixa_salarial_1_parcela_dedutivel, calcIRRF.getParcelaADeduzir());
            Assertions.assertNotEquals(faixa_salarial_1_aliquota, calcIRRF.getPorcentagemAliquota());
        }

    }

    @Nested()
    @DisplayName("Teste Faixa 2 IRRF: 7.5&")
    class Faixa2 {
        @Test
        @DisplayName("Deve retornar Faixa2 para valor final Faixa2")
        void deve_retornar_Faixa2_para_valor_final_faixa2() {
            double salarioBrutoMensal = aliquota2_valor_final;
            double pensaoAlimenticia = 0;
            double dependentes = 0;

            UserIRRFRequest userliq = new UserIRRFRequest("Victor", salarioBrutoMensal, pensaoAlimenticia, dependentes);
            calcIRRF.generateBaseCalculo(userliq);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertEquals(faixa_salarial_2_parcela_dedutivel, calcIRRF.getParcelaADeduzir());
            Assertions.assertEquals(faixa_salarial_2_aliquota, calcIRRF.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Deve retornar Faixa2 para valor inicial Faixa2")
        void deve_retornar_Faixa2_para_valor_inicial_faixa2() {
            double salarioBrutoMensal = aliquota2_valor_inicial;
            double pensaoAlimenticia = 0;
            double dependentes = 0;

            UserIRRFRequest userliq = new UserIRRFRequest("Victor", salarioBrutoMensal, pensaoAlimenticia, dependentes);
            calcIRRF.generateBaseCalculo(userliq);
            calcIRRF.generateBaseCalculo(userliq);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertEquals(faixa_salarial_2_parcela_dedutivel, calcIRRF.getParcelaADeduzir());
            Assertions.assertEquals(faixa_salarial_2_aliquota, calcIRRF.getPorcentagemAliquota());
        }


        @Test
        @DisplayName("Nao deve retornar Faixa2 para valores superioes Faixa2")
        void nao_deve_retornar_Faixa2_para_valores_superioes_faixa2() {
            double salarioBrutoMensal = aliquota2_valor_final + 0.1;
            double pensaoAlimenticia = 0;
            double dependentes = 0;

            UserIRRFRequest userliq = new UserIRRFRequest("Victor", salarioBrutoMensal, pensaoAlimenticia, dependentes);
            calcIRRF.generateBaseCalculo(userliq);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertNotEquals(faixa_salarial_2_parcela_dedutivel, calcIRRF.getParcelaADeduzir());
            Assertions.assertNotEquals(faixa_salarial_2_aliquota, calcIRRF.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Nao deve retornar Faixa2 para valores inferiores Faixa2")
        void nao_deve_retornar_Faixa2_para_valores_inferiores_faixa2() {
            double salarioBrutoMensal = aliquota2_valor_inicial - 0.1;
            double pensaoAlimenticia = 0;
            double dependentes = 0;

            UserIRRFRequest userliq = new UserIRRFRequest("Victor", salarioBrutoMensal, pensaoAlimenticia, dependentes);
            calcIRRF.generateBaseCalculo(userliq);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertNotEquals(faixa_salarial_2_parcela_dedutivel, calcIRRF.getParcelaADeduzir());
            Assertions.assertNotEquals(faixa_salarial_2_aliquota, calcIRRF.getPorcentagemAliquota());
        }

    }

    @Nested()
    @DisplayName("Faixa 3 IRRF: 15%")
    class Faixa3 {
        @Test
        @DisplayName("Deve retornar Faixa3 para valor final Faixa3")
        void deve_retornar_Faixa3_para_valor_final() {
            double salarioBrutoMensal = aliquota3_valor_final;
            double pensaoAlimenticia = 0;
            double dependentes = 0;

            UserIRRFRequest userliq = new UserIRRFRequest("Victor", salarioBrutoMensal, pensaoAlimenticia, dependentes);
            calcIRRF.generateBaseCalculo(userliq);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertEquals(faixa_salarial_3_parcela_dedutivel, calcIRRF.getParcelaADeduzir());
            Assertions.assertEquals(faixa_salarial_3_aliquota, calcIRRF.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Deve retornar Faixa3 para valor inicial Faixa3")
        void deve_retornar_Faixa3_para_valor_inicial() {
            double salarioBrutoMensal = aliquota3_valor_inicial;
            double pensaoAlimenticia = 0;
            double dependentes = 0;

            UserIRRFRequest userliq = new UserIRRFRequest("Victor", salarioBrutoMensal, pensaoAlimenticia, dependentes);
            calcIRRF.generateBaseCalculo(userliq);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertEquals(faixa_salarial_3_parcela_dedutivel, calcIRRF.getParcelaADeduzir());
            Assertions.assertEquals(faixa_salarial_3_aliquota, calcIRRF.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Nao deve retornar Faixa3 para valores superioes Faixa3")
        void nao_deve_retornar_Faixa3_para_valores_superioes() {
            double salarioBrutoMensal = aliquota3_valor_final + 0.1;
            double pensaoAlimenticia = 0;
            double dependentes = 0;

            UserIRRFRequest userliq = new UserIRRFRequest("Victor", salarioBrutoMensal, pensaoAlimenticia, dependentes);
            calcIRRF.generateBaseCalculo(userliq);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertNotEquals(faixa_salarial_3_parcela_dedutivel, calcIRRF.getParcelaADeduzir());
            Assertions.assertNotEquals(faixa_salarial_3_aliquota, calcIRRF.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Nao deve retornar Faixa3 para valores inferiores Faixa3")
        void nao_deve_retornar_Faixa3_para_valores_inferiores() {
            double salarioBrutoMensal = aliquota3_valor_inicial - 0.1;
            double pensaoAlimenticia = 0;
            double dependentes = 0;

            UserIRRFRequest userliq = new UserIRRFRequest("Victor", salarioBrutoMensal, pensaoAlimenticia, dependentes);
            calcIRRF.generateBaseCalculo(userliq);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertNotEquals(faixa_salarial_3_parcela_dedutivel, calcIRRF.getParcelaADeduzir());
            Assertions.assertNotEquals(faixa_salarial_3_aliquota, calcIRRF.getPorcentagemAliquota());
        }

    }

    @Nested()
    @DisplayName("Faixa 4 IRRF: 22.5%")
    class Faixa4 {

        @Test
        @DisplayName("Deve retornar Faixa4 para valor final Fixa4")
        void deve_retornar_Faixa4_para_valor_final() {
            double salarioBrutoMensal = aliquota4_valor_final;
            double pensaoAlimenticia = 0;
            double dependentes = 0;

            UserIRRFRequest userliq = new UserIRRFRequest("Victor", salarioBrutoMensal, pensaoAlimenticia, dependentes);
            calcIRRF.generateBaseCalculo(userliq);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertEquals(faixa_salarial_4_parcela_dedutivel, calcIRRF.getParcelaADeduzir());
            Assertions.assertEquals(faixa_salarial_4_aliquota, calcIRRF.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Deve retornar Faixa4 para valor inicial Faixa4")
        void deve_retornar_Faixa4_para_valor_inicial() {
            double salarioBrutoMensal = aliquota4_valor_inicial;
            double pensaoAlimenticia = 0;
            double dependentes = 0;

            UserIRRFRequest userliq = new UserIRRFRequest("Victor", salarioBrutoMensal, pensaoAlimenticia, dependentes);
            calcIRRF.generateBaseCalculo(userliq);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertEquals(faixa_salarial_4_parcela_dedutivel, calcIRRF.getParcelaADeduzir());
            Assertions.assertEquals(faixa_salarial_4_aliquota, calcIRRF.getPorcentagemAliquota());
        }


        @Test
        @DisplayName("Nao deve retornar Faixa4 para valores superioes Faixa4")
        void nao_deve_retornar_Faixa4_para_valores_superioes() {
            double salarioBrutoMensal = aliquota4_valor_final + 0.1;
            double pensaoAlimenticia = 0;
            double dependentes = 0;

            UserIRRFRequest userliq = new UserIRRFRequest("Victor", salarioBrutoMensal, pensaoAlimenticia, dependentes);
            calcIRRF.generateBaseCalculo(userliq);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertNotEquals(faixa_salarial_4_parcela_dedutivel, calcIRRF.getParcelaADeduzir());
            Assertions.assertNotEquals(faixa_salarial_4_aliquota, calcIRRF.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Nao deve retornar Faixa4 para valores inferiores Faixa4")
        void nao_deve_retornar_Faixa4_para_valores_inferiores() {
            double salarioBrutoMensal = aliquota4_valor_inicial - 0.1;
            double pensaoAlimenticia = 0;
            double dependentes = 0;

            UserIRRFRequest userliq = new UserIRRFRequest("Victor", salarioBrutoMensal, pensaoAlimenticia, dependentes);
            calcIRRF.generateBaseCalculo(userliq);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertNotEquals(faixa_salarial_4_parcela_dedutivel, calcIRRF.getParcelaADeduzir());
            Assertions.assertNotEquals(faixa_salarial_4_aliquota, calcIRRF.getPorcentagemAliquota());
        }
    }
}
