package com.alterdata.calculo.irpf.integration;

import com.alterdata.calculo.irpf.models.account.Usuario;
import com.alterdata.calculo.irpf.models.account.UserRequest;
import com.alterdata.calculo.irpf.models.jwt.JwtRequest;
import com.alterdata.calculo.irpf.models.jwt.JwtResponse;
import com.alterdata.calculo.irpf.services.user.UserService;
import com.alterdata.calculo.irpf.util.CriarUser;
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
@DisplayName("Executando testes integração UsuarioController")
class UsuarioControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @Autowired
    UserService userService;

    HttpHeaders httpHeaders;

    @BeforeEach
    void gerarToken() {
        Usuario savedUser = userService.salvar(UserRequestCreator.createUserRequestBody());
        JwtRequest loginRequest = new JwtRequest(savedUser.getUsuario(), "123321");

        ResponseEntity<JwtResponse> JWTResponse = testRestTemplate.postForEntity("/v1/authenticate",
                loginRequest, JwtResponse.class);
        String token = JWTResponse.getBody().getToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        this.httpHeaders = new HttpHeaders(headers);
    }

    @Test
    @DisplayName("save deve retornar usuario salvo quando sucesso")
    void save_deve_retornar_user_quando_salvo_com_sucesso() throws Exception {
        UserRequest userToBeSaved = new UserRequest();
        userToBeSaved.setNome("Test2");
        userToBeSaved.setUsuario("testUsuario2");
        userToBeSaved.setSenha("testes");

        ResponseEntity<Usuario> userResponseEntity = testRestTemplate.postForEntity("/v1/register", userToBeSaved, Usuario.class);

        Assertions.assertThat(userResponseEntity).isNotNull();
        Assertions.assertThat(userResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(userResponseEntity.getBody()).isNotNull();
        Assertions.assertThat(userResponseEntity.getBody().getId()).isNotNull();
    }


    @Test
    @DisplayName("findUserByUsername deve retornar usuario por username quando sucesso")
    void findUserByUsername_deve_retornar_usuario_por_username_quando_sucesso() {
        Usuario usuarioSalvo = CriarUser.usuarioSalvoValido();

        HttpEntity request = new HttpEntity(this.httpHeaders);

        ResponseEntity<Usuario> userResponseEntity = testRestTemplate.exchange(
                "/v1/users/username/{usuario}",
                HttpMethod.GET,
                request,
                Usuario.class,
                usuarioSalvo.getUsuario()
        );

        Assertions.assertThat(userResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(userResponseEntity.getBody().getUsuario()).isEqualTo(usuarioSalvo.getUsuario());
    }


    @Test
    @DisplayName("findUserByUsername deve retornar status 401 unauthorized para token invalido")
    void findUserByUsername_deve_retornar_401_unauthorized_quando_sem_token() {
        HttpEntity request = new HttpEntity(new HttpHeaders());

        ResponseEntity<Usuario> userResponseEntity = testRestTemplate.exchange(
                "/v1/users/username/{usuario}",
                HttpMethod.GET,
                request,
                Usuario.class,
                "savedUser.getUsername()"
        );

        Assertions.assertThat(userResponseEntity.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
        Assertions.assertThat(userResponseEntity.getBody().getUsuario()).isNull();
    }

    @Test
    @DisplayName("findById deve retornar user por ID quando ID valido")
    void findById_deve_retornar_user_por_id_quando_valido() {
        Usuario savedBeforeEach = CriarUser.usuarioSalvoValido();

        HttpEntity request = new HttpEntity(this.httpHeaders);

        ResponseEntity<Usuario> userResponseEntity = testRestTemplate.exchange(
                "/v1/users/id/{id}",
                HttpMethod.GET,
                request,
                Usuario.class,
                savedBeforeEach.getId()
        );
        Assertions.assertThat(userResponseEntity.getBody().getId()).isNotNull().isEqualTo(savedBeforeEach.getId());
        Assertions.assertThat(userResponseEntity.getStatusCode()).isNotNull().isEqualTo(HttpStatus.OK);
    }


    @Test
    @DisplayName("findById deve retornar status 401 unauthorized para token invalido")
    void findById_deve_retornar_401_unauthorized_para_rota_sem_token() {
        HttpEntity request = new HttpEntity(new HttpHeaders());

        ResponseEntity<Usuario> userResponseEntity = testRestTemplate.exchange(
                "/v1/users/id/{id}",
                HttpMethod.GET,
                request,
                Usuario.class,
                1
        );
        Assertions.assertThat(userResponseEntity.getBody().getId()).isNull();
        Assertions.assertThat(userResponseEntity.getStatusCode()).isNotNull().isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    @DisplayName("replace deve atualizar user quando sucesso")
    void replace_deve_atualizar_user_quando_sucesso() {
        UserRequest validUser = new UserRequest("testUsuario3", "123456", "Test3");
        Usuario userToBeUpdated = userService.salvar(validUser);

        HttpEntity request = new HttpEntity(userToBeUpdated, this.httpHeaders);

        userToBeUpdated.setNome("Freitas");

        ResponseEntity<Void> userResponseEntity = testRestTemplate.exchange(
                "/v1/users",
                HttpMethod.PUT,
                request,
                Void.class
        );

        Assertions.assertThat(userResponseEntity).isNotNull();
        Assertions.assertThat(userResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("replace deve lancar status 401 Unauthorized para token invalido")
    void replace_deve_lancar_status_401_unauthorized_para_token_invalido() {
        UserRequest validUser = new UserRequest("testUsuario3", "123456", "Test3");
        Usuario userToBeUpdated = userService.salvar(validUser);

        HttpEntity request = new HttpEntity(userToBeUpdated, this.httpHeaders);

        userToBeUpdated.setNome("Freitas");

        ResponseEntity<Void> userResponseEntity = testRestTemplate.exchange(
                "/v1/users",
                HttpMethod.PUT,
                request,
                Void.class
        );

        Assertions.assertThat(userResponseEntity).isNotNull();
        Assertions.assertThat(userResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }


    @Test
    @DisplayName("delete deve remover user quando sucesso")
    void delete_deve_deletar_user_quando_sucesso() {
        UserRequest userToBeSaved = new UserRequest();
        userToBeSaved.setNome("Test2");
        userToBeSaved.setUsuario("testUsuario2");
        userToBeSaved.setSenha("testes");

        Usuario userSaved = userService.salvar(userToBeSaved);

        HttpEntity request = new HttpEntity(httpHeaders);

        ResponseEntity<Void> userResponseEntity = testRestTemplate.exchange(
                "/v1/users/{id}",
                HttpMethod.DELETE,
                request,
                Void.class,
                userSaved.getId()
        );
        Assertions.assertThat(userResponseEntity).isNotNull();
        Assertions.assertThat(userResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("delete deve retornar status 401 unauthorized para token invalido")
    void deve_retornar_status401_quando_para_rota_sem_token() {
        HttpEntity request = new HttpEntity(new HttpHeaders());

        ResponseEntity<Void> userResponseEntity = testRestTemplate.exchange(
                "/v1/users/{id}",
                HttpMethod.DELETE,
                request,
                Void.class,
                1
        );
        Assertions.assertThat(userResponseEntity.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

}
