package com.alterdata.calculo.ir.CalculoIRPF.services.calcSalarioLiquido;

import com.alterdata.calculo.ir.CalculoIRPF.models.UserIRRFIn;
import com.alterdata.calculo.ir.CalculoIRPF.services.calcINSS.CalcINSS;
import com.alterdata.calculo.ir.CalculoIRPF.services.calcIRRF.BaseIRRF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalcSalarioLiq implements BaseIRRF {

//    private double salarioLiquido;
//
//    @Autowired
//    CalcINSS calcINSS;
//
//    public void generateSalarioLiquido(UserIRRFIn user){
//        double totalDescontos = generateDescontos(user);
//        this.salarioLiquido = user.getSalarioBruto() - totalDescontos;
//    }
//
//    public double generateDescontos(UserIRRFIn user){
//        double inss = calcINSS.generateINSS(user.getSalarioBruto());
//        double dependentes = valor_dependente * user.getDependentes();
//        double pensao = user.getPensaoAlimenticia();
//        double planoSaude = user.getPlanoDeSaude();
//        double outros = user.getOutrosDescontos();
//
//        return inss + dependentes + pensao + planoSaude + outros;
//    }
}
