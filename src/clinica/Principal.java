/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinica;

import java.io.File;
import java.io.Serializable;
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
        String nombreFichero = "pacientes.dat";
        File fichero = new File(nombreFichero);
        Paciente[] paciente = new Paciente[MAX_PACIENTES];//Array de pacientes

        if (fichero.exists()) {
            System.out.println("FICHERO EXISTE");
        } else {
            System.out.println("NO EXISTE FICHERO");
        }

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
        } while (false);

    }
}
