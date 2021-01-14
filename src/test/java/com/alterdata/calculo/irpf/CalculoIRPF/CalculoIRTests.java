package com.alterdata.calculo.irpf.CalculoIRPF;

import com.alterdata.calculo.irpf.models.UserIRPFIn;
import com.alterdata.calculo.irpf.models.UserIRPFOut;
import com.alterdata.calculo.irpf.services.calcAnualIRPF.CalcAliqAnualSimplesIRPFService;
import com.alterdata.calculo.irpf.services.calcAnualIRPF.CalcIRSimplesService;
import org.junit.jupiter.api.*;

@DisplayName("Executando testes CalculoImpostoRenda")
public class CalculoIRTests {

        private TestInfo testInfo;
        private TestReporter testReporter;

        private CalcAliqAnualSimplesIRPFService aliquotaAnualService;
        private UserIRPFIn usuarioCSVIn;
        private CalcIRSimplesService calcIRSimplesService;


    @BeforeEach
        void initEach(TestReporter testReporter, TestInfo testInfo) {
            aliquotaAnualService = new CalcAliqAnualSimplesIRPFService();
            usuarioCSVIn = new UserIRPFIn();
            calcIRSimplesService = new CalcIRSimplesService(aliquotaAnualService);

            this.testReporter = testReporter;
            this.testInfo = testInfo;

            testReporter.publishEntry("Running -> " +
                    testInfo.getDisplayName() +
                    " with tag -> " + testInfo.getTags());
        }


    @Test
    public void deveRetornarUsuarioCSVOutComTodosCalculos() {
        double rendimentoAnualBruto = 40000;
        UserIRPFIn usrIn= new UserIRPFIn();
        usrIn.setNome("Victor");
        usrIn.setRendimentoAnualBruto(rendimentoAnualBruto);

        aliquotaAnualService.generateAliquota(usrIn);
        calcIRSimplesService.calcularIR(aliquotaAnualService);
        UserIRPFOut usrOut = calcIRSimplesService.generateCSVOut();

        System.out.println(usrOut);

    }


}
