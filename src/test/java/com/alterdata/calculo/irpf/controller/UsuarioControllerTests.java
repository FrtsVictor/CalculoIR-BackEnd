package com.alterdata.calculo.irpf.controller;

import com.alterdata.calculo.irpf.controllers.UsuarioController;
import com.alterdata.calculo.irpf.models.account.Usuario;
import com.alterdata.calculo.irpf.models.account.UsuarioPutRequest;
import com.alterdata.calculo.irpf.models.account.UserRequest;
import com.alterdata.calculo.irpf.services.user.UserService;
import com.alterdata.calculo.irpf.util.CriarUser;
import com.alterdata.calculo.irpf.util.CriarUserPutRequest;
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
class UsuarioControllerTests {

    @InjectMocks
    private UsuarioController usuarioController;

    @Mock
    private UserService userServiceMock;

    @BeforeEach
    void setup() {
        PageImpl<Usuario> userPage = new PageImpl<>(List.of(CriarUser.usuarioSalvoValido()));

        BDDMockito.when(userServiceMock.listarTodos(ArgumentMatchers.any()))
                .thenReturn(userPage);

        BDDMockito.when((userServiceMock.buscarPorIdOuLancarBadRequestException(ArgumentMatchers.anyInt())))
                .thenReturn(CriarUser.usuarioSalvoValido());

        BDDMockito.when((userServiceMock.buscarPorUsernameOuLancarBadRequestException(ArgumentMatchers.anyString())))
                .thenReturn(CriarUser.usuarioSalvoValido());

        BDDMockito.when((userServiceMock.salvar(ArgumentMatchers.any(UserRequest.class))))
                .thenReturn(CriarUser.usuarioSalvoValido());

        BDDMockito.doNothing().when(userServiceMock).atualizar(ArgumentMatchers.any(UsuarioPutRequest.class));

        BDDMockito.doNothing().when(userServiceMock).remover(ArgumentMatchers.anyInt());

    }

    @Test
    @DisplayName("listAll deve retornar lista paginada de users")
    void listAll_deve_retornar_lista_paginada_de_usuarios() {
        String expectedName = CriarUser.usuarioSalvoValido().getNome();

        Page<Usuario> userPage = usuarioController.listarTodos(null).getBody();

        Assertions.assertThat(userPage).isNotNull();
        Assertions.assertThat(userPage.toList())
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(userPage.toList().get(0).getNome()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findById deve retornar user por ID quando ID valido")
    void findById_deve_retornar_user_por_id_quando_valido() {
        Integer expectedId = CriarUser.usuarioSalvoValido().getId();

        Usuario user = usuarioController.encontrarId(1).getBody();

        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getId()).isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findByUsername deve retornar user por username quando username valido")
    void findByUsername_deve_retornar_user_por_username_quando_valido() {
        String expectedUsername = CriarUser.usuarioSalvoValido().getUsuario();

        Usuario user = usuarioController.encontrarPorUsuario(expectedUsername).getBody();

        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getUsuario()).isEqualTo(expectedUsername);
    }

    @Test
    @DisplayName("save deve retornar user para usuario valido quando sucesso")
    void save_deve_retornar_user_quando_salvo_com_sucesso() throws Exception {
        Usuario user = usuarioController.salvarUsuario(UserRequestCreator.createUserRequestBody()).getBody();

        Assertions.assertThat(user).isNotNull().isEqualTo(CriarUser.usuarioSalvoValido());
    }

    @Test
    @DisplayName("replace deve atualizar user quando sucesso")
    void deve_atualizar_user_quando_sucesso() {
        Assertions.assertThatCode(() -> usuarioController.atualizarUsuario(CriarUserPutRequest.createUserPutRequest()))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = usuarioController.atualizarUsuario(CriarUserPutRequest.createUserPutRequest());

        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("delete deve remover user quando sucesso")
    void delete_deve_remover_user_quando_sucesso() {

        Assertions.assertThatCode(() -> usuarioController.removerUsuario(1))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = usuarioController.removerUsuario(1);

        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
