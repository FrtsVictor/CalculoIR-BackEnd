//package com.alterdata.calculo.irpf.integration;
//
//import com.alterdata.calculo.irpf.models.account.User;
//import com.alterdata.calculo.irpf.models.account.UserRequest;
//import com.alterdata.calculo.irpf.models.inss.UserINSSRequest;
//import com.alterdata.calculo.irpf.models.inss.UserINSSResponse;
//import com.alterdata.calculo.irpf.models.irpf.UserIRPFRequest;
//import com.alterdata.calculo.irpf.models.irpf.UserIRPFResponse;
//import com.alterdata.calculo.irpf.models.jwt.JwtRequest;
//import com.alterdata.calculo.irpf.models.jwt.JwtResponse;
//import com.alterdata.calculo.irpf.repositories.UserRepository;
//import com.alterdata.calculo.irpf.services.calculo_anual.CalcAliqAnualSimplesIRPFService;
//import com.alterdata.calculo.irpf.services.calculo_anual.CalcIRSimplesService;
//import com.alterdata.calculo.irpf.services.calculo_mensal.CalcINSSService;
//import com.alterdata.calculo.irpf.services.calculo_mensal.CalcIRRFService;
//import com.alterdata.calculo.irpf.services.calculo_mensal.CalcSalarioLiqService;
//import com.alterdata.calculo.irpf.services.user.UserService;
//import com.alterdata.calculo.irpf.util.UserRequestCreator;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.*;
//import org.springframework.test.annotation.DirtiesContext;
//
//import java.util.Collections;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureTestDatabase
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
//@DisplayName("Executando testes integração CalcController")
//class CalcControllerIT {
//
//    @Autowired
//    private TestRestTemplate testRestTemplate;
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    UserService userService;
//
//    @Test
//    @DisplayName("calcularIR deve retornar UserIRPFResponse quando token valido")
//    void deve_retornar_user_quando_salvo_com_sucesso() throws Exception {
//        UserIRPFRequest irpfRequest = new UserIRPFRequest();
//        irpfRequest.setRendimentoAnualBruto(50000);
//        irpfRequest.setNome("Teste");
//
//        User savedUser = userService.save(UserRequestCreator.createUserRequestBody());
//        JwtRequest loginRequest = new JwtRequest(savedUser.getUsername(), "123321");
//
//        ResponseEntity<JwtResponse> JWTResponse = testRestTemplate.postForEntity("/authenticate",
//                loginRequest, JwtResponse.class);
//        String token = JWTResponse.getBody().getToken();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("Authorization", "Bearer "+ token);
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//
//        HttpEntity request = new HttpEntity(headers);
//
//        ResponseEntity<UserIRPFResponse> irpfRequestReponseEntity = testRestTemplate.exchange(
//                "/api/calculate/irpf",
//                HttpMethod.POST,
//                request,
//                UserIRPFResponse.class,
//                irpfRequest
//        );
//
//        Assertions.assertThat(irpfRequestReponseEntity).isNotNull();
//        Assertions.assertThat(irpfRequestReponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        Assertions.assertThat(irpfRequestReponseEntity.getBody()).isNotNull();
//    }
//
//    @Test
//    @DisplayName("calcularINSS deve retornar UserIRPFResponse quando token valido")
//    void deve_retornar_inss_quando_salvo_com_sucesso()  {
//        UserINSSRequest inssRequest = new UserINSSRequest();
//        inssRequest.setSalarioMensalBruto(5000);
//        inssRequest.setNome("Teste");
//
//        User savedUser = userService.save(UserRequestCreator.createUserRequestBody());
//        JwtRequest loginRequest = new JwtRequest(savedUser.getUsername(), "123321");
//
//        ResponseEntity<JwtResponse> JWTResponse = testRestTemplate.postForEntity("/authenticate",
//                loginRequest, JwtResponse.class);
//        String token = JWTResponse.getBody().getToken();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("Authorization", "Bearer "+ token);
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//
//        HttpEntity request = new HttpEntity(inssRequest.toString(), headers);
//
//        ResponseEntity<UserINSSResponse> irpfRequestReponseEntity = testRestTemplate.postForEntity(
//                "/api/calculate/inss",
//                request,
//                UserINSSResponse.class
//        );
//
//        Assertions.assertThat(irpfRequestReponseEntity).isNotNull();
//        Assertions.assertThat(irpfRequestReponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        Assertions.assertThat(irpfRequestReponseEntity.getBody()).isNotNull();
//    }
//
//    @Test
//    @DisplayName("findUserByUsername deve retornar status 401 unauthorized para rota sem token")
//    void deve_retornar_401_unauthorized_quando_sem_token() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//
//        HttpEntity request = new HttpEntity(headers);
//
//        ResponseEntity<User> userResponseEntity = testRestTemplate.exchange(
//                "/users/username/{username}",
//                HttpMethod.GET,
//                request,
//                User.class,
//                "savedUser.getUsername()"
//        );
//
//        Assertions.assertThat(userResponseEntity.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
//        Assertions.assertThat(userResponseEntity.getBody().getUsername()).isNull();
//    }
//
//
//
//    @Test
//    @DisplayName("findById deve retornar user por ID quando ID valido")
//    void deve_retornar_user_por_id_quando_valido() {
//        User savedUser = userService.save(UserRequestCreator.createUserRequestBody());
//        JwtRequest loginRequest = new JwtRequest(savedUser.getUsername(), "123321");
//
//        ResponseEntity<JwtResponse> JWTResponse = testRestTemplate.postForEntity("/authenticate",
//                loginRequest, JwtResponse.class);
//        String token = JWTResponse.getBody().getToken();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("Authorization", "Bearer "+ token);
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//
//        HttpEntity request = new HttpEntity(headers);
//
//        ResponseEntity<User> userResponseEntity = testRestTemplate.exchange(
//                "/users/id/{id}",
//                HttpMethod.GET,
//                request,
//                User.class,
//                savedUser.getId()
//        );
//        Assertions.assertThat(userResponseEntity.getBody().getId()).isNotNull().isEqualTo(savedUser.getId());
//        Assertions.assertThat(userResponseEntity.getStatusCode()).isNotNull().isEqualTo(HttpStatus.OK);
//    }
//
//
//    @Test
//    @DisplayName("findById deve retornar status 401 unauthorized para rota sem token")
//    void deve_retornar_401_unauthorized_para_rota_sem_token() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//
//        HttpEntity request = new HttpEntity(headers);
//
//        ResponseEntity<User> userResponseEntity = testRestTemplate.exchange(
//                "/users/id/{id}",
//                HttpMethod.GET,
//                request,
//                User.class,
//                1
//        );
//        Assertions.assertThat(userResponseEntity.getBody().getId()).isNull();
//        Assertions.assertThat(userResponseEntity.getStatusCode()).isNotNull().isEqualTo(HttpStatus.UNAUTHORIZED);
//    }
//
//
//
//    @Test
//    @DisplayName("replace deve atualizar user quando sucesso")
//    void deve_atualizar_user_quando_sucesso() {
//        User userToBeUpdated = userService.save(UserRequestCreator.createUserRequestBody());
//        JwtRequest loginRequest = new JwtRequest(userToBeUpdated.getUsername(), "123321");
//
//        ResponseEntity<JwtResponse> JWTResponse = testRestTemplate.postForEntity("/authenticate",
//                loginRequest, JwtResponse.class);
//        String token = JWTResponse.getBody().getToken();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("Authorization", "Bearer "+ token);
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//
//        HttpEntity request = new HttpEntity(headers);
//
//        userToBeUpdated.setNome("Freitas");
//
//        testRestTemplate.put("/users/{id}",
//                request,
//                Void.class,
//                userToBeUpdated);
//
//        ResponseEntity<Void> userResponseEntity = testRestTemplate.exchange(
//                "/users",
//                HttpMethod.PUT,
//                request,
//                Void.class,
//                userToBeUpdated
//        );
//
//        Assertions.assertThat(userResponseEntity).isNotNull();
//        Assertions.assertThat(userResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
//    }
//
//    @Test
//    @DisplayName("delete deve remover user quando sucesso")
//    void deve_deletar_user_quando_sucesso() {
//        User userSaved = userService.save(UserRequestCreator.createUserRequestBody());
//        JwtRequest loginRequest = new JwtRequest(userSaved.getUsername(), "123321");
//
//        ResponseEntity<JwtResponse> JWTResponse = testRestTemplate.postForEntity("/authenticate",
//                loginRequest, JwtResponse.class);
//        String token = JWTResponse.getBody().getToken();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("Authorization", "Bearer "+ token);
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//
//        HttpEntity request = new HttpEntity(headers);
//
//        ResponseEntity<Void> userResponseEntity = testRestTemplate.exchange(
//                "/users/{id}",
//                HttpMethod.DELETE,
//                request,
//                Void.class,
//                userSaved.getId()
//        );
//        Assertions.assertThat(userResponseEntity).isNotNull();
//        Assertions.assertThat(userResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
//    }
//
//
//    @Test
//    @DisplayName("delete deve retornar status 401 unauthorized para rota sem token")
//    void deve_retornar_status401_quando_para_rota_sem_token() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//
//        HttpEntity request = new HttpEntity(headers);
//
//        ResponseEntity<Void> userResponseEntity = testRestTemplate.exchange(
//                "/users/{id}",
//                HttpMethod.DELETE,
//                request,
//                Void.class,
//                1
//        );
//        Assertions.assertThat(userResponseEntity.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
//    }
//
//
//}
