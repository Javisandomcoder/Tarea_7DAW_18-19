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
        boolean cambioEntradaRegistro = false;
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
            
            opcion = ES.leeEntero("Introduzca la opción elegida", 0, 6);
            switch(opcion){
                case 0:
                    ES.msg("Saliendo...");
                    break;
                case 1:
                    insertarPaciente(paciente);
                    break;
                case 2:
                    listarPacientes(paciente);
                    break;
                case 3:
                    if (Principal.borrarPaciente(paciente)) {
                        cambioEntradaRegistro = true;
                    }
                    break;
            }
        } while (opcion != 0);

    }
    
    private static boolean insertarPaciente(Paciente[] listado) {
        int posicionEnArray = 0;
        boolean colocar = true;
        boolean insertado = false;
        Paciente paciente = null;

        if (listado[listado.length - 1] != null) {
            ES.msgln("ERROR: Clínica completa");
        } else {//Pedimos los datos de la caja
            ES.msgln("Introduzca los datos del paciente:");
            String NIF = ES.leeCadena("Escriba el NIF del paciente:");
            String nombrePaciente = ES.leeCadena("EScriba el nombre del paciente");
            String emailNotificaciones = ES.leeCadena("Escriba el email del paciente");
            int tipoPaciente = ES.leeEntero("Escriba el tipo de paciente (1-> PRIVADO, 2 -> MUTUALISTA)", 1, 2);
            if(TipoPaciente.eleccionTipoPaciente(tipoPaciente).equals(TipoPaciente.PRIVADO)){
                paciente = new PacientePrivado(NIF, nombrePaciente, emailNotificaciones);
            }else if(TipoPaciente.eleccionTipoPaciente(tipoPaciente).equals(TipoPaciente.MUTUALISTA)){
                paciente = new PacienteMutualista(NIF, nombrePaciente, emailNotificaciones);
            }
                    
            while (colocar && (posicionEnArray < Principal.MAX_PACIENTES)) {
                if (listado[posicionEnArray] == null) {
                    listado[posicionEnArray] = paciente;
                    colocar = false;
                    System.out.println("Se ha añadido un nuevo paciente");
                    insertado = true;
                }
                posicionEnArray++;
            }

        }
        return insertado;

    }
    
    private static void listarPacientes(Paciente[] listado) {
        if (listado[0] == null) {
            ES.msgln("El registro está vacio");
        } else {
            for(int i = 0; i < listado.length && listado[i] != null; ++i) {
                ES.msgln(listado[i].toString() + "\n");
            }
        }
    }
    
    public static void ordenaRegistro(Paciente[] listado) {
        Paciente aux;
        int contador = 0;
        int posicion;

        if (listado != null) {//Comprobamos que el array no esté vacio
            while (contador < listado.length - 1) {
                posicion = contador + 1;
                while (posicion < listado.length) {
                    if (((listado[contador] == null) && (listado[posicion] != null))
                            || ((listado[contador] != null) && (listado[posicion] != null))) {
                        aux = listado[contador];//intercambiamos las posiciones en el array para dejar los huecos al final
                        listado[contador] = listado[posicion];
                        listado[posicion] = aux;
                    }
                    posicion++;
                }
                contador++;
            }

        } else {
            throw new IllegalArgumentException("No se ha indicado ninguna lista para ordenar");
        }
    }
    
      private static boolean borrarPaciente(Paciente[] listado) {
        boolean pacienteBorrado = false;

        if (listado != null) {
            Principal.ordenaRegistro(listado);
            String pacienteAborrar = ES.leeCadena("Introduzca el DNI del paciente a borrar");

            int posicion = Principal.buscarPaciente(listado, pacienteAborrar);
            if (posicion != listado.length) {
                String respuesta = ES.leeRespuesta("Se va a proceder a borrar de la lista:\n" + listado[posicion] + "\n ¿Desea continuar con el borrado? (S/N):");
                if (respuesta.equals("S")) {
                    for (int conta = posicion; conta <= listado.length - 2; conta++) {
                        listado[conta] = listado[conta + 1];
                    }
                    listado[listado.length - 1] = null;
                    pacienteBorrado = true;
                    ES.msgln("Borrado realizado correctamente.");
                } else {
                    ES.msgln("Operación de borrado cancelada.");
                }
            } else {
                System.err.println("No existe nigún paciente con ese DNI en el registro");
            }
        }
        return pacienteBorrado;
    }
      
       public static int buscarPaciente(Paciente[] listado, String DNI) {
        boolean buscar = true;
        int pos = 0;

        Principal.ordenaRegistro(listado);
        while (buscar) {
            if (pos == listado.length) {
                buscar = false;
            } else {
                if (listado[pos] == null) {
                    buscar = false;
                    pos = listado.length;
                } else {
                    if (!listado[pos].getNIF().equals(DNI)) {
                        pos++;
                    } else {
                        buscar = false;
                    }
                }
            }
        }
           System.out.println("BBBBBBB"+ pos);
        return pos;
    }
}
