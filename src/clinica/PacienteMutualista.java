/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinica;

/**
 * Clase que representa a los pacientes mutualistas ingresados en un hospital
 *
 * @version v0.1 abril_2019
 * @author Javier Sánchez Domínguez javisandom@gmail.com
 */
public class PacienteMutualista extends Paciente {

    private static final long serialVersionUID = 1L;

    private int numeroDeHospitalizaciones;
    public static int numeroPacientesMutualistas;

    /**
     * Constructor para crear el objeto paciente mutualista con los valores de
     * los parámetros
     *
     * @param NIF Cadena de caracteres que representa el numero del documento
     * NIF del paciente mutualista
     * @param nombrePaciente Cadena de caracteres que representa el nombre del
     * paciente mutualista
     * @param emailNotificaciones Cadena de caracteres que representa el correo
     * electrónico del paciente mutualista
     * @param numeroDeHospitalizaciones Número entero que representa el número
     * de veces que ha sido hospitalizado el paciente mutualista
     */
    public PacienteMutualista(String NIF, String nombrePaciente, String emailNotificaciones, int numeroDeHospitalizaciones) {
        super(NIF, nombrePaciente, emailNotificaciones);
        this.numeroDeHospitalizaciones = numeroDeHospitalizaciones;
        PacienteMutualista.numeroPacientesMutualistas++;
    }

    /**
     * Número de hospitalizaciones del paciente mutualista
     *
     * @return El número de hospitalizaciones
     */
    public int getNumeroDeHospitalizaciones() {
        return numeroDeHospitalizaciones;
    }

    /**
     * Define el número de hospitalizaciones del paciente mutualista
     *
     * @param numeroDeHospitalizaciones Número de hospitalizaciones
     */
    public void setNumeroDeHospitalizaciones(int numeroDeHospitalizaciones) {
        this.numeroDeHospitalizaciones = numeroDeHospitalizaciones;
    }

    /**
     * Método que devuelve una representacion textual del objeto paciente
     * mutualista
     *
     * @return Cadena que representa el nombre, NIF, correo electrónico del
     * paciente y el número de hospitalizaciones
     */
    @Override
    public String toString() {
        StringBuilder datosPaciente = new StringBuilder();

        return super.toString() + datosPaciente.append("\n  Número de hospitalizaciones: " + this.getNumeroDeHospitalizaciones());
    }

}
