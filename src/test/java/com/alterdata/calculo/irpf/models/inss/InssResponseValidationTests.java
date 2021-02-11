package com.alterdata.calculo.irpf.models.inss;

import com.alterdata.calculo.irpf.util.CriarInssResponse;
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

@DisplayName("Executando testes unitarios validacao de campos UserINNSResponse")
public class InssResponseValidationTests {

    private Validator validator;

    @BeforeEach
    void setUp() {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Nested
    @DisplayName("Testes para validação do campo nome para InssResponse")
    class ValidacaoCampoNome {

        @Test()
        @DisplayName("Nao deve lancar ValidationException para nome valido")
        void nao_deve_lancar_ValidationException_para_nome_valido() {
            InssResponse inssResponse = CriarInssResponse.createValidINSSResponse();

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(inssResponse);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para nome vazio")
        void deve_lancar_ValidationException_para_nome_vazio() {
            InssResponse inssResponse = CriarInssResponse.createValidINSSResponse();
            inssResponse.setNome("");

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(inssResponse);

            assertFalse(violations.isEmpty());
        }


        @Test()
        @DisplayName("Deve lancar ValidationException para nome nulo")
        void deve_lancar_ValidationException_para_nome_nulo() {
            InssResponse inssResponse = CriarInssResponse.createValidINSSResponse();
            inssResponse.setNome(null);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(inssResponse);

            assertFalse(violations.isEmpty());
        }


        @Test()
        @DisplayName("Deve lancar ValidationException para nome menor que 3 digitos")
        void deve_lancar_ValidationException_para_nome_menor_que_3_digitos() {
            InssResponse inssResponse = CriarInssResponse.createValidINSSResponse();
            inssResponse.setNome("re");

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(inssResponse);

            assertFalse(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para nome maior que 80 digitos")
        void deve_lancar_ValidationException_para_nome_maior_que_80_digitos() {
            InssResponse inssResponse = CriarInssResponse.createValidINSSResponse();
            inssResponse.setNome("aklolkolkoaklolkolkoaklolkolkoaasdasklolkolkoaklolkolkoaklolkolkoaklolkolkoa");

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(inssResponse);

            assertTrue(violations.isEmpty());
        }
    }


    @Nested
    @DisplayName("Testes para validação do campo salarioMensalMensal ppara InssResponse")
    class ValidacaoCampoSalario {

        @Test()
        @DisplayName("Nao deve lancar ValidationException para salarioMensal maior que zero")
        void nao_deve_lancar_ValidationException_para_salarioMensal_maior_que_zero() {
            InssResponse inssResponse = CriarInssResponse.createValidINSSResponse();

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(inssResponse);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para salarioMensal igual zero")
        void deve_lancar_ValidationException_para_salarioMensal_igual_zero() {
            InssResponse inssResponse = CriarInssResponse.createValidINSSResponse();
            inssResponse.setSalarioMensalBruto(0);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(inssResponse);

            assertFalse(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para salarioMensal negativo")
        void deve_lancar_ValidationException_para_salarioMensal_negativo() {
            InssResponse inssResponse = CriarInssResponse.createValidINSSResponse();
            inssResponse.setSalarioMensalBruto(-1);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(inssResponse);

            assertFalse(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("Testes para validação do campo parcelaDedutivel para InssResponse")
    class ValidacaoCampoParcelaDedutivel {

        @Test()
        @DisplayName("Nao deve lancar ValidationException para parcela dedutivel maior que 0")
        void nao_deve_lancar_ValidationException_para_parcela_dedutivel_maior_que_zero() {
            InssResponse inssResponse = CriarInssResponse.createValidINSSResponse();

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(inssResponse);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Nao deve lancar ValidationException para parcelaDedutivel igual 0")
        void deve_lancar_ValidationException_para_parcela_dedutivel_igual_zero() {
            InssResponse inssResponse = CriarInssResponse.createValidINSSResponse();
            inssResponse.setParcelaADeduzir(0);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(inssResponse);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para parcelaDedutivel negativa")
        void deve_lancar_ValidationException_para_parcela_dedutivel_negativa() {
            InssResponse inssResponse = CriarInssResponse.createValidINSSResponse();
            inssResponse.setParcelaADeduzir(-1);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(inssResponse);

            assertFalse(violations.isEmpty());
        }
    }


    @Nested
    @DisplayName("Testes para validação de campo porcentagemAliquota para InssResponse")
    class ValidacaoCampoPorcentagemAliquota {

        @Test()
        @DisplayName("Nao deve lancar ValidationException para porcentagemAliquota maior que zero")
        void nao_deve_lancar_ValidationException_para_aliquota_maior_que_zero() {
            InssResponse inssResponse = CriarInssResponse.createValidINSSResponse();

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(inssResponse);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Nao deve lancar ValidationException para porcentagemAliquota igual zero")
        void deve_lancar_ValidationException_para_porcentagemAliquota_igual_zero() {
            InssResponse inssResponse = CriarInssResponse.createValidINSSResponse();
            inssResponse.setPorcentagemAliquota(0);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(inssResponse);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para porcentagemAliquota negativa")
        void deve_lancar_ValidationException_para_porcentagemAliquota_negativa() {
            InssResponse inssResponse = CriarInssResponse.createValidINSSResponse();
            inssResponse.setPorcentagemAliquota(-1);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(inssResponse);

            assertFalse(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("Testes para validação de campo inss para InssResponse")
    class ValidacaoCampoINNSS {

        @Test()
        @DisplayName("Nao deve lancar ValidationException para inss maior que zero")
        void nao_deve_lancar_ValidationException_para_inss_maior_que_zero() {
            InssResponse inssResponse = CriarInssResponse.createValidINSSResponse();

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(inssResponse);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Nao deve lancar ValidationException para inss igual zero")
        void deve_lancar_ValidationException_para_inss_igual_zero() {
            InssResponse inssResponse = CriarInssResponse.createValidINSSResponse();
            inssResponse.setInss(0);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(inssResponse);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para inss negativa")
        void deve_lancar_ValidationException_para_inss_negativa() {
            InssResponse inssResponse = CriarInssResponse.createValidINSSResponse();
            inssResponse.setPorcentagemAliquota(-1);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(inssResponse);

            assertFalse(violations.isEmpty());
        }
    }
}