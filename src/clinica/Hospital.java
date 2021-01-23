/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinica;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import utilidades.ES;

/**
 * Clase principal del programa, que consiste en un bucle do-while que estará
 * iterando continuamente mientras que el usuario no introduzca un 0.
 *
 * @version v0.1 abril_2019
 * 
 */
public class Hospital {

    /**
     * Capacidad máxima del array que representa el registro del hospital
     */
    private static final int MAX_PACIENTES = 40;

    /**
     * Presenta un menú para elegir entre diversas opciones, hace las llamadas a
     * los métodos correspondientes y ofrece al final la opción de elegir una
     * nueva acción o salir del programa
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int opcion;//Opción para elegir en el menú
        boolean cambioEntradaRegistro = false;
        String nombreFichero = "pacientes.dat";//Ruta del fichero
        File fichero = new File(nombreFichero);
        Paciente[] paciente = new Paciente[MAX_PACIENTES];//Array de pacientes

        //Cargamos los datos si existe el fichero al iniciar el programa
        if (fichero.exists()) {
            paciente = Hospital.cargarLista(nombreFichero);
            ES.msgln("Datos del fichero cargados");
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
            switch (opcion) {
                case 0://Salimos del programa preguntando si se quieren guardar cambios en el caso de que haya
                    if (cambioEntradaRegistro) {
                        String guardar = ES.leeCadena("Ha realizado cambios que no ha "
                                + "guardado en disco ¿Desea guardarlos antes de salir? (S/N)");
                        if (guardar.equalsIgnoreCase("S")) {
                            ES.msgln("Guardando datos antes de salir...");
                            boolean resultado = Hospital.guardarArrayEnFichero(paciente, nombreFichero);
                            if (resultado) {
                                ES.msgln("Los datos se han guardado correctamente en el fichero: " + nombreFichero);
                            } else {
                                ES.msgln("Error: los datos NO se han guardado correctamente en el fichero: " + nombreFichero);
                            }
                        } else {
                            ES.msgln("Anulando cambios antes de salir...");
                        }
                    }
                    ES.msgln("Cerrando aplicación...");
                    break;
                case 1://Añadimos un paciente
                    Hospital.insertarPaciente(paciente);
                    cambioEntradaRegistro = true;
                    break;
                case 2://Ofrecemos el listado de pacientes que hay en el hospital
                    listarPacientes(paciente);
                    break;
                case 3://Borramos un paciente del registro del hospital
                    if (Hospital.borrarPaciente(paciente)) {
                        cambioEntradaRegistro = true;
                    }
                    break;
                case 4://Guardamos datos en el fichero
                    if (Hospital.guardarArrayEnFichero(paciente, nombreFichero)) {
                        cambioEntradaRegistro = false;
                        ES.msgln("Los datos se han guardado correctamente en el fichero: " + nombreFichero);
                    } else {
                        ES.msgln("Error: los datos NO se han guardado correctamente en el fichero: " + nombreFichero);
                    }
                    break;
                case 5://Recuperamos datos desde fichero
                    if (cambioEntradaRegistro) {
                        String cargar = ES.leeCadena("Ha realizado cambios que no ha guardado "
                                + "en disco, \nSi continúa con la carga del archivo se restaurarán los "
                                + "datos del disco y se perderán los datos no guardados \n"
                                + "¿Desea continuar con la carga y restaurar los datos del archivo? (S/N)");
                        if (cargar.equalsIgnoreCase("s")) {
                            ES.msgln("Restaurando datos del archivo y descartando cambios no guardados...");
                            paciente = Hospital.cargarLista(nombreFichero);
                            cambioEntradaRegistro = false;
                            ES.msgln("Archivo restaurado en memoria");
                        } else {
                            ES.msgln("Carga del archivo anulada");
                        }
                    } else {
                        paciente = Hospital.cargarLista(nombreFichero);
                    }
                    break;
                case 6:
                    Hospital.registroATxt(paciente);
                    break;
            }
        } while (opcion != 0);

    }

    /**
     * Crea los datos de un paciente y los inserta en la primera posición libre
     * del array
     *
     * @param listado de pacientes en el array
     * @return true si se inserta un paciente nuevo
     */
    private static boolean insertarPaciente(Paciente[] listado) {
        int posicionEnArray = 0;
        boolean colocar = true;
        boolean insertado = false;
        boolean NIFIncorrecto;
        String emailNotificaciones;
        String NIF;

        if (listado[listado.length - 1] != null) {
            ES.msgln("ERROR: Clínica completa");
        } else {//Pedimos los datos de la caja  
            ES.msgln("Introduzca los datos del paciente:");

            do {
                NIFIncorrecto = false;
                NIF = ES.leeCadena("Escriba el NIF del paciente:");
                try {

                    if (!comprobarNIF(NIF)) {
                        System.err.println("El NIF introducido no es correcto");
                        NIFIncorrecto = true;
                    } else if (buscarNIF(listado, NIF)) {
                        System.err.println("Ya existe un paciente con ese NIF en la clínica");
                    }
                } catch (StringIndexOutOfBoundsException soe) {
                    System.err.println("El formato de NIF introducido no es correcto");
                    NIFIncorrecto = true;
                }
            } while (buscarNIF(listado, NIF) || NIFIncorrecto);

            String nombrePaciente = ES.leeCadena("Escriba el nombre del paciente");
            do {
                emailNotificaciones = ES.leeCadena("Escriba el email del paciente");
                if (!comprobarCorreo(emailNotificaciones)) {
                    System.err.println("El formato de correo eléctronico proporcionado no es correcto");
                }
            } while (!comprobarCorreo(emailNotificaciones));
            Paciente paciente = null;
            int tipoPaciente = ES.leeEntero("Escriba el tipo de paciente (1-> PRIVADO, 2 -> MUTUALISTA)", 1, 2);
            if (TipoPaciente.eleccionTipoPaciente(tipoPaciente).equals(TipoPaciente.PRIVADO)) {
                int numVisitas = ES.leeEntero("Escriba el número de visitas (0,100)", 0, 100);
                paciente = new PacientePrivado(NIF, nombrePaciente, emailNotificaciones, numVisitas);
            } else if (TipoPaciente.eleccionTipoPaciente(tipoPaciente).equals(TipoPaciente.MUTUALISTA)) {
                int numHospitalizaciones = ES.leeEntero("Escriba el número de hospitalizaciones (0,100)", 0, 100);
                paciente = new PacienteMutualista(NIF, nombrePaciente, emailNotificaciones, numHospitalizaciones);
            }

            while (colocar && (posicionEnArray < Hospital.MAX_PACIENTES)) {
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

    /**
     * Muestra la lista de pacientes
     *
     * @param listado de array de pacientes
     */
    private static void listarPacientes(Paciente[] listado) {
        int pacientesActuales = 0;

        if (listado[0] == null) {
            ES.msgln("El registro está vacio");
        } else {
            for (int i = 0; i < listado.length && listado[i] != null; ++i) {
                ES.msgln(listado[i].toString() + "\n");
                pacientesActuales++;
            }
            ES.msgln("Total de pacientes: " + pacientesActuales);
        }
    }

    /**
     * Ordena el array para dejar todos los huecos libres al final del mismo
     *
     * @param listado de array de pacientes
     */
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

    /**
     * Borra un paciente del registro del hospital
     *
     * @param listado de array de pacientes
     * @return true si el paciente ha sido borrado correctamente
     */
    private static boolean borrarPaciente(Paciente[] listado) {
        boolean pacienteBorrado = false;

        if (listado != null) {
            Hospital.ordenaRegistro(listado);
            String pacienteAborrar = ES.leeCadena("Introduzca el DNI del paciente a borrar");

            int posicion = Hospital.buscarPaciente(listado, pacienteAborrar);
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
                System.err.println("No existe ningún paciente con ese DNI en el registro");
            }
        }
        return pacienteBorrado;
    }

    /**
     * Busca un paciente dentro del array
     *
     * @param listado de pacientes del array
     * @param NIF Del paciente
     * @return El paciente dentro del array
     */
    public static int buscarPaciente(Paciente[] listado, String NIF) {
        boolean buscar = true;
        int pos = 0;

        Hospital.ordenaRegistro(listado);
        while (buscar) {
            if (pos == listado.length) {
                buscar = false;
            } else {
                if (listado[pos] == null) {
                    buscar = false;
                    pos = listado.length;
                } else {
                    if (!listado[pos].getNIF().equals(NIF)) {
                        pos++;
                    } else {
                        buscar = false;
                    }
                }
            }
        }
        return pos;
    }

    /**
     * Busca un paciente por su NIF dentro del array
     *
     * @param listado de pacientes del array
     * @param NIF del paciente
     * @return true si encuentra el NIF
     */
    public static boolean buscarNIF(Paciente[] listado, String NIF) {
        boolean buscar = true;
        boolean encontrado = false;
        int pos = 0;

        Hospital.ordenaRegistro(listado);
        while (buscar) {
            if (pos == listado.length) {
                buscar = false;
            } else {
                if (listado[pos] == null) {
                    buscar = false;
                    pos = listado.length;
                } else {
                    if (!listado[pos].getNIF().equals(NIF)) {
                        pos++;
                    } else {
                        buscar = false;
                        encontrado = true;
                    }
                }
            }
        }
        return encontrado;
    }

    /**
     * Comprueba si el formato de NIF introducido es correcto
     *
     * @param NIF del paciente
     * @return true si el formato del NIF es correcto
     * @throws StringIndexOutOfBoundsException si el formato del NIF es
     * incorrecto
     */
    public static boolean comprobarNIF(String NIF) throws StringIndexOutOfBoundsException {
        boolean NIFcorrecto;

        int numNIF = Integer.parseInt(NIF.substring(0, 8));
        String letraNIF = NIF.substring(8, 9);
        String[] comprobacionLetra = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};
        int letraCorrecta = numNIF % 23;
        NIFcorrecto = letraNIF.equals(comprobacionLetra[letraCorrecta]);

        return NIFcorrecto;
    }

    /**
     * Comprueba si el formato del correo electrónico es correcto
     *
     * @param email correo del paciente
     * @return true si el formato del correo es correcto
     */
    public static boolean comprobarCorreo(String email) {
        boolean emailCorrecto = false;

        String emailPattern = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@"
                + "[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";
        Pattern pattern = Pattern.compile(emailPattern);
        if (email != null) {
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                emailCorrecto = true;
            }
        }
        return emailCorrecto;
    }

