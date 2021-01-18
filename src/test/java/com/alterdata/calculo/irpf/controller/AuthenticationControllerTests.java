package com.alterdata.calculo.irpf.controller;

import com.alterdata.calculo.irpf.controllers.UserController;
import com.alterdata.calculo.irpf.models.account.User;
import com.alterdata.calculo.irpf.models.account.UserRequest;
import com.alterdata.calculo.irpf.services.user.UserService;
import com.alterdata.calculo.irpf.util.UserCreator;
import com.alterdata.calculo.irpf.util.UserRequestCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DisplayName("Executando testes unitarios AutenticationController")
class AuthenticationControllerTests {

    @InjectMocks
    private UserController jwtController;

    @Mock
    private UserService userServiceMock;

    @BeforeEach
    void setup() {
        PageImpl<User> userPage = new PageImpl<User>(List.of(UserCreator.createValidUser()));
        BDDMockito.when(userServiceMock.listALl(ArgumentMatchers.any()))
                .thenReturn(userPage);

        BDDMockito.when((userServiceMock.findByIdOrThrowBadRequestException(ArgumentMatchers.anyInt())))
                .thenReturn(UserCreator.createValidUser());

        BDDMockito.when((userServiceMock.findByUserNameOrThrowsBadRequestException(ArgumentMatchers.anyString())))
                .thenReturn(UserCreator.createValidUser());

        BDDMockito.when((userServiceMock.save(ArgumentMatchers.any(UserRequest.class))))
                .thenReturn(UserCreator.createValidUser());

//        BDDMockito.doNothing().when(userServiceMock).replace(ArgumentMatchers.any(UserRequest.class));
        BDDMockito.doNothing().when(userServiceMock).delete(ArgumentMatchers.anyInt());

    }

    @Test
    @DisplayName("listAll deve retornar lista paginada de users")
    void deve_retornar_lista_paginada_de_usuarios() {
        String expectedName = UserCreator.createValidUser().getNome();

        Page<User> userPage = jwtController.getALl(null).getBody();

        Assertions.assertThat(userPage).isNotNull();
        Assertions.assertThat(userPage.toList())
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(userPage.toList().get(0).getNome()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findById deve retornar user por ID quando ID valido")
    void deve_retornar_user_por_id_quando_valido() {
        Integer expectedId = UserCreator.createValidUser().getId();

        User user = jwtController.findById(1).getBody();

        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getId()).isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findByUsername deve retornar user por username quando username valido")
    void deve_retornar_user_por_username_quando_valido() {
        String expectedUsername = UserCreator.createValidUser().getUsername();

        User user = jwtController.findByIdUsername(expectedUsername).getBody();

        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getUsername()).isEqualTo(expectedUsername);
    }

    @Test
    @DisplayName("save deve retornar user para usuario valido quando sucesso")
    void deve_retornar_user_quando_salvo_com_sucesso() throws Exception {
        User user = jwtController.saveUser(UserRequestCreator.createUserRequestBody()).getBody();

        Assertions.assertThat(user).isNotNull().isEqualTo(UserCreator.createValidUser());
    }

//    @Test
//    @DisplayName("replace deve atualizar user quando sucesso")
//    void deve_atualizar_user_quando_sucesso() {
//
//        Assertions.assertThatCode(()-> jwtController.updateUser(UserRequestCreator.createUserRequestBody()))
//                .doesNotThrowAnyException();
//
//        ResponseEntity<Void> entity = jwtController.updateUser(UserRequestCreator.createUserRequestBody());
//
//        Assertions.assertThat(entity).isNotNull();
//        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
//    }

    @Test
    @DisplayName("delete deve remover user quando sucesso")
    void deve_deletar_user_quando_sucesso() {

        Assertions.assertThatCode(()-> jwtController.deleteUser(1))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = jwtController.deleteUser(1);

        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
