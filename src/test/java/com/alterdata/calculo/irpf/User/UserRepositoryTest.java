package com.alterdata.calculo.irpf.User;

import com.alterdata.calculo.irpf.models.User;
import com.alterdata.calculo.irpf.repositories.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

@DataJpaTest
@TestPropertySource(locations = "classpath:test.properties")
@DisplayName("Executando testes userRepository")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User createUser(){
        return User.builder()
                .nome("Victor")
                .username("testUser")
                .password("123321")
                .build();
    }

    @Test
    @DisplayName("Save persists User when sucessfull")
    void save_deve_salvar_usuario_quando_nao_houver_errors() {
        User userToBeSaved = createUser();
        User userSaved = this.userRepository.save(userToBeSaved);

        Assertions.assertThat(userSaved).isNotNull();
        Assertions.assertThat(userSaved.getId()).isNotNull();
        Assertions.assertThat(userSaved.getNome()).isEqualTo(userSaved.getNome());
        Assertions.assertThat(userSaved.getUsername()).isEqualTo(userSaved.getUsername());
    }

    @Test
    @DisplayName("Save updates User when sucessfull")
    void save_deve_atualizar_usuario_quando_nao_houver_errors() {
        User userToBeSaved = createUser();
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
    @DisplayName("Delete removes User when sucessfull")
    void delete_deve_remover_usuario_quando_nao_houver_errors() {
        User userToBeSaved = createUser();
        User userSaved = this.userRepository.save(userToBeSaved);
        this.userRepository.delete(userSaved);
        Optional<User> userOptional = this.userRepository.findById(userSaved.getId());

        Assertions.assertThat(userOptional).isEmpty();
    }


    @Test
    @DisplayName("Find by username returns User when sucessfull")
    void findByUsername_deve_retornar_usuario_quando_nao_houver_errors() {
        User userToBeSaved = createUser();
        User userSaved = this.userRepository.save(userToBeSaved);
        String userName = userSaved.getUsername();
        User userByUserName = this.userRepository.findByUsername(userName).orElseThrow();

        Assertions.assertThat(userByUserName).isNotNull();
        Assertions.assertThat(userByUserName.getUsername()).isEqualTo(userName);
    }

    @Test
    @DisplayName("Find by username returns optional empty when no user found")
     void findByUsername_deve_retornar_null_quando_nao_houver_username_valido() {
        Optional<User> userByUserName = this.userRepository.findByUsername("test");
        Assertions.assertThat(userByUserName).isEmpty();
    }

}
