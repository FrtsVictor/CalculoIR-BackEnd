package com.alterdata.calculo.irpf.integration;

import com.alterdata.calculo.irpf.models.account.User;
import com.alterdata.calculo.irpf.models.account.UserRequest;
import com.alterdata.calculo.irpf.models.jwt.JwtRequest;
import com.alterdata.calculo.irpf.models.jwt.JwtResponse;
import com.alterdata.calculo.irpf.repositories.UserRepository;
import com.alterdata.calculo.irpf.services.user.UserService;
import com.alterdata.calculo.irpf.util.UserCreator;
import com.alterdata.calculo.irpf.util.UserRequestCreator;
import com.alterdata.calculo.irpf.wrapper.PageableResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Collections;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@DisplayName("Executando testes integração UserController")
class UserControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Test
    @DisplayName("save deve retornar user para usuario valido quando sucesso")
    void deve_retornar_user_quando_salvo_com_sucesso() throws Exception {
        UserRequest userRequest = UserRequestCreator.createUserRequestBody();

        ResponseEntity<User> userResponseEntity = testRestTemplate.postForEntity("/register", userRequest, User.class);

        Assertions.assertThat(userResponseEntity).isNotNull();
        Assertions.assertThat(userResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(userResponseEntity.getBody()).isNotNull();
        Assertions.assertThat(userResponseEntity.getBody().getId()).isNotNull();
    }


    @Test
    @DisplayName("findUserByUsername deve retornar usuario quando sucesso")
    void deve_retornar_usuario_por_username_quando_sucesso() {
    User savedUser = userService.save(UserRequestCreator.createUserRequestBody());
    JwtRequest loginRequest = new JwtRequest(savedUser.getUsername(), "123321");

        ResponseEntity<JwtResponse> JWTResponse = testRestTemplate.postForEntity("/authenticate",
               loginRequest, JwtResponse.class);
        String token = JWTResponse.getBody().getToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+ token);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<User> userResponseEntity = testRestTemplate.exchange(
                "/users/username/{username}",
                HttpMethod.GET,
                request,
                User.class,
                savedUser.getUsername()
        );

        Assertions.assertThat(userResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(userResponseEntity.getBody().getUsername()).isEqualTo(savedUser.getUsername());
    }


    @Test
    @DisplayName("findUserByUsername deve retornar status 401 unauthorized para rota sem token")
    void deve_retornar_401_unauthorized_quando_sem_token() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<User> userResponseEntity = testRestTemplate.exchange(
                "/users/username/{username}",
                HttpMethod.GET,
                request,
                User.class,
                "savedUser.getUsername()"
        );

        Assertions.assertThat(userResponseEntity.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
        Assertions.assertThat(userResponseEntity.getBody().getUsername()).isNull();
    }



    @Test
    @DisplayName("findById deve retornar user por ID quando ID valido")
    void deve_retornar_user_por_id_quando_valido() {
        User savedUser = userService.save(UserRequestCreator.createUserRequestBody());
        JwtRequest loginRequest = new JwtRequest(savedUser.getUsername(), "123321");

        ResponseEntity<JwtResponse> JWTResponse = testRestTemplate.postForEntity("/authenticate",
                loginRequest, JwtResponse.class);
        String token = JWTResponse.getBody().getToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+ token);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<User> userResponseEntity = testRestTemplate.exchange(
                "/users/id/{id}",
                HttpMethod.GET,
                request,
                User.class,
                savedUser.getId()
        );
        Assertions.assertThat(userResponseEntity.getBody().getId()).isNotNull().isEqualTo(savedUser.getId());
        Assertions.assertThat(userResponseEntity.getStatusCode()).isNotNull().isEqualTo(HttpStatus.OK);
    }


    @Test
    @DisplayName("findById deve retornar status 401 unauthorized para rota sem token")
    void deve_retornar_401_unauthorized_para_rota_sem_token() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<User> userResponseEntity = testRestTemplate.exchange(
                "/users/id/{id}",
                HttpMethod.GET,
                request,
                User.class,
                1
        );
        Assertions.assertThat(userResponseEntity.getBody().getId()).isNull();
        Assertions.assertThat(userResponseEntity.getStatusCode()).isNotNull().isEqualTo(HttpStatus.UNAUTHORIZED);
    }



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

    @Test
    @DisplayName("delete deve remover user quando sucesso")
    void deve_deletar_user_quando_sucesso() {
        User userSaved = userService.save(UserRequestCreator.createUserRequestBody());
        JwtRequest loginRequest = new JwtRequest(userSaved.getUsername(), "123321");

        ResponseEntity<JwtResponse> JWTResponse = testRestTemplate.postForEntity("/authenticate",
                loginRequest, JwtResponse.class);
        String token = JWTResponse.getBody().getToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+ token);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<Void> userResponseEntity = testRestTemplate.exchange(
                "/users/{id}",
                HttpMethod.DELETE,
                request,
                Void.class,
                userSaved.getId()
        );
        Assertions.assertThat(userResponseEntity).isNotNull();
        Assertions.assertThat(userResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }


    @Test
    @DisplayName("delete deve retornar status 401 unauthorized para rota sem token")
    void deve_retornar_status401_quando_para_rota_sem_token() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<Void> userResponseEntity = testRestTemplate.exchange(
                "/users/{id}",
                HttpMethod.DELETE,
                request,
                Void.class,
                1
        );
        Assertions.assertThat(userResponseEntity.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }


}
