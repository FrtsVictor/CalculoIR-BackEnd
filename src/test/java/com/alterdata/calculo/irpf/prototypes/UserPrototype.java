package com.alterdata.calculo.irpf.prototypes;

import com.alterdata.calculo.irpf.models.User;

public class UserPrototype {

    public  static User completeTestUserModel(){
        User usr = new User();
        usr.setUsername("Test Username");
        usr.setPassword("123321");
        usr.setNome("Victor");
        return usr;
    }


}
