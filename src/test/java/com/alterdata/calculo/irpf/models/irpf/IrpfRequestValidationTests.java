package com.alterdata.calculo.irpf.models.irpf;

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

@DisplayName("Executando testes unitarios validacao de campos IrpfRequest")
public class IrpfRequestValidationTests {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Nested
    @DisplayName("Testes para validação do campo nome para IrpfRequest")
    class ValidacaoNome{

        @Test()
        @DisplayName("Nao deve lancar ValidationException para nome valido")
        void nao_deve_lancar_ValidationException_para_nome_valido() {
            IrpfRequest irpfRequest = new IrpfRequest();
            irpfRequest.setNome("teste");
            irpfRequest.setRendimentoAnualBruto(5000);
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfRequest);
            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para nome vazio")
        void deve_lancar_ValidationException_para_nome_vazio() {
            IrpfRequest irpfRequest = new IrpfRequest();
            irpfRequest.setNome("");
            irpfRequest.setRendimentoAnualBruto(5000);
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfRequest);
            assertFalse(violations.isEmpty());
        }


        @Test()
        @DisplayName("Deve lancar ValidationException para nome nulo")
        void deve_lancar_ValidationException_para_nome_nulo() {
            IrpfRequest irpfRequest = new IrpfRequest();
            irpfRequest.setNome(null);
            irpfRequest.setRendimentoAnualBruto(5000);
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfRequest);
            assertFalse(violations.isEmpty());
        }


        @Test()
        @DisplayName("Deve lancar ValidationException para nome menor que 3 digitos")
        void deve_lancar_ValidationException_para_nome_menor_que_min() {
            IrpfRequest irpfRequest = new IrpfRequest();
            irpfRequest.setNome("te");
            irpfRequest.setRendimentoAnualBruto(5000);
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfRequest);
            assertFalse(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para nome maior que 80 digitos")
        void deve_lancar_ValidationException_para_nome_maior_max() {
            IrpfRequest irpfRequest = new IrpfRequest();
            irpfRequest.setNome("Lorem Ipsum is simply dummy text of the printing and typesettings standard dummy text ever ");
            irpfRequest.setRendimentoAnualBruto(5000);
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfRequest);
            assertFalse(violations.isEmpty());
        }
    }


    @Nested
    @DisplayName("Testes para validação do campo salarioMensal para IrpfRequest")
    class ValidacaoCampoSalario {

        @Test()
        @DisplayName("Nao deve lancar ValidationException para salario maior que 0")
        void nao_deve_lancar_ValidationException_para_salario_valido() {
            IrpfRequest irpfRequest = new IrpfRequest();
            irpfRequest.setNome("Lorem Ipsutext ever");
            irpfRequest.setRendimentoAnualBruto(1);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfRequest);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para salario igual 0")
        void deve_lancar_ValidationException_para_salario_0() {
            IrpfRequest irpfRequest = new IrpfRequest();
            irpfRequest.setNome("Lorem Ipsutext ever");
            irpfRequest.setRendimentoAnualBruto(0);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfRequest);

            assertFalse(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para salario negativo")
        void deve_lancar_ValidationException_para_salario_negativo() {
            IrpfRequest irpfRequest = new IrpfRequest();
            irpfRequest.setNome("username");
            irpfRequest.setRendimentoAnualBruto(-1);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfRequest);

            assertFalse(violations.isEmpty());
        }


    }

}
