package com.alterdata.calculo.irpf.models.inss;

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

@DisplayName("Executando testes unitarios validacao de campos UserINNSRequest")
public class InssRequestValidationTests {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Nested
    @DisplayName("Testes para validação do campo nome InssRequest")
    class ValidacaoCampoNome {

        @Test()
        @DisplayName("Nao deve lancar ValidationException para nome valido")
        void nao_deve_lancar_ValidationException_para_nome_valido() {
            InssRequest usr = new InssRequest();
            usr.setNome("teste");
            usr.setSalarioMensalBruto(5000);
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(usr);
            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para nome vazio")
        void deve_lancar_ValidationException_para_nome_vazio() {
            InssRequest usr = new InssRequest();
            usr.setNome("");
            usr.setSalarioMensalBruto(5000);
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(usr);
            assertFalse(violations.isEmpty());
        }


        @Test()
        @DisplayName("Deve lancar ValidationException para nome nulo")
        void deve_lancar_ValidationException_para_nome_nulo() {
            InssRequest usr = new InssRequest();
            usr.setNome(null);
            usr.setSalarioMensalBruto(5000);
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(usr);
            assertFalse(violations.isEmpty());
        }


        @Test()
        @DisplayName("Deve lancar ValidationException para nome menor que 3 digitos")
        void deve_lancar_ValidationException_para_nome_menor_que_3_digitos() {
            InssRequest usr = new InssRequest();
            usr.setNome("te");
            usr.setSalarioMensalBruto(5000);
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(usr);
            assertFalse(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para nome maior que 80 digitos")
        void deve_lancar_ValidationException_para_nome_maior_que_80_digitos() {
            InssRequest usr = new InssRequest();
            usr.setNome("Lorem Ipsum is simply dummy text of the printing and typesettings standard dummy text ever ");
            usr.setSalarioMensalBruto(5000);
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(usr);
            assertFalse(violations.isEmpty());
        }
    }


    @Nested
    @DisplayName("Testes para validação do campo salarioMensal para InssRequest")
    class ValidacaoCampoSalario {

        @Test()
        @DisplayName("Nao deve lancar ValidationException para salario valido")
        void nao_deve_lancar_ValidationException_para_salario_valido() {
            InssRequest usr = new InssRequest();
            usr.setNome("Lorem Ipsutext ever");
            usr.setSalarioMensalBruto(1);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(usr);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para salario igual 0")
        void deve_lancar_ValidationException_para_salario_igual_zero() {
            InssRequest usr = new InssRequest();
            usr.setNome("Lorem Ipsutext ever");
            usr.setSalarioMensalBruto(0);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(usr);

            assertFalse(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para salario negativo")
        void deve_lancar_ValidationException_para_salario_negativo() {
            InssRequest usr = new InssRequest();
            usr.setNome("username");
            usr.setSalarioMensalBruto(-1);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(usr);

            assertFalse(violations.isEmpty());
        }


    }


}
