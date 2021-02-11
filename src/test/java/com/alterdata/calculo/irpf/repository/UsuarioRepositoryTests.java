package com.alterdata.calculo.irpf.repository;

import com.alterdata.calculo.irpf.models.account.Usuario;
import com.alterdata.calculo.irpf.repositories.UserRepository;
import com.alterdata.calculo.irpf.util.CriarUser;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@DisplayName("Executando testes unitarios persistencia UserRepository")
@Log4j2
class UsuarioRepositoryTests {

    @Autowired
    private UserRepository userRepository;


    @Test
    @DisplayName("deve salvar Usuario no banco de dados")
    void save_deve_salvar_usuario_quando_nao_houver_errors() {
        Usuario userToBeSaved = CriarUser.usuarioParaSalvar();
        Usuario userSaved = this.userRepository.save(userToBeSaved);

        Assertions.assertThat(userSaved).isNotNull();
        Assertions.assertThat(userSaved.getId()).isNotNull();
        Assertions.assertThat(userSaved.getNome()).isEqualTo(userSaved.getNome());
        Assertions.assertThat(userSaved.getUsuario()).isEqualTo(userSaved.getUsuario());
    }

    @Test
    @DisplayName("deve atualizar Usuario no banco de dados")
    void save_deve_atualizar_usuario_quando_nao_houver_errors() {
        Usuario userToBeSaved = CriarUser.usuarioParaSalvar();
        Usuario userSaved = this.userRepository.save(userToBeSaved);

        userSaved.setNome("Test Name");
        userSaved.setUsuario("Test Username");

        Usuario userUpdated = this.userRepository.save(userSaved);

        Assertions.assertThat(userUpdated).isNotNull();
        Assertions.assertThat(userUpdated.getId()).isNotNull();
        Assertions.assertThat(userUpdated.getNome()).isEqualTo(userSaved.getNome());
        Assertions.assertThat(userSaved.getUsuario()).isEqualTo(userSaved.getUsuario());
    }


    @Test
    @DisplayName("deve remover Usuario do banco de dados")
    void delete_deve_remover_usuario_quando_nao_houver_errors() {
        Usuario userToBeSaved = CriarUser.usuarioParaSalvar();
        Usuario userSaved = this.userRepository.save(userToBeSaved);
        this.userRepository.delete(userSaved);
        Optional<Usuario> userOptional = this.userRepository.findById(userSaved.getId());

        Assertions.assertThat(userOptional).isEmpty();
    }


    @Test
    @DisplayName("deve encontrar Usuario por username")
    void findByUsuario_deve_retornar_usuario_quando_nao_houver_errors() {
        Usuario userToBeSaved = CriarUser.usuarioParaSalvar();
        Usuario userSaved = this.userRepository.save(userToBeSaved);
        String userName = userSaved.getUsuario();
        Usuario userByUserName = this.userRepository.findByUsuario(userName).orElseThrow();

        Assertions.assertThat(userByUserName).isNotNull();
        Assertions.assertThat(userByUserName.getUsuario()).isEqualTo(userName);
    }

    @Test
    @DisplayName("findByUsuario deve retornar optional empty quando nao encontrar usuario")
    void findByUsuario_deve_retornar_null_quando_nao_houver_username_valido() {
        Optional<Usuario> userByUserName = this.userRepository.findByUsuario("test");
        Assertions.assertThat(userByUserName).isEmpty();
    }


//    @Test
//    @DisplayName("deve retornar optional empty quando nao encontrar usuario")
//    void deveria() {
//        Usuario user = new Usuario();
//        user.setNome("testes");
//        user.setPassword("12312121");
//        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
//                .isThrownBy(()-> this.userRepository.save(user))
//                .withMessageContaining("usuario mandatory");
//    }

}
