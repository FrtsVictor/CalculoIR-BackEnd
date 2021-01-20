package com.alterdata.calculo.irpf.service.user;

import com.alterdata.calculo.irpf.exceptions.BadRequestException;
import com.alterdata.calculo.irpf.models.account.User;
import com.alterdata.calculo.irpf.util.UserCreator;
import com.alterdata.calculo.irpf.repositories.UserRepository;
import com.alterdata.calculo.irpf.services.user.UserService;
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
class UserServiceTests {

    @InjectMocks
    private UserService userServiceMock;

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    PasswordEncoder bcrypt;

    @BeforeEach
    void setup() {
        PageImpl<User> userPage = new PageImpl<>(List.of(UserCreator.createValidUser()));
        BDDMockito.when(userRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(userPage);

        BDDMockito.when((userRepositoryMock.save(ArgumentMatchers.any(User.class))))
                .thenReturn(UserCreator.createValidUser());

        BDDMockito.when((userRepositoryMock.findById(ArgumentMatchers.anyInt())))
                .thenReturn(Optional.of(UserCreator.createValidUser()));

        BDDMockito.when((userRepositoryMock.findByUsername(ArgumentMatchers.anyString())))
                .thenReturn(Optional.of(UserCreator.createValidUser()));

        BDDMockito.doNothing().when(userRepositoryMock).delete(ArgumentMatchers.any(User.class));

    }

    @Test
    @DisplayName("listAll deve retornar lista paginada de users")
    void deve_retornar_lista_paginada_de_usuarios() {
        String expectedName = UserCreator.createValidUser().getNome();

        Page<User> userPage = userServiceMock.listALl(PageRequest.of(1, 1));

        Assertions.assertThat(userPage).isNotNull();
        Assertions.assertThat(userPage.toList())
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(userPage.toList().get(0).getNome()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findByIdOrThrowBadRequestException deve retornar user por ID")
    void deve_retornar_user_por_id_quando_valido() {
        Integer expectedId = UserCreator.createValidUser().getId();

        User user = userServiceMock.findByIdOrThrowBadRequestException(1);

        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getId()).isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findByUserNameOrThrowsBadRequestException deve retornar user por username")
    void deve_retornar_user_por_username_quando_valido() {
        String expectedUsername = UserCreator.createValidUser().getUsername();

        User user = userServiceMock.findByUserNameOrThrowsBadRequestException(expectedUsername);

        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getUsername()).isEqualTo(expectedUsername);
    }

//    @Test
//    @DisplayName("save deve retornar user quando sucesso")
//    void deve_retornar_user_quando_salvo_com_sucesso() throws Exception {
//        User user = UserCreator.createValidUser();
//        user.setUsername("Testzin");
//        BDDMockito.when((userRepositoryMock.save(ArgumentMatchers.any(User.class))))
//                .thenReturn(user);
//
//        UserRequest rest = UserRequestCreator.createUserRequestBody();
//        rest.setUsername("Testzin");
//        User users = userServiceMock.save(rest);
//
//        Assertions.assertThat(user).isNotNull().isEqualTo(UserCreator.createValidUser());
//    }

    @Test
    @DisplayName("delete deve remover user quando sucesso")
    void deve_deletar_user_quando_sucesso() {
        Assertions.assertThatCode(() -> userServiceMock.delete(1))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("findByIdOrThrowsBadRequestException deve lancar exception quando user nao for encontrado ")
    void deve_retornar_BadRequestException_para_usuario_nao_encontrado_por_id() {
        BDDMockito.when((userRepositoryMock.findById(ArgumentMatchers.anyInt())))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> userServiceMock.findByIdOrThrowBadRequestException(1));
    }


    @Test
    @DisplayName("findByUsernameOrThrowsBadRequestException deve lancar exceptopn quando user nao for encontrado ")
    void deve_retornar_BadRequestException_para_usuario_nao_encontrado_por_username() {
        BDDMockito.when((userRepositoryMock.findByUsername(ArgumentMatchers.anyString())))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> userServiceMock.findByUserNameOrThrowsBadRequestException("asd"));
    }


}
