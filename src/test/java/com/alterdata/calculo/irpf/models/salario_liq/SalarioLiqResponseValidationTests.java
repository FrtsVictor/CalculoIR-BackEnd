package com.alterdata.calculo.irpf.models.salario_liq;

import com.alterdata.calculo.irpf.util.CriarSalarioLiqResponse;
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

@DisplayName("Executando testes unitarios validacao de campos SalarioLiqResponse")
public class SalarioLiqResponseValidationTests {
    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Nested
    @DisplayName("Testes para validação do campo nome para SalarioLiqResponse")
    class ValidacaoCampoNome {

        @Test()
        @DisplayName("Nao deve lancar ValidationException para nome valido")
        void nao_deve_lancar_ValidationException_para_nome_valido() {
            SalarioLiqResponse irpfResponse = CriarSalarioLiqResponse.createValidSalarioLiqResponse();

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para nome vazio")
        void deve_lancar_ValidationException_para_nome_vazio() {
            SalarioLiqResponse irpfResponse = CriarSalarioLiqResponse.createValidSalarioLiqResponse();
            irpfResponse.setNome("");

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertFalse(violations.isEmpty());
        }


        @Test()
        @DisplayName("Deve lancar ValidationException para nome nulo")
        void deve_lancar_ValidationException_para_nome_nulo() {
            SalarioLiqResponse irpfResponse = CriarSalarioLiqResponse.createValidSalarioLiqResponse();
            irpfResponse.setNome(null);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertFalse(violations.isEmpty());
        }


        @Test()
        @DisplayName("Deve lancar ValidationException para nome menor que 3 digitos")
        void deve_lancar_ValidationException_para_nome_menor_que_3_digitos() {
            SalarioLiqResponse irpfResponse = CriarSalarioLiqResponse.createValidSalarioLiqResponse();
            irpfResponse.setNome("re");

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertFalse(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para nome maior que 80 digitos")
        void deve_lancar_ValidationException_para_nome_maior_que_80_digitos() {
            SalarioLiqResponse irpfResponse = CriarSalarioLiqResponse.createValidSalarioLiqResponse();
            irpfResponse.setNome("aklolkolkoaklolkolkoaklolkolkoaasdasklolkolkoaklolkolkoaklolkolkoaklolkolkoa");

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertTrue(violations.isEmpty());
        }
    }


    @Nested
    @DisplayName("Testes para validação do campo salarioMensalBruto ppara SalarioLiqResponse")
    class ValidacaoCampoSalarioMensalBruto {

        @Test()
        @DisplayName("Nao deve lancar ValidationException para salarioMensalBruto maior que 0")
        void nao_deve_lancar_ValidationException_para_salarioMensalBruto_maior_que_zero() {
            SalarioLiqResponse irpfResponse = CriarSalarioLiqResponse.createValidSalarioLiqResponse();

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para salarioMensalBruto igual 0")
        void deve_lancar_ValidationException_para_salarioMensalBruto_igual_zero() {
            SalarioLiqResponse irpfResponse = CriarSalarioLiqResponse.createValidSalarioLiqResponse();
            irpfResponse.setSalarioMensalBruto(0);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertFalse(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para salarioMensalBruto negativo")
        void deve_lancar_ValidationException_para_salarioMensalBruto_negativo() {
            SalarioLiqResponse irpfResponse = CriarSalarioLiqResponse.createValidSalarioLiqResponse();
            irpfResponse.setSalarioMensalBruto(-1);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertFalse(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("Testes para validação do campo parcelaADeduzir para SalarioLiqResponse")
    class ValidacaoCampoParcelaADeduzil {

        @Test()
        @DisplayName("Nao deve lancar ValidationException para parcelaADeduzir maior que 0")
        void nao_deve_lancar_ValidationException_para_parcelaADeduzir_maior_que_zero() {
            SalarioLiqResponse irpfResponse = CriarSalarioLiqResponse.createValidSalarioLiqResponse();

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Nao deve lancar ValidationException para parcelaADeduzir igual 0")
        void deve_lancar_ValidationException_para_parcelaADeduzir_0() {
            SalarioLiqResponse irpfResponse = CriarSalarioLiqResponse.createValidSalarioLiqResponse();
            irpfResponse.setParcelaADeduzir(0);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para parcelaADeduzir negativa")
        void deve_lancar_ValidationException_para_parcelaADeduzir_negativa() {
            SalarioLiqResponse irpfResponse = CriarSalarioLiqResponse.createValidSalarioLiqResponse();
            irpfResponse.setParcelaADeduzir(-1);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertFalse(violations.isEmpty());
        }
    }


    @Nested
    @DisplayName("Testes para validação de campo porcentagemAliquota para SalarioLiqResponse")
    class ValidacaoCampoPorcentagemAliquota {

        @Test()
        @DisplayName("Nao deve lancar ValidationException para porcentagemAliquota maior que 0")
        void nao_deve_lancar_ValidationException_para_aliquota_maior_que_zero() {
            SalarioLiqResponse irpfResponse = CriarSalarioLiqResponse.createValidSalarioLiqResponse();

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Nao deve lancar ValidationException para porcentagemAliquota igual 0")
        void deve_lancar_ValidationException_para_parcelaADeduzir_0() {
            SalarioLiqResponse irpfResponse = CriarSalarioLiqResponse.createValidSalarioLiqResponse();
            irpfResponse.setPorcentagemAliquota(0);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para porcentagemAliquota negativa")
        void deve_lancar_ValidationException_para_parcelaADeduzir_negativa() {
            SalarioLiqResponse irpfResponse = CriarSalarioLiqResponse.createValidSalarioLiqResponse();
            irpfResponse.setPorcentagemAliquota(-1);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertFalse(violations.isEmpty());
        }
    }


    @Nested
    @DisplayName("Testes para validação de campo baseDeCalculo para SalarioLiqResponse")
    class ValidacaoCampoBaseDeCalculo {

        @Test()
        @DisplayName("Nao deve lancar ValidationException para baseDeCalculo maior que zero")
        void nao_deve_lancar_ValidationException_para_baseDeCalculo_maior_que_zero() {
            SalarioLiqResponse irpfResponse = CriarSalarioLiqResponse.createValidSalarioLiqResponse();

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Nao deve lancar ValidationException para baseDeCalculo igual zero")
        void nao_deve_lancar_ValidationException_para_baseDeCalculo_igual_zero() {
            SalarioLiqResponse irpfResponse = CriarSalarioLiqResponse.createValidSalarioLiqResponse();
            irpfResponse.setBaseDeCalculo(0);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para baseDeCalculo negativa")
        void deve_lancar_ValidationException_para_dbaseDeCalculo_negativa() {
            SalarioLiqResponse irpfResponse = CriarSalarioLiqResponse.createValidSalarioLiqResponse();
            irpfResponse.setBaseDeCalculo(-1);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertFalse(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("Testes para validação de campo irrf para SalarioLiqResponse")
    class ValidacaoCampoIrrf {

        @Test()
        @DisplayName("Nao deve lancar ValidationException para irrf maior que zero")
        void nao_deve_lancar_ValidationException_para_irrf_maior_que_zero() {
            SalarioLiqResponse irpfResponse = CriarSalarioLiqResponse.createValidSalarioLiqResponse();

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Nao deve lancar ValidationException para irrf igual zero")
        void nao_deve_lancar_ValidationException_para_irrf_igual_zero() {
            SalarioLiqResponse irpfResponse = CriarSalarioLiqResponse.createValidSalarioLiqResponse();
            irpfResponse.setIrrf(0);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para irrf negativa")
        void deve_lancar_ValidationException_para_irrf_negativa() {
            SalarioLiqResponse irpfResponse = CriarSalarioLiqResponse.createValidSalarioLiqResponse();
            irpfResponse.setIrrf(-1);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertFalse(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("Testes para validação de campo inss para SalarioLiqResponse")
    class ValidacaoCampoInss {

        @Test()
        @DisplayName("Nao deve lancar ValidationException para inss maior que zero")
        void nao_deve_lancar_ValidationException_para_inss_maior_que_zero() {
            SalarioLiqResponse irpfResponse = CriarSalarioLiqResponse.createValidSalarioLiqResponse();

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Nao deve lancar ValidationException para inss igual 0")
        void nao_deve_lancar_ValidationException_para_inss_igual_zero() {
            SalarioLiqResponse irpfResponse = CriarSalarioLiqResponse.createValidSalarioLiqResponse();
            irpfResponse.setInss(0);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para inss negativa")
        void deve_lancar_ValidationException_para_inss_negativa() {
            SalarioLiqResponse irpfResponse = CriarSalarioLiqResponse.createValidSalarioLiqResponse();
            irpfResponse.setInss(-1);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertFalse(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("Testes para validação de campo valorDependentes para SalarioLiqResponse")
    class ValidacaoCampoValorDependentes {

        @Test()
        @DisplayName("Nao deve lancar ValidationException para valorDependentes maior que zero")
        void nao_deve_lancar_ValidationException_para_valorDependentes_maior_que_zero() {
            SalarioLiqResponse irpfResponse = CriarSalarioLiqResponse.createValidSalarioLiqResponse();

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Nao deve lancar ValidationException para valorDependentes igual 0")
        void nao_deve_lancar_ValidationException_para_valorDependentes_igual_zero() {
            SalarioLiqResponse irpfResponse = CriarSalarioLiqResponse.createValidSalarioLiqResponse();
            irpfResponse.setValorDependentes(0);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para valorDependentes negativa")
        void deve_lancar_ValidationException_para_valorDependentes_negativa() {
            SalarioLiqResponse irpfResponse = CriarSalarioLiqResponse.createValidSalarioLiqResponse();
            irpfResponse.setValorDependentes(-1);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(irpfResponse);

            assertFalse(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("Testes para validação do campo dependentes para SalarioLiqResponse")
    class ValidacaoCampoDependentes {

        @Test()
        @DisplayName("Nao deve lancar ValidationException para dependentes maior que zero")
        void nao_deve_lancar_ValidationException_para_dependentes_maior_que_zero() {
            SalarioLiqResponse salarioLiqResponse = CriarSalarioLiqResponse.createValidSalarioLiqResponse();

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(salarioLiqResponse);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Nao deve lancar ValidationException para dependentes igual zero")
        void nao_deve_lancar_ValidationException_para_dependentes_igual_zero() {
            SalarioLiqResponse salarioLiqResponse = CriarSalarioLiqResponse.createValidSalarioLiqResponse();
            salarioLiqResponse.setDependentes(0);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(salarioLiqResponse);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para dependentes negativo")
        void deve_lancar_ValidationException_para_dependentes_negativo() {
            SalarioLiqResponse salarioLiqResponse = CriarSalarioLiqResponse.createValidSalarioLiqResponse();
            salarioLiqResponse.setDependentes(-1);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(salarioLiqResponse);

            assertFalse(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("Testes para validação do campo pensaoAlimenticia para SalarioLiqResponse")
    class ValidacaoCampoPensaoAlimenticia {

        @Test()
        @DisplayName("Nao deve lancar ValidationException para pensaoAlimenticia maior que zero")
        void nao_deve_lancar_ValidationException_para_pensaoAlimenticia_maior_que_zero() {
            SalarioLiqResponse salarioLiqResponse = CriarSalarioLiqResponse.createValidSalarioLiqResponse();

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(salarioLiqResponse);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Nao deve lancar ValidationException para pensaoAlimenticia igual zero")
        void nao_deve_lancar_ValidationException_para_pensaoAlimenticia_igual_zero() {
            SalarioLiqResponse salarioLiqResponse = CriarSalarioLiqResponse.createValidSalarioLiqResponse();
            salarioLiqResponse.setPensaoAlimenticia(0);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(salarioLiqResponse);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para pensaoAlimenticia negativa")
        void deve_lancar_ValidationException_para_pensaoAlimenticia_negativa() {
            SalarioLiqResponse salarioLiqResponse = CriarSalarioLiqResponse.createValidSalarioLiqResponse();
            salarioLiqResponse.setPensaoAlimenticia(-1);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(salarioLiqResponse);

            assertFalse(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("Testes para validação do campo totalDescontos para SalarioLiqResponse")
    class ValidacaoCampoTotalDescontos {

        @Test()
        @DisplayName("Nao deve lancar ValidationException para totalDescontos maior que zero")
        void nao_deve_lancar_ValidationException_para_totalDescontos_maior_que_zero() {
            SalarioLiqResponse salarioLiqResponse = CriarSalarioLiqResponse.createValidSalarioLiqResponse();

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(salarioLiqResponse);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Nao deve lancar ValidationException para totalDescontos igual zero")
        void nao_deve_lancar_ValidationException_para_totalDescontos_igual_zero() {
            SalarioLiqResponse salarioLiqResponse = CriarSalarioLiqResponse.createValidSalarioLiqResponse();
            salarioLiqResponse.setTotalDescontos(0);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(salarioLiqResponse);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para totalDescontos negativa")
        void deve_lancar_ValidationException_para_totalDescontos_negativa() {
            SalarioLiqResponse salarioLiqResponse = CriarSalarioLiqResponse.createValidSalarioLiqResponse();
            salarioLiqResponse.setTotalDescontos(-1);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(salarioLiqResponse);

            assertFalse(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("Testes para validação do campo outrosDescontos para SalarioLiqResponse")
    class ValidacaoCampoOutrosDescontos {

        @Test()
        @DisplayName("Nao deve lancar ValidationException para outrosDescontos maior que zero")
        void nao_deve_lancar_ValidationException_para_outrosDescontos_maior_que_zero() {
            SalarioLiqResponse salarioLiqResponse = CriarSalarioLiqResponse.createValidSalarioLiqResponse();

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(salarioLiqResponse);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Nao deve lancar ValidationException para outrosDescontos igual zero")
        void nao_deve_lancar_ValidationException_para_outrosDescontos_igual_zero() {
            SalarioLiqResponse salarioLiqResponse = CriarSalarioLiqResponse.createValidSalarioLiqResponse();
            salarioLiqResponse.setOutrosDescontos(0);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(salarioLiqResponse);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para outrosDescontos negativa")
        void deve_lancar_ValidationException_para_outrosDescontos_negativa() {
            SalarioLiqResponse salarioLiqResponse = CriarSalarioLiqResponse.createValidSalarioLiqResponse();
            salarioLiqResponse.setOutrosDescontos(-1);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(salarioLiqResponse);

            assertFalse(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("Testes para validação do campo salarioLiq para SalarioLiqResponse")
    class ValidacaoCampoSalarioLiq {

        @Test()
        @DisplayName("Nao deve lancar ValidationException para salarioLiq maior que zero")
        void nao_deve_lancar_ValidationException_para_salarioLiq_maior_que_zero() {
            SalarioLiqResponse salarioLiqResponse = CriarSalarioLiqResponse.createValidSalarioLiqResponse();

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(salarioLiqResponse);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Nao deve lancar ValidationException para salarioLiq igual zero")
        void nao_deve_lancar_ValidationException_para_salarioLiq_igual_zero() {
            SalarioLiqResponse salarioLiqResponse = CriarSalarioLiqResponse.createValidSalarioLiqResponse();
            salarioLiqResponse.setSalarioLiquido(0);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(salarioLiqResponse);

            assertTrue(violations.isEmpty());
        }

        @Test()
        @DisplayName("Deve lancar ValidationException para salarioLiq negativa")
        void deve_lancar_ValidationException_para_salarioLiq_negativa() {
            SalarioLiqResponse salarioLiqResponse = CriarSalarioLiqResponse.createValidSalarioLiqResponse();
            salarioLiqResponse.setSalarioLiquido(-1);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set violations = validator.validate(salarioLiqResponse);

            assertFalse(violations.isEmpty());
        }
    }

}
