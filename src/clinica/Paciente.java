/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinica;

import java.io.Serializable;

/**
 * Clase abstracta que representa de forma los pacientes de un hospital
 *
 * @version v0.1 abril_2019
 * @author Javier Sánchez Domínguez javisandom@gmail.com
 */
public abstract class Paciente implements Serializable {

    private static final long serialVersionUID = 1L;
    private String NIF;
    private String nombrePaciente;
    private String emailNotificaciones;

    /**
     * Constructor para crear el objeto paciente con los valores de los
     * parámetros
     *
     * @param NIF Cadena de caracteres que representa el numero del documento
     * NIF del paciente
     * @param nombrePaciente Cadena de caracteres que representa el nombre del
     * paciente
     * @param emailNotificaciones Cadena de caracteres que representa el correo
     * electrónico del paciente
     */
    public Paciente(String NIF, String nombrePaciente, String emailNotificaciones) {
        this.NIF = NIF;
        this.nombrePaciente = nombrePaciente;
        this.emailNotificaciones = emailNotificaciones;
    }

    /**
     * Constructor copia de la clase
     *
     * @param paciente cera una copia de un objeto paciente
     */
    public Paciente(Paciente paciente) {
        this.NIF = paciente.NIF;
        this.nombrePaciente = paciente.nombrePaciente;
        this.emailNotificaciones = paciente.emailNotificaciones;
    }

    /**
     * Número NIF del paciente
     *
     * @return Devuelve la cadena que representa el número NIF del paciente
     */
    public String getNIF() {
        return NIF;
    }

    /**
     * Define la cadena que representa el número del NIF del paciente
     *
     * @param NIF Cadena que se asignará al NIF del paciente
     */
    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    /**
     * Nombre del paciente
     *
     * @return El nombre del paciente
     */
    public String getNombrePaciente() {
        return nombrePaciente;
    }

    /**
     * Define el nombre del paciente
     *
     * @param nombrePaciente Nombre del paciente
     */
    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    /**
     * Correo elécrónico del paciente
     *
     * @return El correo del paciente
     */
    public String getEmailNotificaciones() {
        return emailNotificaciones;
    }

    /**
     * Define el correo electrónico del paciente
     *
     * @param emailNotificaciones Correo del paciente
     */
    public void setEmailNotificaciones(String emailNotificaciones) {
        this.emailNotificaciones = emailNotificaciones;
    }

    /**
     * Método que devuelve una representacion textual del objeto paciente
     *
     * @return Cadena que representa el nombre NIF y correo electrónico del
     * paciente
     */
    @Override
    public String toString() {
        StringBuilder datosPaciente = new StringBuilder();

        datosPaciente.append("-NIF: ");
        datosPaciente.append(this.getNIF());
        datosPaciente.append("   Nombre paciente: ");
        datosPaciente.append(this.getNombrePaciente());
        datosPaciente.append("   Email para notificaciones: ");
        datosPaciente.append(this.getEmailNotificaciones());

        return datosPaciente.toString();
    }
}
