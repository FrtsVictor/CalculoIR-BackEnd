package com.alterdata.calculo.irpf.User;

import com.alterdata.calculo.irpf.dto.UserDTO;
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

@DisplayName("Executando testes validacao de campos UserDTO")
class UserDTOValidationTests {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Nested
    @DisplayName("Testes para validação do campo nome")
    class ValidacaoNome{

        @Test()
        @DisplayName("Nao deve lancar ValidationException para nome vazio")
        void nao_deve_lancar_ValidationException_para_nome_valido() {
            UserDTO usr = UserDTO.builder()
                    .nome("teste")
                    .username("teste")
                    .password("teste")
                    .build();
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(usr);
            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para nome vazio")
        void deve_lancar_ValidationException_para_nome_vazio() {
            UserDTO usr = UserDTO.builder()
                    .nome("")
                    .username("teste")
                    .password("teste")
                    .build();
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(usr);
            assertFalse(violations.isEmpty());
        }


        @Test()
        @DisplayName("Deve lancar ValidationException para nome nulo")
        void deve_lancar_ValidationException_para_nome_nulo() {
            UserDTO usr = UserDTO.builder()
                    .nome(null)
                    .username("teste")
                    .password("teste")
                    .build();
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(usr);
            assertFalse(violations.isEmpty());
        }


        @Test()
        @DisplayName("Deve lancar ValidationException para nome menor que length 3")
        void deve_lancar_ValidationException_para_nome_menor_que_min() {
            UserDTO usr = UserDTO.builder()
                    .nome("a")
                    .username("teste")
                    .password("teste")
                    .build();
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(usr);
            assertFalse(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para nome maior que length 80")
        void deve_lancar_ValidationException_para_nome_maior_max() {
            UserDTO usr = UserDTO.builder()
                    .nome("Lorem Ipsum is simply dummy text of the printing and typesettings standard dummy text ever ")
                    .username("teste")
                    .password("teste")
                    .build();
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(usr);
            assertFalse(violations.isEmpty());
        }
    }

    @Nested
    class ValidacaoUsername{
        @Test()
        @DisplayName("Nao deve lancar ValidationException para username valido")
        void nao_deve_lancar_ValidationException_para_username_valido() {
            UserDTO usr = UserDTO.builder()
                    .nome("teste")
                    .username("teste")
                    .password("teste")
                    .build();
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(usr);
            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para username vazio")
        void deve_lancar_ValidationException_para_username_vazio() {
            UserDTO usr = UserDTO.builder()
                    .nome("teste")
                    .username("")
                    .password("teste")
                    .build();
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(usr);
            assertFalse(violations.isEmpty());
        }


        @Test()
        @DisplayName("Deve lancar ValidationException para username nulo")
        void deve_lancar_ValidationException_para_username_nulo() {
            UserDTO usr = UserDTO.builder()
                    .nome("teste")
                    .username(null)
                    .password("teste")
                    .build();
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(usr);
            assertFalse(violations.isEmpty());
        }


        @Test()
        @DisplayName("Deve lancar ValidationException para username menor que length 5")
        void deve_lancar_ValidationException_para_username_menor_que_min() {
            UserDTO usr = UserDTO.builder()
                    .nome("teste")
                    .username("test")
                    .password("teste")
                    .build();
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(usr);
            assertFalse(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para username maior que length 50")
        void deve_lancar_ValidationException_para_username_maior_max() {
            UserDTO usr = UserDTO.builder()
                    .nome("teste")
                    .username("Lorem Ipsum is simply dummy text of the printing and dummy text ever ")
                    .password("teste")
                    .build();
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(usr);
            assertFalse(violations.isEmpty());
        }
    }

    @Nested
    class ValidacaoPassword{
        @Test()
        @DisplayName("Nao deve lancar ValidationException para password valido")
        void nao_deve_lancar_ValidationException_para_password_valido() {
            UserDTO usr = UserDTO.builder()
                    .nome("teste")
                    .username("teste")
                    .password("teste")
                    .build();
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(usr);
            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para password vazio")
        void deve_lancar_ValidationException_para_password_vazio() {
            UserDTO usr = UserDTO.builder()
                    .nome("teste")
                    .username("teste")
                    .password("")
                    .build();
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(usr);
            assertFalse(violations.isEmpty());
        }


        @Test()
        @DisplayName("Deve lancar ValidationException para password nulo")
        void deve_lancar_ValidationException_para_password_nulo() {
            UserDTO usr = UserDTO.builder()
                    .nome("teste")
                    .username("teste")
                    .password(null)
                    .build();
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(usr);
            assertFalse(violations.isEmpty());
        }


        @Test()
        @DisplayName("Deve lancar ValidationException para password menor que length 5")
        void deve_lancar_ValidationException_para_password_menor_que_min() {
            UserDTO usr = UserDTO.builder()
                    .nome("teste")
                    .username("teste")
                    .password("test")
                    .build();
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(usr);
            assertFalse(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para password maior que length 50")
        void deve_lancar_ValidationException_para_password_maior_max() {
            UserDTO usr = UserDTO.builder()
                    .nome("teste")
                    .username("teste")
                    .password("Lorem Ipsum is simply dummy text of the printing and dummy text ever ing and dummy te")
                    .build();
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(usr);
            assertFalse(violations.isEmpty());
        }
    }

}
