/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinica;


public class PacientePrivado extends PacienteMutualista {

    private static final long serialVersionUID = 1L;
    
    private int numeroDeVisitas;
    public static int numeroPacientesPrivados;

    public PacientePrivado(String NIF, String nombrePaciente, String emailNotificaciones) {
        super(NIF, nombrePaciente, emailNotificaciones);
    }
}
