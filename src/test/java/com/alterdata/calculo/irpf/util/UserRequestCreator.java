package com.alterdata.calculo.irpf.util;

import com.alterdata.calculo.irpf.models.account.UserRequest;

public class UserRequestCreator {

    public static UserRequest createUserRequestBody() {
        return UserRequest.builder()
                .nome("Test")
                .username("testUsername")
                .password("123321")
                .build();
    }

}
