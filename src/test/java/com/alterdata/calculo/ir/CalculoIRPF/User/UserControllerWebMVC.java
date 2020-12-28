package com.alterdata.calculo.ir.CalculoIRPF.User;

import com.alterdata.calculo.ir.CalculoIRPF.controllers.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
public class UserControllerWebMVC {

    @Autowired
    UserController userController;

    @Autowired
    MockMvc mockMvc;



}
