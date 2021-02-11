package com.alterdata.calculo.irpf.models.irpf;

import com.alterdata.calculo.irpf.util.CiarIrpfResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Executando testes unitarios validacao de campos IrpfResponse")
public class IrpfResponseValidationTests {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Nested
    @DisplayName("Testes para validação do campo nome para IrpfResponse")
    class ValidacaoCampoNome {

        @Test()
        @DisplayName("Nao deve lancar ValidationException para nome valido")
        void nao_deve_lancar_ValidationException_para_nome_valido() {
            IrpfResponse irpfResponse = CiarIrpfResponse.createValidIRPFResponse();

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para nome vazio")
        void deve_lancar_ValidationException_para_nome_vazio() {
            IrpfResponse irpfResponse = CiarIrpfResponse.createValidIRPFResponse();
            irpfResponse.setNome("");

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertFalse(violations.isEmpty());
        }


        @Test()
        @DisplayName("Deve lancar ValidationException para nome nulo")
        void deve_lancar_ValidationException_para_nome_nulo() {
            IrpfResponse irpfResponse = CiarIrpfResponse.createValidIRPFResponse();
            irpfResponse.setNome(null);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertFalse(violations.isEmpty());
        }


        @Test()
        @DisplayName("Deve lancar ValidationException para nome menor que 3 digitos")
        void deve_lancar_ValidationException_para_nome_menor_que_3_digitos() {
            IrpfResponse irpfResponse = CiarIrpfResponse.createValidIRPFResponse();
            irpfResponse.setNome("re");

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertFalse(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para nome maior que 80 digitos")
        void deve_lancar_ValidationException_para_nome_maior_que_80_digitos() {
            IrpfResponse irpfResponse = CiarIrpfResponse.createValidIRPFResponse();
            irpfResponse.setNome("aklolkolkoaklolkolkoaklolkolkoaasdasklolkolkoaklolkolkoaklolkolkoaklolkolkoa");

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertTrue(violations.isEmpty());
        }
    }


    @Nested
    @DisplayName("Testes para validação do campo rendimentoAnualBruto ppara IrpfResponse")
    class ValidacaoCampoRendimentoAnualBruto {

        @Test()
        @DisplayName("Nao deve lancar ValidationException para rendimentoAnualBruto maior que 0")
        void nao_deve_lancar_ValidationException_para_rendimentoAnualBruto_maior_que_zero() {
            IrpfResponse irpfResponse = CiarIrpfResponse.createValidIRPFResponse();

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para rendimentoAnualBruto igual 0")
        void deve_lancar_ValidationException_para_rendimentoAnualBruto_igual_zero() {
            IrpfResponse irpfResponse = CiarIrpfResponse.createValidIRPFResponse();
            irpfResponse.setRendimentoAnualBruto(0);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertFalse(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para rendimentoAnualBruto negativo")
        void deve_lancar_ValidationException_para_rendimentoAnualBruto_negativo() {
            IrpfResponse irpfResponse = CiarIrpfResponse.createValidIRPFResponse();
            irpfResponse.setRendimentoAnualBruto(-1);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertFalse(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("Testes para validação do campo parcelaDedutivel para IrpfResponse")
    class ValidacaoCampoParcelaDedutivel {

        @Test()
        @DisplayName("Nao deve lancar ValidationException para parcelaDedutivel maior que 0")
        void nao_deve_lancar_ValidationException_para_parcelaDedutivel_maior_que_zero() {
            IrpfResponse irpfResponse = CiarIrpfResponse.createValidIRPFResponse();

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Nao deve lancar ValidationException para parcelaDedutivel igual 0")
        void deve_lancar_ValidationException_para_parcelaDedutivel_0() {
            IrpfResponse irpfResponse = CiarIrpfResponse.createValidIRPFResponse();
            irpfResponse.setParcelaDedutivel(0);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para parcelaDedutivel negativa")
        void deve_lancar_ValidationException_para_parcelaDedutivel_negativa() {
            IrpfResponse irpfResponse = CiarIrpfResponse.createValidIRPFResponse();
            irpfResponse.setParcelaDedutivel(-1);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertFalse(violations.isEmpty());
        }
    }


    @Nested
    @DisplayName("Testes para validação de campo porcentagemAliquota para IrpfResponse")
    class ValidacaoCampoPorcentagemAliquota {

        @Test()
        @DisplayName("Nao deve lancar ValidationException para porcentagemAliquota maior que 0")
        void nao_deve_lancar_ValidationException_para_aliquota_maior_que_zero() {
            IrpfResponse irpfResponse = CiarIrpfResponse.createValidIRPFResponse();

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Nao deve lancar ValidationException para porcentagemAliquota igual 0")
        void deve_lancar_ValidationException_para_parcelaDedutivel_0() {
            IrpfResponse irpfResponse = CiarIrpfResponse.createValidIRPFResponse();
            irpfResponse.setPorcentagemAliquota(0);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para porcentagemAliquota negativa")
        void deve_lancar_ValidationException_para_parcelaDedutivel_negativa() {
            IrpfResponse irpfResponse = CiarIrpfResponse.createValidIRPFResponse();
            irpfResponse.setPorcentagemAliquota(-1);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertFalse(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("Testes para validação de campo deducaoSimplificada para IrpfResponse")
    class ValidacaoCampoDeducaoSimplificada {

        @Test()
        @DisplayName("Nao deve lancar ValidationException para deducaoSimplificada maior que 0")
        void nao_deve_lancar_ValidationException_para_deducaoSimplificada_maior_que_zero() {
            IrpfResponse irpfResponse = CiarIrpfResponse.createValidIRPFResponse();

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("deve lancar ValidationException para deducaoSimplificada igual 0")
        void deve_lancar_ValidationException_para_deducaoSimplificada_igual_a_zero() {
            IrpfResponse irpfResponse = CiarIrpfResponse.createValidIRPFResponse();
            irpfResponse.setDeducaoSimplificada(0);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertFalse(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para deducaoSimplificada negativa")
        void deve_lancar_ValidationException_para_deducaoSimplificada_negativa() {
            IrpfResponse irpfResponse = CiarIrpfResponse.createValidIRPFResponse();
            irpfResponse.setDeducaoSimplificada(-1);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertFalse(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("Testes para validação de campo baseDeCalculo para IrpfResponse")
    class ValidacaoCampoBaseDeCalculo {

        @Test()
        @DisplayName("Nao deve lancar ValidationException para baseDeCalculo maior que 0")
        void nao_deve_lancar_ValidationException_para_baseDeCalculo_maior_que_zero() {
            IrpfResponse irpfResponse = CiarIrpfResponse.createValidIRPFResponse();

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("deve lancar ValidationException para  baseDeCalculo igual 0")
        void deve_lancar_ValidationException_para_baseDeCalculo_igual_a_zero() {
            IrpfResponse irpfResponse = CiarIrpfResponse.createValidIRPFResponse();
            irpfResponse.setBaseDeCalculo(0);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertFalse(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para baseDeCalculo negativa")
        void deve_lancar_ValidationException_para_dbaseDeCalculo_negativa() {
            IrpfResponse irpfResponse = CiarIrpfResponse.createValidIRPFResponse();
            irpfResponse.setBaseDeCalculo(-1);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertFalse(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("Testes para validação de campo impostoRenda para IrpfResponse")
    class ValidacaoCampoImpostoRenda {

        @Test()
        @DisplayName("Nao deve lancar ValidationException para impostoRenda maior que 0")
        void nao_deve_lancar_ValidationException_para_impostoRenda_maior_que_zero() {
            IrpfResponse irpfResponse = CiarIrpfResponse.createValidIRPFResponse();

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Nao deve lancar ValidationException para impostoRenda igual 0")
        void nao_deve_lancar_ValidationException_para_impostoRenda_igual_zero() {
            IrpfResponse irpfResponse = CiarIrpfResponse.createValidIRPFResponse();
            irpfResponse.setImpostoRenda(0);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para impostoRenda negativa")
        void deve_lancar_ValidationException_para_impostoRenda_negativa() {
            IrpfResponse irpfResponse = CiarIrpfResponse.createValidIRPFResponse();
            irpfResponse.setImpostoRenda(-1);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertFalse(violations.isEmpty());
        }
    }
}
