package com.alterdata.calculo.irpf.service.calculo_irrf;

import com.alterdata.calculo.irpf.models.irrf.IrrfRequest;
import com.alterdata.calculo.irpf.services.calculo_mensal.IrrfService;
import com.alterdata.calculo.irpf.util.IrrfRequestCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.alterdata.calculo.irpf.services.calculo_mensal.constants.AliquotaMensalIRPF.*;
import static com.alterdata.calculo.irpf.services.calculo_mensal.constants.TetoSalarialMensalAliquotaIRPF.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Executando testes unitarios IrrfService generateFaixaSalarial()")
public class GenerateFaixaSalarialTests {

    @InjectMocks
    IrrfService calcIRRF;

    @Nested
    @DisplayName("Teste Faixa 1 IRRF: 0%")
    class Faixa1 {
        @Test
        @DisplayName("Deve retornar Faixa1 para valor final Faixa1")
        void deve_retornar_Faixa1_para_valor_final_faixa1() {
            double salarioBrutoMensal = TETO_SALARIAL1_VALOR_FINAL.value();
            IrrfRequest irrfRequest = IrrfRequestCreator.createValidWithZeroIrrfRequest();
            irrfRequest.setSalarioMensalBruto(salarioBrutoMensal);

            calcIRRF.generateBaseCalculo(irrfRequest);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertEquals(FAIXA_SALARIAL1_ALIQUOTA.value(), calcIRRF.getPorcentagemAliquota());
            Assertions.assertEquals(FAIXA_SALARIAL1_PARCELA_DEDUTIVEL.value(), calcIRRF.getParcelaADeduzir());
        }

        @Test
        @DisplayName("Nao deve retornar Faixa1 para valores superioes Faixa1")
        void nao_deve_retornar_Faixa1_para_valores_superioes_faixa1() {
            double salarioBrutoMensal = TETO_SALARIAL1_VALOR_FINAL.value() + 0.1;
            IrrfRequest irrfRequest = IrrfRequestCreator.createValidWithZeroIrrfRequest();
            irrfRequest.setSalarioMensalBruto(salarioBrutoMensal);

            calcIRRF.generateBaseCalculo(irrfRequest);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertNotEquals(FAIXA_SALARIAL1_PARCELA_DEDUTIVEL.value(), calcIRRF.getParcelaADeduzir());
            Assertions.assertNotEquals(FAIXA_SALARIAL1_ALIQUOTA.value(), calcIRRF.getPorcentagemAliquota());
        }

    }

