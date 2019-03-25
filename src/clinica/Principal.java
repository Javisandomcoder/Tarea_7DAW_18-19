/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinica;

import java.io.File;
import utilidades.ES;

/**
 *
 * @author javisandom
 */
public class Principal  {

    private static final int MAX_PACIENTES = 40;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int opcion;
        String nombreFichero = "pacientes.dat";
        File fichero = new File(nombreFichero);
        Paciente[] paciente = new Paciente[MAX_PACIENTES];//Array de pacientes

        if (fichero.exists()) {
            System.out.println("FICHERO EXISTE");
        } else {
            System.out.println("NO EXISTE FICHERO");
        }
        
        opcion = ES.leeEntero("Introduzca la opción elegida", 0, 6);

        //Presentamos el menú
        do {
            ES.msgln("\t =============================================");
            ES.msgln("\t ============ Gestión del Almacén ============");
            ES.msgln("\t =============================================\n");
            ES.msgln("\t\t 1.- Añadir un paciente.");
            ES.msgln("\t\t 2.- Listar pacientes.");
            ES.msgln("\t\t 3.- Borrar un paciente.");
            ES.msgln("\t\t 4.- Guardar datos en fichero.");
            ES.msgln("\t\t 5.- Recuperar datos desde fichero.");
            ES.msgln("\t\t 6.- Escribir lista de pacientes a txt.\n");
            ES.msgln("\t\t 0.- Salir de la aplicación.");
            ES.msgln("\t =============================================");
        } while (opcion != 0);

    }
    
    private static boolean insertarPaciente(Paciente[] listado) {
        int posicionEnArray = 0;
        boolean colocar = true;
        boolean insertado = false;
        Paciente paciente;

        if (listado[listado.length - 1] != null) {
            ES.msgln("Error: Almacén lleno");
        } else {//Pedimos los datos de la caja
            ES.msgln("Introduzca los datos del paciente:");
            String NIF = ES.leeCadena("Escriba el NIF del paciente:");
            String nombrePaciente = ES.leeCadena("EScriba el nombre del paciente");
            String emailNotificaciones = ES.leeCadena("Escriba el email del paciente");
            int tipoPaciente = ES.leeEntero("Escriba el tipo de paciente (1-> PRIVADO, 2 -> MUTUALISTA)", 1, 2);
            while (colocar && (posicionEnArray < Principal.MAX_PACIENTES)) {
                if (listado[posicionEnArray] == null) {
                    listado[posicionEnArray] = paciente;
                    colocar = false;
                    System.out.println("Se ha creado una nueva caja");
                    insertado = true;
                }
                posicionEnArray++;
            }

        }
        return insertado;

    }
}
