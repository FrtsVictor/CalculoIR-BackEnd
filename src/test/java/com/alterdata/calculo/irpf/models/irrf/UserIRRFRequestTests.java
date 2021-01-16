package com.alterdata.calculo.irpf.models.irrf;

import com.alterdata.calculo.irpf.models.irrf.UserIRRFRequest;
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

@DisplayName("Executando testes validacao de campos UserIRRFIn")
public class UserIRRFRequestTests {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Nested
    @DisplayName("Testes para validação do campo nome para UserIRRFRequest")
    class ValidacaoNome{

        @Test()
        @DisplayName("Nao deve lancar ValidationException para nome valido")
        void nao_deve_lancar_ValidationException_para_nome_valido() {
            UserIRRFRequest usr = new UserIRRFRequest();
            usr.setNome("teste");
            usr.setSalarioMensalBruto(5000);
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(usr);
            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para nome vazio")
        void deve_lancar_ValidationException_para_nome_vazio() {
            UserIRRFRequest usr = new UserIRRFRequest();
            usr.setNome("");
            usr.setSalarioMensalBruto(5000);
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(usr);
            assertFalse(violations.isEmpty());
        }


        @Test()
        @DisplayName("Deve lancar ValidationException para nome nulo")
        void deve_lancar_ValidationException_para_nome_nulo() {
            UserIRRFRequest usr = new UserIRRFRequest();
            usr.setNome(null);
            usr.setSalarioMensalBruto(5000);
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(usr);
            assertFalse(violations.isEmpty());
        }


        @Test()
        @DisplayName("Deve lancar ValidationException para nome menor que 3 digitos")
        void deve_lancar_ValidationException_para_nome_menor_que_min() {
            UserIRRFRequest usr = new UserIRRFRequest();
            usr.setNome("te");
            usr.setSalarioMensalBruto(5000);
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(usr);
            assertFalse(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para nome maior que 80 digitos")
        void deve_lancar_ValidationException_para_nome_maior_max() {
            UserIRRFRequest usr = new UserIRRFRequest();
            usr.setNome("Lorem Ipsum is simply dummy text of the printing and typesettings standard dummy text ever ");
            usr.setSalarioMensalBruto(5000);
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(usr);
            assertFalse(violations.isEmpty());
        }
    }


}
