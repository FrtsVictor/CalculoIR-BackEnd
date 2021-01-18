package com.alterdata.calculo.irpf.service.calculo_irpf;

import com.alterdata.calculo.irpf.models.irpf.UserIRPFRequest;
import com.alterdata.calculo.irpf.models.irpf.UserIRPFResponse;
import com.alterdata.calculo.irpf.services.calculo_anual.CalcAliqAnualSimplesIRPFService;
import com.alterdata.calculo.irpf.services.calculo_anual.CalcIRSimplesService;
import org.junit.jupiter.api.*;

@DisplayName("Executando testes unitarios CalcIRPF generateIR()")
class CalculoIRPFTests {

        private TestInfo testInfo;
        private TestReporter testReporter;

        private CalcAliqAnualSimplesIRPFService aliquotaAnualService;
        private UserIRPFRequest usuarioCSVIn;
        private CalcIRSimplesService calcIRSimplesService;


    @BeforeEach
        void initEach(TestReporter testReporter, TestInfo testInfo) {
            aliquotaAnualService = new CalcAliqAnualSimplesIRPFService();
            usuarioCSVIn = new UserIRPFRequest();
            calcIRSimplesService = new CalcIRSimplesService(aliquotaAnualService);

            this.testReporter = testReporter;
            this.testInfo = testInfo;

            testReporter.publishEntry("Running -> " +
                    testInfo.getDisplayName() +
                    " with tag -> " + testInfo.getTags());
        }


    @Test
    @DisplayName("Deve retornar UserIRPFResponse para valores de calculo validos")
    void deve_retornar_UserIRPFOut_para_valores_validos() {
        double rendimentoAnualBruto = 40000;
        UserIRPFRequest usrIn= new UserIRPFRequest("Test" ,rendimentoAnualBruto);

        aliquotaAnualService.generateAliquota(usrIn);
        UserIRPFResponse usrOut = calcIRSimplesService.calcularIR(aliquotaAnualService);

        Assertions.assertNotNull(usrOut);
    }


}
