package com.alterdata.calculo.irpf.integration;

import com.alterdata.calculo.irpf.models.account.User;
import com.alterdata.calculo.irpf.models.inss.InssRequest;
import com.alterdata.calculo.irpf.models.irpf.IrpfRequest;
import com.alterdata.calculo.irpf.models.irpf.IrpfResponse;
import com.alterdata.calculo.irpf.models.irrf.IrrfRequest;
import com.alterdata.calculo.irpf.models.jwt.JwtRequest;
import com.alterdata.calculo.irpf.models.jwt.JwtResponse;
import com.alterdata.calculo.irpf.models.salario_liq.SalarioLiqRequest;
import com.alterdata.calculo.irpf.services.user.UserService;
import com.alterdata.calculo.irpf.util.UserRequestCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Collections;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@DisplayName("Executando testes integração CalculateControllerTests")
class CalculateControllerTestsIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    HttpHeaders httpHeaders;

    @BeforeEach
    void generateToken() {
        User savedUser = userService.save(UserRequestCreator.createUserRequestBody());
        JwtRequest loginRequest = new JwtRequest(savedUser.getUsername(), "123321");

        ResponseEntity<JwtResponse> JWTResponse = testRestTemplate.postForEntity("/v1/authenticate",
                loginRequest, JwtResponse.class);
        String token = JWTResponse.getBody().getToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        this.httpHeaders = new HttpHeaders(headers);
    }

    @Autowired
    UserService userService;

    @Test
    @DisplayName("calcularIRPF deve retornar IrpfResponse status 200 OK quando token valido")
    void calcularIRPF_deve_retornar_user_quando_salvo_com_sucesso() throws Exception {
        IrpfRequest irpfRequest = new IrpfRequest();
        irpfRequest.setRendimentoAnualBruto(50000);
        irpfRequest.setNome("Teste");

        HttpEntity request = new HttpEntity(irpfRequest, this.httpHeaders);

        ResponseEntity<IrpfResponse> irpfRequestReponseEntity = testRestTemplate.postForEntity(
                "/v1/calculate/irpf",
                request,
                IrpfResponse.class
        );
        Assertions.assertThat(irpfRequestReponseEntity).isNotNull();
        Assertions.assertThat(irpfRequestReponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(irpfRequestReponseEntity.getBody()).isNotNull();
    }

    @Test
    @DisplayName("calcularIRPF deve retornar status 401 Unauthorized para token invalido")
    void calcularIRPF_deve_retornar_status_401_unauthorized_para_rota_sem_token() throws Exception {
        IrpfRequest irpfRequest = new IrpfRequest();
        irpfRequest.setRendimentoAnualBruto(50000);
        irpfRequest.setNome("Teste");

        HttpEntity request = new HttpEntity(irpfRequest, new HttpHeaders());

        ResponseEntity<IrpfResponse> irpfRequestReponseEntity = testRestTemplate.postForEntity(
                "/v1/calculate/irpf",
                request,
                IrpfResponse.class
        );
        Assertions.assertThat(irpfRequestReponseEntity).isNotNull();
        Assertions.assertThat(irpfRequestReponseEntity.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }


    @Test
    @DisplayName("calcularINSS deve retornar InssResponse status 200 OK quando token valido")
    void calcularINSS_deve_retornar_inss_quando_salvo_com_sucesso() {
        InssRequest inssRequest = new InssRequest();
        inssRequest.setNome("Teste");
        inssRequest.setSalarioMensalBruto(5000);

        HttpEntity request = new HttpEntity(inssRequest, this.httpHeaders);

        ResponseEntity<IrpfResponse> irpfRequestReponseEntity = testRestTemplate.postForEntity(
                "/v1/calculate/inss",
                request,
                IrpfResponse.class
        );

        Assertions.assertThat(irpfRequestReponseEntity).isNotNull();
        Assertions.assertThat(irpfRequestReponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(irpfRequestReponseEntity.getBody()).isNotNull();
    }

    @Test
    @DisplayName("calcularINSS deve retornar status 401 Unauthorized para token invalido")
    void calcularINSS_deve_retornar_status_401_unautorized_para_rota_sem_token() {
        InssRequest inssRequest = new InssRequest();
        inssRequest.setNome("Teste");
        inssRequest.setSalarioMensalBruto(5000);

        HttpEntity request = new HttpEntity(inssRequest, new HttpHeaders());

        ResponseEntity<IrpfResponse> irpfRequestReponseEntity = testRestTemplate.postForEntity(
                "/v1/calculate/inss",
                request,
                IrpfResponse.class
        );
        Assertions.assertThat(irpfRequestReponseEntity).isNotNull();
        Assertions.assertThat(irpfRequestReponseEntity.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }


    @Test
    @DisplayName("calcularIRRF deve retornar IrrfResponse status 200 OK quando token valido")
    void calcularIRRF_deve_retornar_UserIRRFResponse_quando_token_valido() {
        IrrfRequest irrfRequest = new IrrfRequest();
        irrfRequest.setNome("Teste");
        irrfRequest.setSalarioMensalBruto(5000);

        HttpEntity request = new HttpEntity(irrfRequest, this.httpHeaders);

        ResponseEntity<IrpfResponse> irpfRequestReponseEntity = testRestTemplate.postForEntity(
                "/v1/calculate/irrf",
                request,
                IrpfResponse.class
        );
        Assertions.assertThat(irpfRequestReponseEntity).isNotNull();
        Assertions.assertThat(irpfRequestReponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(irpfRequestReponseEntity.getBody()).isNotNull();
    }


    @Test
    @DisplayName("calcularIRRF deve retornar status 401 Unauthorized para token invalido")
    void calcularIRRF_deve_retornar_status_401_Unauthorized_para_rota_sem_token() {
        IrrfRequest irrfRequest = new IrrfRequest();
        irrfRequest.setNome("Teste");
        irrfRequest.setSalarioMensalBruto(5000);

        HttpEntity request = new HttpEntity(irrfRequest, new HttpHeaders());

        ResponseEntity<IrpfResponse> irpfRequestReponseEntity = testRestTemplate.postForEntity(
                "/v1/calculate/irrf",
                request,
                IrpfResponse.class
        );
        Assertions.assertThat(irpfRequestReponseEntity).isNotNull();
        Assertions.assertThat(irpfRequestReponseEntity.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }


    @Test
    @DisplayName("calcularSalLiq deve retornar UserSalarioLiq status 200 OK quando token valido")
    void calcularSalLiq_deve_retornar_UserSalarioLiqResponse_quando_token_valido() {
        SalarioLiqRequest salLiq = new SalarioLiqRequest();
        salLiq.setNome("Teste");
        salLiq.setSalarioMensalBruto(5000);

        HttpEntity request = new HttpEntity(salLiq, this.httpHeaders);

        ResponseEntity<IrpfResponse> irpfRequestReponseEntity = testRestTemplate.postForEntity(
                "/v1/calculate/salario-liq",
                request,
                IrpfResponse.class
        );
        Assertions.assertThat(irpfRequestReponseEntity).isNotNull();
        Assertions.assertThat(irpfRequestReponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(irpfRequestReponseEntity.getBody()).isNotNull();
    }


    @Test
    @DisplayName("calcularSalario-liq deve retornar status 401 Unauthorized para token invalido")
    void calcularSalarioLiq_deve_retornar_status_401_Unauthorized_para_rota_sem_token() {
        IrrfRequest irrfRequest = new IrrfRequest();
        irrfRequest.setNome("Teste");
        irrfRequest.setSalarioMensalBruto(5000);

        HttpEntity request = new HttpEntity(irrfRequest, new HttpHeaders());

        ResponseEntity<IrpfResponse> irpfRequestReponseEntity = testRestTemplate.postForEntity(
                "/v1/calculate/salario-liq",
                request,
                IrpfResponse.class
        );
        Assertions.assertThat(irpfRequestReponseEntity).isNotNull();
        Assertions.assertThat(irpfRequestReponseEntity.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

}
