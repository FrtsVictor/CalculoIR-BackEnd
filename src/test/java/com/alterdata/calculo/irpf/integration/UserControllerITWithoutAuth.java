//package com.alterdata.calculo.irpf.integration;
//
//import com.alterdata.calculo.irpf.models.account.User;
//import com.alterdata.calculo.irpf.models.account.UserRequest;
//import com.alterdata.calculo.irpf.repositories.UserRepository;
//import com.alterdata.calculo.irpf.util.UserCreator;
//import com.alterdata.calculo.irpf.util.UserRequestCreator;
//import com.alterdata.calculo.irpf.wrapper.PageableResponse;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.annotation.DirtiesContext;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureTestDatabase
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
//public class UserControllerIT {
//
//    @Autowired
//    private TestRestTemplate testRestTemplate;
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Test
//    @DisplayName("listALl deve retornar lista paginada de users")
//    void deve_retornar_lista_paginada_de_usuarios() {
//        User userSaved = userRepository.save(UserCreator.createUserToBeSaved());
//
//        String expectedName = userSaved.getNome();
//
//        PageableResponse<User> userPage = testRestTemplate.exchange("/users", HttpMethod.GET, null,
//                new ParameterizedTypeReference<PageableResponse<User>>() {
//                }).getBody();
//
//        Assertions.assertThat(userPage).isNotNull();
//        Assertions.assertThat(userPage.toList())
//                .isNotEmpty()
//                .hasSize(1);
//        Assertions.assertThat(userPage.toList().get(0).getNome()).isEqualTo(expectedName);
//    }
//
//    @Test
//    @DisplayName("findById deve retornar user por ID quando ID valido")
//    void deve_retornar_user_por_id_quando_valido() {
//        User userSaved = userRepository.save(UserCreator.createUserToBeSaved());
//        Integer expectedId = userSaved.getId();
//
//        User user = testRestTemplate.getForObject("/users/id/{id}", User.class, expectedId);
//
//        Assertions.assertThat(user).isNotNull();
//        Assertions.assertThat(user.getId()).isNotNull().isEqualTo(expectedId);
//    }
//
//    @Test
//    @DisplayName("findByUsername deve retornar user por username quando username valido")
//    void deve_retornar_user_por_username_quando_valido() {
//        User userSaved = userRepository.save(UserCreator.createUserToBeSaved());
//        String expectedUsername = userSaved.getUsername();
//
//        User user = testRestTemplate.getForObject("/users/username/{username}", User.class, expectedUsername);
//
//        Assertions.assertThat(user).isNotNull();
//        Assertions.assertThat(user.getUsername()).isEqualTo(expectedUsername);
//    }
//
//    @Test
//    @DisplayName("save deve retornar user para usuario valido quando sucesso")
//    void deve_retornar_user_quando_salvo_com_sucesso() throws Exception {
//        UserRequest userRequest = UserRequestCreator.createUserRequestBody();
//
//        ResponseEntity<User> userResponseEntity = testRestTemplate.postForEntity("/register", userRequest, User.class);
//
//        Assertions.assertThat(userResponseEntity).isNotNull();
//        Assertions.assertThat(userResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//        Assertions.assertThat(userResponseEntity.getBody()).isNotNull();
//        Assertions.assertThat(userResponseEntity.getBody().getId()).isNotNull();
//    }
//
//    @Test
//    @DisplayName("replace deve atualizar user quando sucesso")
//    void deve_atualizar_user_quando_sucesso() {
//        User userSaved = userRepository.save(UserCreator.createUserToBeSaved());
//
//        UserRequest userTOUpdate = new UserRequest();
//        userTOUpdate.setUsername(userSaved.getUsername());
//        userTOUpdate.setNome("Updated");
////        userTOUpdate.setPassword("new pass");
//
//        ResponseEntity<Void> userResponseEntity = testRestTemplate.exchange("/users",
//                HttpMethod.PUT, new HttpEntity<>(userTOUpdate), Void.class);
//
//        Assertions.assertThat(userResponseEntity).isNotNull();
//
//        Assertions.assertThat(userResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
//    }
//
//    @Test
//    @DisplayName("delete deve remover user quando sucesso")
//    void deve_deletar_user_quando_sucesso() {
//        User userSaved = userRepository.save(UserCreator.createUserToBeSaved());
//
//        ResponseEntity<Void> userResponseENtity = testRestTemplate.exchange("/users/{id}",
//                HttpMethod.DELETE,null, Void.class, userSaved.getId());
//
//        Assertions.assertThat(userResponseENtity).isNotNull();
//
//        Assertions.assertThat(userResponseENtity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
//    }
//
//}
