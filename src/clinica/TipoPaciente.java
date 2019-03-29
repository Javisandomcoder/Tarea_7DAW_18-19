/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinica;

/**
 *
 * @author javisandom
 */
public enum TipoPaciente {
    PRIVADO,
    MUTUALISTA;

    public static TipoPaciente getPRIVADO() {
        return PRIVADO;
    }

    public static TipoPaciente getMUTUALISTA() {
        return MUTUALISTA;
    }
    
    

    public static TipoPaciente eleccionTipoPaciente(int eleccion) {
        TipoPaciente tip = null;
        switch (eleccion) {
            case 1:
                tip = TipoPaciente.PRIVADO;
                break;
            case 2:
                tip = TipoPaciente.MUTUALISTA;
                break;
        }
        return tip;
    }

}
