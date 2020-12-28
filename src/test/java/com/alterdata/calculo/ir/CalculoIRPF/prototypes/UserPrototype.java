package com.alterdata.calculo.ir.CalculoIRPF.prototypes;

import com.alterdata.calculo.ir.CalculoIRPF.dto.UserDto;
import com.alterdata.calculo.ir.CalculoIRPF.entity.User;

public class UserPrototype {

    public  static User completeTestUserModel(){
        User usr = new User();
        usr.setEmail("victor@test");
        usr.setUsername("Test Username");
        usr.setPassword("123321");
        usr.setNome("Victor");
        return usr;
    }

    public static UserDto createCompleteUserDto(){
        return UserDto.builder()
                .email("victor@test")
                .nome("Victor")
                .userName("Test Username")
                .build();
    }

}
