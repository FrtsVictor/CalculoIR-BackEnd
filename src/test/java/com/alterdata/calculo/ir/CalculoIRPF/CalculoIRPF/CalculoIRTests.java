package com.alterdata.calculo.ir.CalculoIRPF.CalculoIRPF;

import com.alterdata.calculo.ir.CalculoIRPF.modelsCVS.UserCSVIn;
import com.alterdata.calculo.ir.CalculoIRPF.modelsCVS.UserCSVOut;
import com.alterdata.calculo.ir.CalculoIRPF.services.calcIRPF.AliqAnualSimplesService;
import com.alterdata.calculo.ir.CalculoIRPF.services.calcIRPF.CalcIRSimplesServices;
import org.junit.jupiter.api.*;

@DisplayName("Executando Calculo  ImpostoRenda")
public class CalculoIRTests {

        private TestInfo testInfo;
        private TestReporter testReporter;

        private AliqAnualSimplesService aliquotaAnualService;
        private UserCSVIn usuarioCSVIn;
        private CalcIRSimplesServices calcIRSimplesService;


    @BeforeEach
        void initEach(TestReporter testReporter, TestInfo testInfo) {
            aliquotaAnualService = new AliqAnualSimplesService();
            usuarioCSVIn = new UserCSVIn();
            calcIRSimplesService = new CalcIRSimplesServices(aliquotaAnualService);

            this.testReporter = testReporter;
            this.testInfo = testInfo;

            testReporter.publishEntry("Running -> " +
                    testInfo.getDisplayName() +
                    " with tag -> " + testInfo.getTags());
        }


    @Test
    public void deveRetornarUsuarioCSVOutComTodosCalculos() {
        double rendimentoAnualBruto = 40000;
        UserCSVIn usrIn= new UserCSVIn();
        usrIn.setNome("Victor");
        usrIn.setCpf("160.428.122-89");
        usrIn.setRendimentoAnualBruto(rendimentoAnualBruto);
//        usrIn.setDataNascimento("17-03-1994");

        aliquotaAnualService.generateDeducaoAndAliquota2(usrIn);
        calcIRSimplesService.calcularIR(aliquotaAnualService);
        UserCSVOut usrOut = calcIRSimplesService.generateCSVOut();

        System.out.println(usrOut);

    }


}
