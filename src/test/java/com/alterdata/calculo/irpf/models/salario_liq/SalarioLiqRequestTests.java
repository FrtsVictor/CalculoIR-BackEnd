package com.alterdata.calculo.irpf.models.salario_liq;

import com.alterdata.calculo.irpf.util.CriarSalarioLiqRequest;
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

@DisplayName("Executando testes validacao de campos SalarioLiqRequest")
public class SalarioLiqRequestTests {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Nested
    @DisplayName("Testes para validação do campo nome para SalarioLiqRequest")
    class ValidacaoNome {

        @Test()
        @DisplayName("Nao deve lancar ValidationException para nome valido")
        void nao_deve_lancar_ValidationException_para_nome_valido() {
            SalarioLiqRequest salarioLiqRequest = CriarSalarioLiqRequest.createValidUserSalarioLiq();

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(salarioLiqRequest);
            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para nome vazio")
        void deve_lancar_ValidationException_para_nome_vazio() {
            SalarioLiqRequest salarioLiqRequest = CriarSalarioLiqRequest.createValidUserSalarioLiq();
            salarioLiqRequest.setNome("");

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(salarioLiqRequest);
            assertFalse(violations.isEmpty());
        }


        @Test()
        @DisplayName("Deve lancar ValidationException para nome nulo")
        void deve_lancar_ValidationException_para_nome_nulo() {
            SalarioLiqRequest salarioLiqRequest = CriarSalarioLiqRequest.createValidUserSalarioLiq();
            salarioLiqRequest.setNome(null);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(salarioLiqRequest);
            assertFalse(violations.isEmpty());
        }


        @Test()
        @DisplayName("Deve lancar ValidationException para nome menor que 3 digitos")
        void deve_lancar_ValidationException_para_nome_menor_que_3_digitos() {
            SalarioLiqRequest salarioLiqRequest = CriarSalarioLiqRequest.createValidUserSalarioLiq();
            salarioLiqRequest.setNome("te");

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(salarioLiqRequest);
            assertFalse(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para nome maior que 80 digitos")
        void deve_lancar_ValidationException_para_nome_maior_que_80_digitos() {
            SalarioLiqRequest salarioLiqRequest = CriarSalarioLiqRequest.createValidUserSalarioLiq();
            salarioLiqRequest.setNome("Lorem Ipsum is simply dummy text of the printing and typesettings standard dummy text ever ");

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(salarioLiqRequest);
            assertFalse(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("Testes para validação do campo salarioMensalBruto para SalarioLiqRequest")
    class ValidacaoCampoSalario {

        @Test()
        @DisplayName("Nao deve lancar ValidationException para salarioMensalBruto maior que zero")
        void nao_deve_lancar_ValidationException_para_salarioMensalBruto_maior_que_zero() {
            SalarioLiqRequest salarioLiqRequest = CriarSalarioLiqRequest.createValidUserSalarioLiq();

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(salarioLiqRequest);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para salarioMensalBruto igual zero")
        void deve_lancar_ValidationException_para_salarioMensalBruto_igual_zero() {
            SalarioLiqRequest salarioLiqRequest = CriarSalarioLiqRequest.createValidUserSalarioLiq();
            salarioLiqRequest.setSalarioMensalBruto(0);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(salarioLiqRequest);

            assertFalse(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para salarioMensalBruto negativo")
        void deve_lancar_ValidationException_para_salarioMensalBruto_negativo() {
            SalarioLiqRequest salarioLiqRequest = CriarSalarioLiqRequest.createValidUserSalarioLiq();
            salarioLiqRequest.setSalarioMensalBruto(-1);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(salarioLiqRequest);

            assertFalse(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("Testes para validação do campo dependentes para SalarioLiqRequest")
    class ValidacaoCampoDependentes {

        @Test()
        @DisplayName("Nao deve lancar ValidationException para dependentes maior que 0")
        void nao_deve_lancar_ValidationException_para_dependentes_maior_que_zero() {
            SalarioLiqRequest salarioLiqRequest = CriarSalarioLiqRequest.createValidUserSalarioLiq();

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(salarioLiqRequest);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Nao deve lancar ValidationException para dependentes igual zero")
        void nao_deve_lancar_ValidationException_para_dependentes_igual_zero() {
            SalarioLiqRequest salarioLiqRequest = CriarSalarioLiqRequest.createValidUserSalarioLiq();
            salarioLiqRequest.setDependentes(0);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(salarioLiqRequest);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para dependentes negativo")
        void deve_lancar_ValidationException_para_dependentes_negativo() {
            SalarioLiqRequest salarioLiqRequest = CriarSalarioLiqRequest.createValidUserSalarioLiq();
            salarioLiqRequest.setDependentes(-1);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(salarioLiqRequest);

            assertFalse(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("Testes para validação do campo pensaoAlimenticia para SalarioLiqRequest")
    class ValidacaoCampoPensaoAlimenticia {

        @Test()
        @DisplayName("Nao deve lancar ValidationException para pensaoAlimenticia maior que 0")
        void nao_deve_lancar_ValidationException_para_pensaoAlimenticia_maior_que_zero() {
            SalarioLiqRequest salarioLiqRequest = CriarSalarioLiqRequest.createValidUserSalarioLiq();

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(salarioLiqRequest);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Nao deve lancar ValidationException para pensaoAlimenticia igual zero")
        void nao_deve_lancar_ValidationException_para_pensaoAlimenticia_igual_zero() {
            SalarioLiqRequest salarioLiqRequest = CriarSalarioLiqRequest.createValidUserSalarioLiq();
            salarioLiqRequest.setPensaoAlimenticia(0);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(salarioLiqRequest);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para pensaoAlimenticia negativa")
        void deve_lancar_ValidationException_para_pensaoAlimenticia_negativa() {
            SalarioLiqRequest salarioLiqRequest = CriarSalarioLiqRequest.createValidUserSalarioLiq();
            salarioLiqRequest.setPensaoAlimenticia(-1);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(salarioLiqRequest);

            assertFalse(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("Testes para validação do campo outrosDescontos para SalarioLiqRequest")
    class ValidacaoCampoOutrosDescontos {

        @Test()
        @DisplayName("Nao deve lancar ValidationException para outrosDescontos maior que 0")
        void nao_deve_lancar_ValidationException_para_outrosDescontos_maior_que_zero() {
            SalarioLiqRequest salarioLiqRequest = CriarSalarioLiqRequest.createValidUserSalarioLiq();

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(salarioLiqRequest);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Nao deve lancar ValidationException para outrosDescontos igual zero")
        void nao_deve_lancar_ValidationException_para_outrosDescontos_igual_zero() {
            SalarioLiqRequest salarioLiqRequest = CriarSalarioLiqRequest.createValidUserSalarioLiq();
            salarioLiqRequest.setOutrosDescontos(0);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(salarioLiqRequest);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para outrosDescontos negativa")
        void deve_lancar_ValidationException_para_outrosDescontos_negativa() {
            SalarioLiqRequest salarioLiqRequest = CriarSalarioLiqRequest.createValidUserSalarioLiq();
            salarioLiqRequest.setOutrosDescontos(-1);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(salarioLiqRequest);

            assertFalse(violations.isEmpty());
        }
    }

}
