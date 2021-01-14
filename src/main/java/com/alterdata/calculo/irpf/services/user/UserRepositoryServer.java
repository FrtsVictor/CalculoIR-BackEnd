package com.alterdata.calculo.irpf.services.user;

import com.alterdata.calculo.irpf.dto.UserDTO;
import com.alterdata.calculo.irpf.exceptions.BadRequestException;
import com.alterdata.calculo.irpf.models.User;
import com.alterdata.calculo.irpf.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

@Component
@RequiredArgsConstructor
public class UserRepositoryServer {

    private final UserRepository userRepository;
    private final PasswordEncoder bcryptEncoder;

    public User findByIdOrThrowBadRequestException(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("User not found"));
    }

    @Transactional
    public User save(@Valid UserDTO user) {
//        User userAlreadyExists = userRepository.findByUsername(user.getUsername()).;
        return this.userRepository.save(saveUserDTOToUserWithPassEncoder(user));
    }

    private User saveUserDTOToUserWithPassEncoder(UserDTO user){
        return User.builder()
                .nome(user.getNome())
                .username(user.getUsername())
                .password(bcryptEncoder.encode(user.getPassword()))
                .build();
    }

    public User getByUserNameOrThowBadRequestException(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(()-> new BadRequestException("User not found"));
    }

    public void delete(Integer id) {
        userRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public User replace(@Valid UserDTO userDto) {
        User updateUser = getByUserNameOrThowBadRequestException(userDto.getUsername());

        if(userDto.getPassword().isBlank() || userDto.getPassword().equals(null) ){
            updateUser.setPassword(bcryptEncoder.encode(userDto.getPassword()));
        }

        if(userDto.getNome().isBlank() || userDto.getNome().equals(null) ){
            updateUser.setNome(userDto.getNome());
        }

        return userRepository.save(updateUser);
    }

}
