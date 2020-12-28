package com.alterdata.calculo.ir.CalculoIRPF.services.user;

import com.alterdata.calculo.ir.CalculoIRPF.dto.UserDto;
import com.alterdata.calculo.ir.CalculoIRPF.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UsersConverter {


    public User fromUserDtoToUser(UserDto usersDto) {
        User user = new User();
        user.setId(usersDto.getId());
        user.setEmail(usersDto.getEmail());
        user.setNome(usersDto.getNome());
        user.setUsername(usersDto.getUserName());
        return user;
    }

    public UserDto fromUserToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .userName(user.getUsername())
                .nome(user.getNome())
                .build();
    }
}
