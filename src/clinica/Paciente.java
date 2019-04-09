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
    private String NIF;
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

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getEmailNotificaciones() {
        return emailNotificaciones;
    }

    public void setEmailNotificaciones(String emailNotificaciones) {
        this.emailNotificaciones = emailNotificaciones;
    }

    
    
    @Override
    public String toString(){
        StringBuilder datosPaciente = new StringBuilder();
        
        datosPaciente.append(String.format("NIF: %-8s", this.NIF));
        datosPaciente.append(String.format("  Nombre paciente: %-8s", this.nombrePaciente));
        datosPaciente.append(String.format("  Email para notificaciones: %-8s", this.emailNotificaciones));
       
        return datosPaciente.toString();
    }
}
