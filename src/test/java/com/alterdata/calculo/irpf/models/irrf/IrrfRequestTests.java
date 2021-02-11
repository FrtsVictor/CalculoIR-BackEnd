package com.alterdata.calculo.irpf.models.irrf;

import com.alterdata.calculo.irpf.util.CriarIrrfRequest;
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

@DisplayName("Executando testes validacao de campos IrrfRequest")
public class IrrfRequestTests {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Nested
    @DisplayName("Testes para validação do campo nome para IrrfRequest")
    class ValidacaoNome {

        @Test()
        @DisplayName("Nao deve lancar ValidationException para nome valido")
        void nao_deve_lancar_ValidationException_para_nome_valido() {
            IrrfRequest irrfRequest = CriarIrrfRequest.createValidIrrfRequest();

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irrfRequest);
            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para nome vazio")
        void deve_lancar_ValidationException_para_nome_vazio() {
            IrrfRequest irrfRequest = CriarIrrfRequest.createValidIrrfRequest();
            irrfRequest.setNome("");

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irrfRequest);
            assertFalse(violations.isEmpty());
        }


        @Test()
        @DisplayName("Deve lancar ValidationException para nome nulo")
        void deve_lancar_ValidationException_para_nome_nulo() {
            IrrfRequest irrfRequest = CriarIrrfRequest.createValidIrrfRequest();
            irrfRequest.setNome(null);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irrfRequest);
            assertFalse(violations.isEmpty());
        }


        @Test()
        @DisplayName("Deve lancar ValidationException para nome menor que 3 digitos")
        void deve_lancar_ValidationException_para_nome_menor_que_3_digitos() {
            IrrfRequest irrfRequest = CriarIrrfRequest.createValidIrrfRequest();
            irrfRequest.setNome("te");

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irrfRequest);
            assertFalse(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para nome maior que 80 digitos")
        void deve_lancar_ValidationException_para_nome_maior_que_80_digitos() {
            IrrfRequest irrfRequest = CriarIrrfRequest.createValidIrrfRequest();
            irrfRequest.setNome("Lorem Ipsum is simply dummy text of the printing and typesettings standard dummy text ever ");

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irrfRequest);
            assertFalse(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("Testes para validação do campo salarioMensalBruto para IrrfRequest")
    class ValidacaoCampoSalario {

        @Test()
        @DisplayName("Nao deve lancar ValidationException para salarioMensalBruto maior que zero")
        void nao_deve_lancar_ValidationException_para_salarioMensalBruto_maior_que_zero() {
            IrrfRequest irrfRequest = CriarIrrfRequest.createValidIrrfRequest();

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irrfRequest);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para salarioMensalBruto igual zero")
        void deve_lancar_ValidationException_para_salarioMensalBruto_igual_zero() {
            IrrfRequest irrfRequest = CriarIrrfRequest.createValidIrrfRequest();
            irrfRequest.setSalarioMensalBruto(0);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irrfRequest);

            assertFalse(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para salarioMensalBruto negativo")
        void deve_lancar_ValidationException_para_salarioMensalBruto_negativo() {
            IrrfRequest irrfRequest = CriarIrrfRequest.createValidIrrfRequest();
            irrfRequest.setSalarioMensalBruto(-1);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irrfRequest);

            assertFalse(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("Testes para validação do campo dependentes para IrrfRequest")
    class ValidacaoCampoDependentes {

        @Test()
        @DisplayName("Nao deve lancar ValidationException para dependentes maior que 0")
        void nao_deve_lancar_ValidationException_para_dependentes_maior_que_zero() {
            IrrfRequest irrfRequest = CriarIrrfRequest.createValidIrrfRequest();

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irrfRequest);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Nao deve lancar ValidationException para dependentes igual zero")
        void nao_deve_lancar_ValidationException_para_dependentes_igual_zero() {
            IrrfRequest irrfRequest = CriarIrrfRequest.createValidIrrfRequest();
            irrfRequest.setDependentes(0);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irrfRequest);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para dependentes negativo")
        void deve_lancar_ValidationException_para_dependentes_negativo() {
            IrrfRequest irrfRequest = CriarIrrfRequest.createValidIrrfRequest();
            irrfRequest.setDependentes(-1);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irrfRequest);

            assertFalse(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("Testes para validação do campo pensaoAlimenticia para IrrfRequest")
    class ValidacaoCampoPensaoAlimenticia {

        @Test()
        @DisplayName("Nao deve lancar ValidationException para pensaoAlimenticia maior que 0")
        void nao_deve_lancar_ValidationException_para_pensaoAlimenticia_maior_que_zero() {
            IrrfRequest irrfRequest = CriarIrrfRequest.createValidIrrfRequest();

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irrfRequest);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Nao deve lancar ValidationException para pensaoAlimenticia igual zero")
        void nao_deve_lancar_ValidationException_para_pensaoAlimenticia_igual_zero() {
            IrrfRequest irrfRequest = CriarIrrfRequest.createValidIrrfRequest();
            irrfRequest.setPensaoAlimenticia(0);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irrfRequest);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para pensaoAlimenticia negativa")
        void deve_lancar_ValidationException_para_pensaoAlimenticia_negativa() {
            IrrfRequest irrfRequest = CriarIrrfRequest.createValidIrrfRequest();
            irrfRequest.setPensaoAlimenticia(-1);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irrfRequest);

            assertFalse(violations.isEmpty());
        }
    }
}
