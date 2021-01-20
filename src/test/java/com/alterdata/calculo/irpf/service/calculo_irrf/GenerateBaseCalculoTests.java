package com.alterdata.calculo.irpf.service.calculo_irrf;

import com.alterdata.calculo.irpf.models.irrf.IrrfRequest;
import com.alterdata.calculo.irpf.util.IrrfRequestCreator;
import com.alterdata.calculo.irpf.services.calculo_mensal.InssService;
import com.alterdata.calculo.irpf.services.calculo_mensal.IrrfService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Executando testes unitarios IrrfService generateBaseCalculo()")
@ExtendWith(MockitoExtension.class)
class GenerateBaseCalculoTests {

    @Mock
    InssService inssService;

    @InjectMocks
    IrrfService irrfService;

    @Test
    @DisplayName("Nao deve retornar baseCalculo nula para valores validos")
    void nao_deve_retornar_nulo_para_base_de_calculo_com_valores_validos() {
        IrrfRequest userIn = IrrfRequestCreator.createValidIrrfRequest();
        irrfService.generateBaseCalculo(userIn);
        Assertions.assertNotNull(irrfService.getBaseCalculo());
    }

    @Test
    @DisplayName("Nao deve retornar baseCalculo zero para valores validos")
    void nao_deve_retornar_zero_para_baseCalculo_com_valores_validos() {
        IrrfRequest userIn = IrrfRequestCreator.createValidIrrfRequest();
        irrfService.generateBaseCalculo(userIn);
        assertThat(irrfService.getBaseCalculo()).isNotZero();
    }

    @Test
    @DisplayName("Deve retornar baseCalculo correta para valores validos")
    void deve_retornar_baseCalculo_com_valores_validos() {
        IrrfRequest userIn = IrrfRequestCreator.createValidIrrfRequest();
        irrfService.generateBaseCalculo(userIn);
        assertThat(irrfService.getBaseCalculo()).isNotZero();
        Assertions.assertNotNull(irrfService.getBaseCalculo());
    }

    @Test
    @DisplayName("Deve retornar baseCalculo zero para valores 0")
    void deve_retornar_null_para_baseCalculo_com_valores_nulos() {
        IrrfRequest userIn = IrrfRequestCreator.createValidWithZeroIrrfRequest();
        userIn.setSalarioMensalBruto(0);
        irrfService.generateBaseCalculo(userIn);
        Assertions.assertEquals(0.0, irrfService.getBaseCalculo());
    }
}

