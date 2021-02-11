package com.alterdata.calculo.irpf.service.user;

import com.alterdata.calculo.irpf.exceptions.BadRequestException;
import com.alterdata.calculo.irpf.models.account.Usuario;
import com.alterdata.calculo.irpf.repositories.UserRepository;
import com.alterdata.calculo.irpf.services.user.UserService;
import com.alterdata.calculo.irpf.util.CriarUser;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DisplayName("Executando testes unitarios UserService")
class UsuarioServiceTests {

    @InjectMocks
    private UserService userServiceMock;

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    PasswordEncoder bcrypt;

    @BeforeEach
    void setup() {
        PageImpl<Usuario> userPage = new PageImpl<>(List.of(CriarUser.usuarioSalvoValido()));
        BDDMockito.when(userRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(userPage);

        BDDMockito.when((userRepositoryMock.save(ArgumentMatchers.any(Usuario.class))))
                .thenReturn(CriarUser.usuarioSalvoValido());

        BDDMockito.when((userRepositoryMock.findById(ArgumentMatchers.anyInt())))
                .thenReturn(Optional.of(CriarUser.usuarioSalvoValido()));

        BDDMockito.when((userRepositoryMock.findByUsuario(ArgumentMatchers.anyString())))
                .thenReturn(Optional.of(CriarUser.usuarioSalvoValido()));

        BDDMockito.doNothing().when(userRepositoryMock).delete(ArgumentMatchers.any(Usuario.class));

    }

    @Test
    @DisplayName("listAll deve retornar lista paginada de users")
    void deve_retornar_lista_paginada_de_usuarios() {
        String expectedName = CriarUser.usuarioSalvoValido().getNome();

        Page<Usuario> userPage = userServiceMock.listarTodos(PageRequest.of(1, 1));

        Assertions.assertThat(userPage).isNotNull();
        Assertions.assertThat(userPage.toList())
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(userPage.toList().get(0).getNome()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("buscarPorIdOuLancarBadRequestException deve retornar user por ID")
    void deve_retornar_user_por_id_quando_valido() {
        Integer expectedId = CriarUser.usuarioSalvoValido().getId();

        Usuario user = userServiceMock.buscarPorIdOuLancarBadRequestException(1);

        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getId()).isEqualTo(expectedId);
    }

    @Test
    @DisplayName("buscarPorUsernameOuLancarBadRequestException deve retornar user por username")
    void deve_retornar_user_por_username_quando_valido() {
        String expectedUsername = CriarUser.usuarioSalvoValido().getUsuario();

        Usuario user = userServiceMock.buscarPorUsernameOuLancarBadRequestException(expectedUsername);

        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getUsuario()).isEqualTo(expectedUsername);
    }

    @Test
    @DisplayName("delete deve remover user quando sucesso")
    void deve_deletar_user_quando_sucesso() {
        Assertions.assertThatCode(() -> userServiceMock.remover(1))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("buscarPorIdOuLancarBadRequestException deve lancar exception quando user nao for encontrado ")
    void deve_retornar_BadRequestException_para_usuario_nao_encontrado_por_id() {
        BDDMockito.when((userRepositoryMock.findById(ArgumentMatchers.anyInt())))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> userServiceMock.buscarPorIdOuLancarBadRequestException(1));
    }


    @Test
    @DisplayName("buscarPorUsernameOuLancarBadRequestException deve lancar exceptopn quando user nao for encontrado ")
    void deve_retornar_BadRequestException_para_usuario_nao_encontrado_por_username() {
        BDDMockito.when((userRepositoryMock.findByUsuario(ArgumentMatchers.anyString())))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> userServiceMock.buscarPorUsernameOuLancarBadRequestException("asd"));
    }


}
