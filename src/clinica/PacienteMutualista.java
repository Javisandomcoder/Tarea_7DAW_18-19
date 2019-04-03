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
public class PacienteMutualista extends Paciente {

    private static final long serialVersionUID = 1L;

    private int numeroDeHospitalizaciones;
    public static int numeroPacientesMutualistas;

    public PacienteMutualista(String NIF, String nombrePaciente, String emailNotificaciones, int numeroDeHospitalizaciones) {
        super(NIF, nombrePaciente, emailNotificaciones);
        this.numeroDeHospitalizaciones = numeroDeHospitalizaciones;
        PacienteMutualista.numeroPacientesMutualistas++;
    }

    @Override
    public String toString() {
        return super.toString() + " Número de hospitalizaciones: " + this.numeroDeHospitalizaciones;
    }

}
