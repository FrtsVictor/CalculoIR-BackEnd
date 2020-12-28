package com.alterdata.calculo.ir.CalculoIRPF.CalculoIR;

import com.alterdata.calculo.ir.CalculoIRPF.modelsCVS.UserCSVIn;
import com.alterdata.calculo.ir.CalculoIRPF.modelsCVS.UserCSVOut;
import com.alterdata.calculo.ir.CalculoIRPF.services.calc.CalcBaseAliqAnualSimplesWithoutCSVOutService;
import com.alterdata.calculo.ir.CalculoIRPF.services.calc.CalcImpostoRenda;
import org.junit.jupiter.api.*;

@DisplayName("Executando Calculo  ImpostoRenda")
public class CalculoIRTests {

        private TestInfo testInfo;
        private TestReporter testReporter;

        private CalcBaseAliqAnualSimplesWithoutCSVOutService aliquotaAnualService;
        private UserCSVIn usuarioCSVIn;
        private CalcImpostoRenda calcImpostoRenda;


    @BeforeEach
        void initEach(TestReporter testReporter, TestInfo testInfo) {
            aliquotaAnualService = new CalcBaseAliqAnualSimplesWithoutCSVOutService();
            usuarioCSVIn = new UserCSVIn();
            calcImpostoRenda = new CalcImpostoRenda(aliquotaAnualService);

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
        usrIn.setDataNascimento("17-03-1994");

        aliquotaAnualService.generateDeducaoAndAliquota2(usrIn);
        calcImpostoRenda.calcularIR(aliquotaAnualService);
        UserCSVOut usrOut = calcImpostoRenda.generateCSVOut();

        System.out.println(usrOut);

    }


}
