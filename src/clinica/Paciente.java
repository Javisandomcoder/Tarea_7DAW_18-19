/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinica;

import java.io.Serializable;

/**
 *
 * @author javisandom
 */
public abstract class Paciente implements Serializable{

    private static final long serialVersionUID = 1L;
    String NIF;
    private String nombrePaciente;
    private String emailNotificaciones;
    
    
    public Paciente(String NIF, String nombrePaciente,String emailNotificaciones){
        this.NIF = NIF;
        this.nombrePaciente = nombrePaciente;
        this.emailNotificaciones = emailNotificaciones;
    }
    
    public Paciente(Paciente paciente){
        this.NIF = paciente.NIF;
        this.nombrePaciente = paciente.nombrePaciente;
        this.emailNotificaciones = paciente.emailNotificaciones;
    }

    public String getNIF() {
        return NIF;
    }
    
    @Override
    public String toString(){
        StringBuilder datosPaciente = new StringBuilder();
        
        datosPaciente.append("NIF: ");
        datosPaciente.append(this.NIF);
        datosPaciente.append(". Nombre paciente: ");
        datosPaciente.append(this.nombrePaciente);
        datosPaciente.append(". email para notificaciones: ");
        datosPaciente.append(this.emailNotificaciones);
        
        return datosPaciente.toString();
    }
}
