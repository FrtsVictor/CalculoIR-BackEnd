package com.alterdata.calculo.irpf.service.calculo_irpf;

import com.alterdata.calculo.irpf.models.irpf.IrpfRequest;
import com.alterdata.calculo.irpf.models.irpf.IrpfResponse;
import com.alterdata.calculo.irpf.services.calculo_anual.AliqAnualIrpfService;
import com.alterdata.calculo.irpf.services.calculo_anual.IrpfService;
import org.junit.jupiter.api.*;

@DisplayName("Executando testes unitarios CalcIRPF gerarIR()")
class IrpfServiceTests {

    private TestInfo testInfo;
    private TestReporter testReporter;

    private AliqAnualIrpfService aliquotaAnualService;
    private IrpfRequest irpfRequest;
    private IrpfService irpfService;


    @BeforeEach
    void initEach(TestReporter testReporter, TestInfo testInfo) {
        aliquotaAnualService = new AliqAnualIrpfService();
        irpfRequest = new IrpfRequest();
        irpfService = new IrpfService(aliquotaAnualService);

        this.testReporter = testReporter;
        this.testInfo = testInfo;

        testReporter.publishEntry("Running -> " +
                testInfo.getDisplayName() +
                " with tag -> " + testInfo.getTags());
    }


    @Test
    @DisplayName("Deve retornar IrpfResponse para valores de calculo validos")
    void deve_retornar_IrpfResponse_para_valores_validos() {
        double rendimentoAnualBruto = 40000;
        IrpfRequest usrIn = new IrpfRequest("Test", rendimentoAnualBruto);

        aliquotaAnualService.gerarAliquota(usrIn);
        IrpfResponse usrOut = irpfService.calcularIrpf(aliquotaAnualService);

        Assertions.assertNotNull(usrOut);
    }


}
