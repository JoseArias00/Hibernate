package crud.Vista;

import crud.Controlador.Controlador;
import crud.Controlador.ControladorValidaciones;
import crud.Otros.Constantes.ConstantesConsola;
import crud.Otros.Excepciones.ClienteException;
import crud.Modelo.ClienteEntity;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * @author Jose Maria
 */
public class ConsolaInsertar {

    private static ClienteEntity clienteEntity;
    private static Scanner sc = new Scanner(System.in);
    private static boolean salida;


    /**
     * Método encargado de mostrar por pantalla el proceso de solicitud de datos e inserción del nuevo cliente
     */
    public static void insertarClientes() {
        clienteEntity = new ClienteEntity();
        salida = false;
        //Asignamos el identificador del cliente
        pedirDNI();
        //Asignamos el nombre del cliente
        if (!salida) {
            pedirNombre();
        }
        //Asignamos los apellidos del cliente
        if (!salida) {
            pedirApellido();
        }
        //Asignamos la fecha de alta del cliente
        if (!salida) {
            pedirFechaAlta();
        }
        //Asignamos el tipo de cliente
        if (!salida) {
            pedirTipo();
        }
        //Asignamos la cuota máxima del cliente cuando sea necesaria
        if (!salida && clienteEntity.getTipo() == 0) {
            pedirCuota();
        }

        if (!salida) {
            System.out.println("Cargando...");

            try {
                Controlador.insertarCliente(clienteEntity);
                System.out.println("\u001B[32m" + "Cliente añadido a la base de datos." + "\u001B[0m");
            } catch (ClienteException exception) {
                System.err.println("No ha sido posible modificar el cliente: " + exception.getMessage());
            }
        }

    }

    private static void pedirDNI() {
        System.out.println("Introduzca el DNI/NIE/CIF del cliente:");

        String DNI = sc.nextLine();

        while (!ControladorValidaciones.validarDNI(DNI) && !salida) {
            if (ConstantesConsola.EXIT.equalsIgnoreCase(DNI)) {
                salida = true;
            } else {
                System.err.println("El DNI/NIE/CIF no es válido, por favor, introdúzcalo de nuevo:");
                DNI = sc.nextLine();
            }
        }


        clienteEntity.setDni(DNI);
    }

    private static void pedirNombre() {
        System.out.println("Introduzca el nombre del cliente:");

        String nombre = sc.nextLine();

        while (ConstantesConsola.VACIO.equals(nombre) || (ConstantesConsola.EXIT.equals(nombre) && !salida)) {
            if (ConstantesConsola.EXIT.equalsIgnoreCase(nombre)) {
                salida = true;
            } else {
                System.err.println("El nombre no puede ser nulo, por favor, introdúzcalo de nuevo:");
                nombre = sc.nextLine();
            }
        }

        clienteEntity.setNombre(nombre);
    }

    private static void pedirApellido() {
        System.out.println("Introduzca los apellidos del cliente:");

        String apellidos = sc.nextLine();

        while (ConstantesConsola.VACIO.equals(apellidos) || (ConstantesConsola.EXIT.equals(apellidos) && !salida)) {
            if (ConstantesConsola.EXIT.equalsIgnoreCase(apellidos)) {
                salida = true;
            } else {
                System.err.println("Los apellidos no puede ser nulo, por favor, introdúzcalo de nuevo:");
                apellidos = sc.nextLine();
            }
        }

        clienteEntity.setApellidos(apellidos);
    }

    private static void pedirFechaAlta() {
        System.out.println("Introduzca la fecha de alta del cliente (yyyy/MM/dd HHmmss):");


        String fechaAlta = sc.nextLine();

        while (!ControladorValidaciones.validarFecha(fechaAlta) && !salida) {
            if (ConstantesConsola.EXIT.equalsIgnoreCase(fechaAlta)) {
                salida = true;
            } else {
                System.err.println("Introduzca la fecha de alta del cliente (yyyy/MM/dd HHmmss):");
                fechaAlta = sc.nextLine();
            }

        }

        clienteEntity.setFechaAlta(Controlador.devolverFechaAlta(fechaAlta));

    }

    private static void pedirTipo() {
        System.out.println("Introduzca el tipo del cliente:"
                + "\n0.Registrado"
                + "\n1.Socio");

        String tipo = sc.nextLine();

        while (!ControladorValidaciones.validarTipo(tipo) && !salida) {
            if (ConstantesConsola.EXIT.equalsIgnoreCase(tipo)) {
                salida = true;
            } else {
                System.err.println("El tipo no es válido, por favor, introdúzcalo de nuevo:");
                tipo = sc.nextLine();
            }
        }

        clienteEntity.setTipo(Byte.parseByte(tipo));
    }

    private static void pedirCuota() {
        System.out.println("Introduzca la cuota máxima del cliente:");


        String cuota = sc.nextLine();

        while (!ControladorValidaciones.validarCuota(cuota) && !salida) {
            if (ConstantesConsola.EXIT.equalsIgnoreCase(cuota)) {
                salida = true;
            } else {
                System.err.println("La cuota máxima no es válida, por favor, introdúzcala de nuevo:");
                cuota = sc.nextLine();
            }
        }

        BigDecimal cuotaMax = new BigDecimal(cuota);
        clienteEntity.setCuotaMaxima(cuotaMax);
    }
}