    /**
     * Guarda los datos en el fichero
     *
     * @param paciente Array de pacientes
     * @param ruta en la que se guardará el fichero
     * @return true si se ha almacenado correctamente
     */
    private static boolean guardarArrayEnFichero(Paciente[] paciente, String ruta) {
        boolean almacenado = false;

        try {
            FileOutputStream fichero = new FileOutputStream(new File(ruta));
            ObjectOutputStream ficheroSalida;
            ficheroSalida = new ObjectOutputStream(fichero);
            ficheroSalida.writeObject(paciente);
            ficheroSalida.close();
            almacenado = true;
            ES.msgln("Datos guardados en fichero");
        } catch (FileNotFoundException ex) {
            ES.msgln("Error: Archivo no encontrado");
        } catch (IOException ex) {
            ES.msgln("Problema al acceder al archivo" + ex.getMessage());
        }
        return almacenado;

    }

    /**
     * Lee la información que contiene el archivo
     *
     * @param ruta direccion del fichero donde se guarda el archivo
     * @return el array de pacientes
     */
    private static Paciente[] cargarLista(String ruta) {
        Paciente[] array = null;

        try {
            FileInputStream fichero = new FileInputStream(new File(ruta));
            ObjectInputStream ficheroEntrada = new ObjectInputStream(fichero);
            array = (Paciente[]) ficheroEntrada.readObject();
            ficheroEntrada.close();
        } catch (FileNotFoundException e) {
            System.err.println("Fichero no encontrado");
        } catch (ClassNotFoundException Cex) {
            System.err.println("Problema al cargar fichero");
        } catch (IOException ex) {
            Logger.getLogger(Hospital.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }

    /**
     * Escribe en fichero .txt el contenido del array
     *
     * @param listado de pacientes del array
     */
    private static void registroATxt(Paciente[] listado) {
        try {
            if (listado[0] == null) {
                ES.msgln("No hay cajas en el almacén");
            } else {
                FileWriter ficheroTxt = new FileWriter("cajas.txt");
                PrintWriter escrito = new PrintWriter(ficheroTxt);
                for (int i = 0; i < listado.length && listado[i] != null; i++) {
                    escrito.println(listado[i].toString());
                }
                ficheroTxt.close();
                ES.msgln("Fichero creado con correctamente");
            }
        } catch (FileNotFoundException e) {
            ES.msgln("Archivo no encontrado");
        } catch (IOException ex) {
            Logger.getLogger(Hospital.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
