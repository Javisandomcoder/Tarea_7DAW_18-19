/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinica;

/**
 * Clase que representa a los pacientes privados ingresados en un hospital
 *
 * @version v0.1 abril_2019
 * @author Javier Sánchez Domínguez javisandom@gmail.com
 */
public class PacientePrivado extends Paciente {

    private static final long serialVersionUID = 1L;

    private int numeroDeVisitas;
    public static int numeroPacientesPrivados;

    /**
     * Constructor para crear el objeto paciente mutualista con los valores de
     * los parámetros
     *
     * @param NIF Cadena de caracteres que representa el numero del documento
     * NIF del paciente privado
     * @param nombrePaciente Cadena de caracteres que representa el nombre del
     * paciente privado
     * @param emailNotificaciones Cadena de caracteres que representa el correo
     * electrónico del paciente privado
     * @param numDeVisitas Número entero que representa el número de visitas del
     * paciente privado el paciente mutualista
     */
    public PacientePrivado(String NIF, String nombrePaciente, String emailNotificaciones, int numDeVisitas) {
        super(NIF, nombrePaciente, emailNotificaciones);
        this.numeroDeVisitas = numDeVisitas;
        PacientePrivado.numeroPacientesPrivados++;
    }

    /**
     * Número de visitas del paciente privado
     *
     * @return El número de visitas
     */
    public int getNumeroDeVisitas() {
        return numeroDeVisitas;
    }

    /**
     * Define el número de visitas del paciente privado
     *
     * @param numeroDeVisitas Número de visitas
     */
    public void setNumeroDeVisitas(int numeroDeVisitas) {
        this.numeroDeVisitas = numeroDeVisitas;
    }

    /**
     * Método que devuelve una representacion textual del objeto paciente
     * privado
     *
     * @return Cadena que representa el nombre, NIF, correo electrónico del
     * paciente y el número de visitas
     */
    @Override
    public String toString() {
        StringBuilder datosPaciente = new StringBuilder();

        return super.toString() + datosPaciente.append("\n  Número de visitas " + this.getNumeroDeVisitas());
    }

}
