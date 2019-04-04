/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinica;

public class PacientePrivado extends Paciente {

    private static final long serialVersionUID = 1L;

    private int numeroDeVisitas;
    public static int numeroPacientesPrivados;

    public PacientePrivado(String NIF, String nombrePaciente, String emailNotificaciones, int numDeVisitas) {
        super(NIF, nombrePaciente, emailNotificaciones);
        this.numeroDeVisitas = numDeVisitas;
        PacientePrivado.numeroPacientesPrivados++;
    }

    public int getNumeroDeVisitas() {
        return numeroDeVisitas;
    }

    public void setNumeroDeVisitas(int numeroDeVisitas) {
        this.numeroDeVisitas = numeroDeVisitas;
    }
    
    

    @Override
    public String toString() {
        return super.toString() + " Número de visitas: " + getNumeroDeVisitas();
    }

}
