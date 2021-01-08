package com.alterdata.calculo.ir.CalculoIRPF.CalculoIRPF;

import com.alterdata.calculo.ir.CalculoIRPF.modelsCVS.CalcUserIn;
import com.alterdata.calculo.ir.CalculoIRPF.modelsCVS.CalcUserOut;
import com.alterdata.calculo.ir.CalculoIRPF.services.calcAnualIRPF.CalcAliqAnualSimplesIRPFService;
import com.alterdata.calculo.ir.CalculoIRPF.services.calcAnualIRPF.CalcIRSimplesService;
import org.junit.jupiter.api.*;

@DisplayName("Executando Calculo  ImpostoRenda")
public class CalculoIRTests {

        private TestInfo testInfo;
        private TestReporter testReporter;

        private CalcAliqAnualSimplesIRPFService aliquotaAnualService;
        private CalcUserIn usuarioCSVIn;
        private CalcIRSimplesService calcIRSimplesService;


    @BeforeEach
        void initEach(TestReporter testReporter, TestInfo testInfo) {
            aliquotaAnualService = new CalcAliqAnualSimplesIRPFService();
            usuarioCSVIn = new CalcUserIn();
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
        CalcUserIn usrIn= new CalcUserIn();
        usrIn.setNome("Victor");
        usrIn.setCpf("160.428.122-89");
        usrIn.setRendimentoAnualBruto(rendimentoAnualBruto);
//        usrIn.setDataNascimento("17-03-1994");

        aliquotaAnualService.generateDeducaoAndAliquota2(usrIn);
        calcIRSimplesService.calcularIR(aliquotaAnualService);
        CalcUserOut usrOut = calcIRSimplesService.generateCSVOut();

        System.out.println(usrOut);

    }


}
