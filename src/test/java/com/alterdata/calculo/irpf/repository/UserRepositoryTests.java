package com.alterdata.calculo.irpf.repository;

import com.alterdata.calculo.irpf.models.account.User;
import com.alterdata.calculo.irpf.repositories.UserRepository;
import com.alterdata.calculo.irpf.util.UserCreator;
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
class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;



    @Test
    @DisplayName("deve salvar User no banco de dados")
    void save_deve_salvar_usuario_quando_nao_houver_errors() {
        User userToBeSaved = UserCreator.createUserToBeSaved();
        User userSaved = this.userRepository.save(userToBeSaved);

        Assertions.assertThat(userSaved).isNotNull();
        Assertions.assertThat(userSaved.getId()).isNotNull();
        Assertions.assertThat(userSaved.getNome()).isEqualTo(userSaved.getNome());
        Assertions.assertThat(userSaved.getUsername()).isEqualTo(userSaved.getUsername());
    }

    @Test
    @DisplayName("deve atualizar User no banco de dados")
    void save_deve_atualizar_usuario_quando_nao_houver_errors() {
        User userToBeSaved = UserCreator.createUserToBeSaved();
        User userSaved = this.userRepository.save(userToBeSaved);

        userSaved.setNome("Test Name");
        userSaved.setUsername("Test Username");

        User userUpdated = this.userRepository.save(userSaved);

        Assertions.assertThat(userUpdated).isNotNull();
        Assertions.assertThat(userUpdated.getId()).isNotNull();
        Assertions.assertThat(userUpdated.getNome()).isEqualTo(userSaved.getNome());
        Assertions.assertThat(userSaved.getUsername()).isEqualTo(userSaved.getUsername());
    }


    @Test
    @DisplayName("deve remover User do banco de dados")
    void delete_deve_remover_usuario_quando_nao_houver_errors() {
        User userToBeSaved = UserCreator.createUserToBeSaved();
        User userSaved = this.userRepository.save(userToBeSaved);
        this.userRepository.delete(userSaved);
        Optional<User> userOptional = this.userRepository.findById(userSaved.getId());

        Assertions.assertThat(userOptional).isEmpty();
    }


    @Test
    @DisplayName("deve encontrar User por username")
    void findByUsername_deve_retornar_usuario_quando_nao_houver_errors() {
        User userToBeSaved = UserCreator.createUserToBeSaved();
        User userSaved = this.userRepository.save(userToBeSaved);
        String userName = userSaved.getUsername();
        User userByUserName = this.userRepository.findByUsername(userName).orElseThrow();

        Assertions.assertThat(userByUserName).isNotNull();
        Assertions.assertThat(userByUserName.getUsername()).isEqualTo(userName);
    }

    @Test
    @DisplayName("deve retornar optional empty quando nao encontrar usuario")
     void findByUsername_deve_retornar_null_quando_nao_houver_username_valido() {
        Optional<User> userByUserName = this.userRepository.findByUsername("test");
        Assertions.assertThat(userByUserName).isEmpty();
    }


//    @Test
//    @DisplayName("deve retornar optional empty quando nao encontrar usuario")
//    void deveria() {
//        User user = new User();
//        user.setNome("testes");
//        user.setPassword("12312121");
//        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
//                .isThrownBy(()-> this.userRepository.save(user))
//                .withMessageContaining("username mandatory");
//    }

}