    @Nested()
    @DisplayName("Teste Faixa 2 IRRF: 7.5&")
    class Faixa2 {
        @Test
        @DisplayName("Deve retornar Faixa2 para valor final Faixa2")
        void deve_retornar_Faixa2_para_valor_final_faixa2() {
            double salarioBrutoMensal = TETO_SALARIAL2_VALOR_FINAL.value();
            IrrfRequest irrfRequest = IrrfRequestCreator.createValidWithZeroIrrfRequest();
            irrfRequest.setSalarioMensalBruto(salarioBrutoMensal);

            calcIRRF.generateBaseCalculo(irrfRequest);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertEquals(FAIXA_SALARIAL2_PARCELA_DEDUTIVEL.value(), calcIRRF.getParcelaADeduzir());
            Assertions.assertEquals(FAIXA_SALARIAL2_ALIQUOTA.value(), calcIRRF.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Deve retornar Faixa2 para valor inicial Faixa2")
        void deve_retornar_Faixa2_para_valor_inicial_faixa2() {
            double salarioBrutoMensal = TETO_SALARIAL2_VALOR_INICIAL.value();
            IrrfRequest irrfRequest = IrrfRequestCreator.createValidWithZeroIrrfRequest();
            irrfRequest.setSalarioMensalBruto(salarioBrutoMensal);

            calcIRRF.generateBaseCalculo(irrfRequest);
            calcIRRF.generateBaseCalculo(irrfRequest);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertEquals(FAIXA_SALARIAL2_PARCELA_DEDUTIVEL.value(), calcIRRF.getParcelaADeduzir());
            Assertions.assertEquals(FAIXA_SALARIAL2_ALIQUOTA.value(), calcIRRF.getPorcentagemAliquota());
        }


        @Test
        @DisplayName("Nao deve retornar Faixa2 para valores superioes Faixa2")
        void nao_deve_retornar_Faixa2_para_valores_superioes_faixa2() {
            double salarioBrutoMensal = TETO_SALARIAL2_VALOR_FINAL.value() + 0.1;
            IrrfRequest irrfRequest = IrrfRequestCreator.createValidWithZeroIrrfRequest();
            irrfRequest.setSalarioMensalBruto(salarioBrutoMensal);

            calcIRRF.generateBaseCalculo(irrfRequest);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertNotEquals(FAIXA_SALARIAL2_PARCELA_DEDUTIVEL.value(), calcIRRF.getParcelaADeduzir());
            Assertions.assertNotEquals(FAIXA_SALARIAL2_ALIQUOTA.value(), calcIRRF.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Nao deve retornar Faixa2 para valores inferiores Faixa2")
        void nao_deve_retornar_Faixa2_para_valores_inferiores_faixa2() {
            double salarioBrutoMensal = TETO_SALARIAL2_VALOR_INICIAL.value() - 0.1;
            IrrfRequest irrfRequest = IrrfRequestCreator.createValidWithZeroIrrfRequest();
            irrfRequest.setSalarioMensalBruto(salarioBrutoMensal);

            calcIRRF.generateBaseCalculo(irrfRequest);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertNotEquals(FAIXA_SALARIAL2_PARCELA_DEDUTIVEL.value(), calcIRRF.getParcelaADeduzir());
            Assertions.assertNotEquals(FAIXA_SALARIAL2_ALIQUOTA.value(), calcIRRF.getPorcentagemAliquota());
        }

    }

    @Nested()
    @DisplayName("Faixa 3 IRRF: 15%")
    class Faixa3 {
        @Test
        @DisplayName("Deve retornar Faixa3 para valor final Faixa3")
        void deve_retornar_Faixa3_para_valor_final() {
            double salarioBrutoMensal = TETO_SALARIAL3_VALOR_FINAL.value();
            IrrfRequest irrfRequest = IrrfRequestCreator.createValidWithZeroIrrfRequest();
            irrfRequest.setSalarioMensalBruto(salarioBrutoMensal);

            calcIRRF.generateBaseCalculo(irrfRequest);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertEquals(FAIXA_SALARIAL3_PARCELA_DEDUTIVEL.value(), calcIRRF.getParcelaADeduzir());
            Assertions.assertEquals(FAIXA_SALARIAL3_ALIQUOTA.value(), calcIRRF.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Deve retornar Faixa3 para valor inicial Faixa3")
        void deve_retornar_Faixa3_para_valor_inicial() {
            double salarioBrutoMensal = TETO_SALARIAL3_VALOR_INICIAL.value();
            IrrfRequest irrfRequest = IrrfRequestCreator.createValidWithZeroIrrfRequest();
            irrfRequest.setSalarioMensalBruto(salarioBrutoMensal);

            calcIRRF.generateBaseCalculo(irrfRequest);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertEquals(FAIXA_SALARIAL3_PARCELA_DEDUTIVEL.value(), calcIRRF.getParcelaADeduzir());
            Assertions.assertEquals(FAIXA_SALARIAL3_ALIQUOTA.value(), calcIRRF.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Nao deve retornar Faixa3 para valores superioes Faixa3")
        void nao_deve_retornar_Faixa3_para_valores_superioes() {
            double salarioBrutoMensal = TETO_SALARIAL3_VALOR_FINAL.value() + 0.1;
            IrrfRequest irrfRequest = IrrfRequestCreator.createValidWithZeroIrrfRequest();
            irrfRequest.setSalarioMensalBruto(salarioBrutoMensal);

            calcIRRF.generateBaseCalculo(irrfRequest);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertNotEquals(FAIXA_SALARIAL3_PARCELA_DEDUTIVEL.value(), calcIRRF.getParcelaADeduzir());
            Assertions.assertNotEquals(FAIXA_SALARIAL3_ALIQUOTA.value(), calcIRRF.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Nao deve retornar Faixa3 para valores inferiores Faixa3")
        void nao_deve_retornar_Faixa3_para_valores_inferiores() {
            double salarioBrutoMensal = TETO_SALARIAL3_VALOR_INICIAL.value() - 0.1;
            IrrfRequest irrfRequest = IrrfRequestCreator.createValidWithZeroIrrfRequest();
            irrfRequest.setSalarioMensalBruto(salarioBrutoMensal);

            calcIRRF.generateBaseCalculo(irrfRequest);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertNotEquals(FAIXA_SALARIAL3_PARCELA_DEDUTIVEL.value(), calcIRRF.getParcelaADeduzir());
            Assertions.assertNotEquals(FAIXA_SALARIAL3_ALIQUOTA.value(), calcIRRF.getPorcentagemAliquota());
        }

    }

    @Nested()
    @DisplayName("Faixa 4 IRRF: 22.5%")
    class Faixa4 {

        @Test
        @DisplayName("Deve retornar Faixa4 para valor final Fixa4")
        void deve_retornar_Faixa4_para_valor_final() {
            double salarioBrutoMensal = TETO_SALARIAL4_VALOR_FINAL.value();
            IrrfRequest irrfRequest = IrrfRequestCreator.createValidWithZeroIrrfRequest();
            irrfRequest.setSalarioMensalBruto(salarioBrutoMensal);

            calcIRRF.generateBaseCalculo(irrfRequest);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertEquals(FAIXA_SALARIAL4_PARCELA_DEDUTIVEL.value(), calcIRRF.getParcelaADeduzir());
            Assertions.assertEquals(FAIXA_SALARIAL4_ALIQUOTA.value(), calcIRRF.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Deve retornar Faixa4 para valor inicial Faixa4")
        void deve_retornar_Faixa4_para_valor_inicial() {
            double salarioBrutoMensal = TETO_SALARIAL4_VALOR_INICIAL.value();
            IrrfRequest irrfRequest = IrrfRequestCreator.createValidWithZeroIrrfRequest();
            irrfRequest.setSalarioMensalBruto(salarioBrutoMensal);

            calcIRRF.generateBaseCalculo(irrfRequest);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertEquals(FAIXA_SALARIAL4_PARCELA_DEDUTIVEL.value(), calcIRRF.getParcelaADeduzir());
            Assertions.assertEquals(FAIXA_SALARIAL4_ALIQUOTA.value(), calcIRRF.getPorcentagemAliquota());
        }


        @Test
        @DisplayName("Nao deve retornar Faixa4 para valores superioes Faixa4")
        void nao_deve_retornar_Faixa4_para_valores_superioes() {
            double salarioBrutoMensal = TETO_SALARIAL4_VALOR_FINAL.value() + 0.1;
            IrrfRequest irrfRequest = IrrfRequestCreator.createValidWithZeroIrrfRequest();
            irrfRequest.setSalarioMensalBruto(salarioBrutoMensal);

            calcIRRF.generateBaseCalculo(irrfRequest);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertNotEquals(FAIXA_SALARIAL4_PARCELA_DEDUTIVEL.value(), calcIRRF.getParcelaADeduzir());
            Assertions.assertNotEquals(FAIXA_SALARIAL4_ALIQUOTA.value(), calcIRRF.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Nao deve retornar Faixa4 para valores inferiores Faixa4")
        void nao_deve_retornar_Faixa4_para_valores_inferiores() {
            double salarioBrutoMensal = TETO_SALARIAL4_VALOR_INICIAL.value() - 0.1;
            IrrfRequest irrfRequest = IrrfRequestCreator.createValidWithZeroIrrfRequest();
            irrfRequest.setSalarioMensalBruto(salarioBrutoMensal);

            calcIRRF.generateBaseCalculo(irrfRequest);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertNotEquals(FAIXA_SALARIAL4_PARCELA_DEDUTIVEL.value(), calcIRRF.getParcelaADeduzir());
            Assertions.assertNotEquals(FAIXA_SALARIAL4_ALIQUOTA.value(), calcIRRF.getPorcentagemAliquota());
        }
    }

    @Nested()
    @DisplayName("Faixa 5 IRRF: 27.5%")
    class Aliquota5 {
        @Test
        @DisplayName("Deve retornar Faixa5 para valor inicial Faixa5")
        void deve_retornar_Aliquota5_para_valor_inicial() {
            double salarioBrutoMensal = TETO_SALARIAL5_VALOR_INICIAL.value();
            IrrfRequest irrfRequest = IrrfRequestCreator.createValidWithZeroIrrfRequest();
            irrfRequest.setSalarioMensalBruto(salarioBrutoMensal);

            calcIRRF.generateBaseCalculo(irrfRequest);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertEquals(FAIXA_SALARIAL5_PARCELA_DEDUTIVEL.value(), calcIRRF.getParcelaADeduzir());
            Assertions.assertEquals(FAIXA_SALARIAL5_ALIQUOTA.value(), calcIRRF.getPorcentagemAliquota());
        }

        @Test
        @DisplayName("Nao deve retornar Faixa5 para valores inferioes Faixa5")
        void nao_deve_retornar_Aliquota5_para_valores_inferioes() {
            double salarioBrutoMensal = TETO_SALARIAL5_VALOR_INICIAL.value() - 0.1;
            IrrfRequest irrfRequest = IrrfRequestCreator.createValidWithZeroIrrfRequest();
            irrfRequest.setSalarioMensalBruto(salarioBrutoMensal);

            calcIRRF.generateBaseCalculo(irrfRequest);
            calcIRRF.generateFaixaSalarial();

            Assertions.assertNotEquals(FAIXA_SALARIAL5_PARCELA_DEDUTIVEL.value(), calcIRRF.getParcelaADeduzir());
            Assertions.assertNotEquals(FAIXA_SALARIAL5_ALIQUOTA.value(), calcIRRF.getPorcentagemAliquota());
        }
    }
}
