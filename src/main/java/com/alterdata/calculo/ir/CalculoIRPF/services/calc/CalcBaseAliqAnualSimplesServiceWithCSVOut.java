package com.alterdata.calculo.ir.CalculoIRPF.services.calc;

import com.alterdata.calculo.ir.CalculoIRPF.modelsCVS.UserCSVIn;
import com.alterdata.calculo.ir.CalculoIRPF.modelsCVS.UserCSVOut;
import lombok.Getter;
import lombok.Setter;

 @Getter @Setter
    public class CalcBaseAliqAnualSimplesServiceWithCSVOut implements CalcBaseAliquota {

        private UserCSVIn usuarioCSVIn;
        private UserCSVOut usuarioCSVOut;
        private double deducaoSimplificada;

        public CalcBaseAliqAnualSimplesServiceWithCSVOut() {
            this.usuarioCSVIn = new UserCSVIn();
            this.usuarioCSVOut = new UserCSVOut();
        }

        private void createDeducaoSimplificada(){
            double calcDeducaoSimplificada = this.usuarioCSVIn.getRendimentoAnualBruto() * valorBaseDeducaoSimplificada;

            if(calcDeducaoSimplificada > tetoDeducaoSimplificada ){
                calcDeducaoSimplificada = tetoDeducaoSimplificada;
            }
            this.deducaoSimplificada = calcDeducaoSimplificada;
        }

        private void createCalculoBase2(){
            double baseCalculo = this.usuarioCSVIn.getRendimentoAnualBruto() - deducaoSimplificada;
            this.usuarioCSVOut.setBaseDeCalculo(baseCalculo);
        }

        private void aliquota1() {
            if (this.usuarioCSVOut.getBaseDeCalculo() <= base1_valorInicial) {
                this.usuarioCSVOut.setPorcentagemAliquota(base1_aliquota);
                this.usuarioCSVOut.setParcelaDedutivel(base1_parcelaADeduzir);
            }
        }


        private void aliquota2() {
            if (this.usuarioCSVOut.getBaseDeCalculo() >= base2_valorInicial && this.usuarioCSVOut.getBaseDeCalculo() <= base2_valorFinal) {
                this.usuarioCSVOut.setPorcentagemAliquota(base2_aliquota);
                this.usuarioCSVOut.setParcelaDedutivel(base2_parcelaADeduzir);
            }
        }


        private void aliquota3() {
            if (this.usuarioCSVOut.getBaseDeCalculo() >= base3_valorInicial && this.usuarioCSVOut.getBaseDeCalculo() <= base3_valorFinal) {
                this.usuarioCSVOut.setPorcentagemAliquota(base3_aliquota);
                this.usuarioCSVOut.setParcelaDedutivel(base3_parcelaADeduzir);
            }
        }


        private void aliquota4() {
            if (this.usuarioCSVOut.getBaseDeCalculo() >= base4_valorInicial && this.usuarioCSVOut.getBaseDeCalculo() <= base4_valorFinal) {
                this.usuarioCSVOut.setPorcentagemAliquota(base4_aliquota);
                this.usuarioCSVOut.setParcelaDedutivel(base4_parcelaADeduzir);
            }
        }


        private void aliquota5() {
            if (this.usuarioCSVOut.getBaseDeCalculo() >= base5_valorInicial) {
                this.usuarioCSVOut.setParcelaDedutivel(base5_parcelaADeduzir);
                this.usuarioCSVOut.setPorcentagemAliquota(base5_aliquota);
            }
        }


        public UserCSVOut generateDeducaoAndAliquota(UserCSVIn usrCSVIn) {
            this.usuarioCSVIn.setRendimentoAnualBruto(usrCSVIn.getRendimentoAnualBruto());
            this.usuarioCSVOut.copyUsuarioEntrada(usrCSVIn);

            createDeducaoSimplificada();
            createCalculoBase2();
            aliquota1();
            aliquota2();
            aliquota3();
            aliquota4();
            aliquota5();

            return this.usuarioCSVOut;
        }

    }


