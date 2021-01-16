package com.alterdata.calculo.irpf.services.calculo_mensal_salario_liquido;

import org.springframework.stereotype.Service;

@Service
public class CalcSalarioLiq  {

//    private double salarioLiquido;
//
//    @Autowired
//    CalcINSS calcINSS;
//
//    public void generateSalarioLiquido(UserIRRFRequest user){
//        double totalDescontos = generateDescontos(user);
//        this.salarioLiquido = user.getSalarioBruto() - totalDescontos;
//    }
//
//    public double generateDescontos(UserIRRFRequest user){
//        double inss = calcINSS.generateINSS(user.getSalarioBruto());
//        double dependentes = valor_dependente * user.getDependentes();
//        double pensao = user.getPensaoAlimenticia();
//        double planoSaude = user.getPlanoDeSaude();
//        double outros = user.getOutrosDescontos();
//
//        return inss + dependentes + pensao + planoSaude + outros;
//    }
}
