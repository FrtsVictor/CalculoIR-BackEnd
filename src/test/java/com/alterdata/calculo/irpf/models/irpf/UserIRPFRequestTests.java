package com.alterdata.calculo.irpf.models.irpf;

import com.alterdata.calculo.irpf.models.account.User;
import com.alterdata.calculo.irpf.models.irpf.UserIRPFRequest;
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

@DisplayName("Executando testes unitarios validacao de campos UserIRRFIn")
public class UserIRPFRequestTests {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Nested
    @DisplayName("Testes para validação do campo nome para UserIRPFRequest")
    class ValidacaoNome{

        @Test()
        @DisplayName("Nao deve lancar ValidationException para nome valido")
        void nao_deve_lancar_ValidationException_para_nome_valido() {
            UserIRPFRequest usr = new UserIRPFRequest();
            usr.setNome("teste");
            usr.setRendimentoAnualBruto(5000);
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(usr);
            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para nome vazio")
        void deve_lancar_ValidationException_para_nome_vazio() {
            UserIRPFRequest usr = new UserIRPFRequest();
            usr.setNome("");
            usr.setRendimentoAnualBruto(5000);
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(usr);
            assertFalse(violations.isEmpty());
        }


        @Test()
        @DisplayName("Deve lancar ValidationException para nome nulo")
        void deve_lancar_ValidationException_para_nome_nulo() {
            UserIRPFRequest usr = new UserIRPFRequest();
            usr.setNome(null);
            usr.setRendimentoAnualBruto(5000);
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(usr);
            assertFalse(violations.isEmpty());
        }


        @Test()
        @DisplayName("Deve lancar ValidationException para nome menor que 3 digitos")
        void deve_lancar_ValidationException_para_nome_menor_que_min() {
            UserIRPFRequest usr = new UserIRPFRequest();
            usr.setNome("te");
            usr.setRendimentoAnualBruto(5000);
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(usr);
            assertFalse(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para nome maior que 80 digitos")
        void deve_lancar_ValidationException_para_nome_maior_max() {
            UserIRPFRequest usr = new UserIRPFRequest();
            usr.setNome("Lorem Ipsum is simply dummy text of the printing and typesettings standard dummy text ever ");
            usr.setRendimentoAnualBruto(5000);
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(usr);
            assertFalse(violations.isEmpty());
        }
    }


}
