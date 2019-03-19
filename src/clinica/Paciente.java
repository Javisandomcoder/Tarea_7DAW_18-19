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
public abstract class Paciente {
    private final String NIF;
    private final String nombrePaciente;
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
}
