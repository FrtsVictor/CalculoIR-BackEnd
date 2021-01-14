package com.alterdata.calculo.irpf.CalcUser;

import com.alterdata.calculo.irpf.services.user.DefaultUserService;
import org.springframework.beans.factory.annotation.Autowired;

public class ClacUserInTests {

    @Autowired
    DefaultUserService userService;

//    @Test
//    public void deve_retornar_exception_nome_invalido() throws UserCVSInValitaionException {
//        UserIRRFIn user = new UserIRRFIn();
//        user.setNome("a");
//        user.setSalarioMensalBruto(10000);
//
//        UserCVSInValitaionException exception = assertThrows(UserCVSInValitaionException.class, () -> {
//            userService.validateUser(user);
//        });


//    }

}
