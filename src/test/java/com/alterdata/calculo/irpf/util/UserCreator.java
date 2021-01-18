package com.alterdata.calculo.irpf.util;

import com.alterdata.calculo.irpf.models.account.User;

public class UserCreator {

    public static User createUserToBeSaved() {
        return User.builder()
                .nome("Test")
                .username("testUsername")
                .password("123321")
                .build();
    }

    public static User createValidUser() {
        return User.builder()
                .nome("Test")
                .username("testUsername")
                .password("123321")
                .id(1)
                .build();
    }

    public static User createValidUpdatedUser() {
        return User.builder()
                .nome("Test")
                .username("testUsername 2")
                .password("123321")
                .id(1)
                .build();
    }


}
