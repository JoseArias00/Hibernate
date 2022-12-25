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

    /**
     * Método encargado de mostrar por pantalla el proceso de solicitud de datos e inserción del nuevo cliente
     */
    public static void insertarClientes() {
        String vacio = "";
        ClienteEntity clienteEntity = new ClienteEntity();
        Scanner sc = new Scanner(System.in);

        //Asignamos el identificador del cliente
        System.out.println("Introduzca el DNI/NIE/CIF del cliente:");

        String DNI = sc.nextLine();

        while (!ControladorValidaciones.validarDNI(DNI)) {
            if (!ConstantesConsola.EXIT.equals(DNI)) {

            }
            System.err.println("El DNI/NIE/CIF no puede ser nulo, por favor, introdúzcalo de nuevo:");
            DNI = sc.nextLine();
        }


        clienteEntity.setDni(DNI);

        //Asignamos el nombre del cliente
        System.out.println("Introduzca el nombre del cliente:");

        String nombre = sc.nextLine();

        while (vacio.equals(nombre)) {
            System.err.println("El nombre no puede ser nulo, por favor, introdúzcalo de nuevo:");
            nombre = sc.nextLine();
        }

        clienteEntity.setNombre(nombre);

        //Asignamos los apellidos del cliente
        System.out.println("Introduzca los apellidos del cliente:");

        String apellidos = sc.nextLine();

        while (vacio.equals(apellidos)) {
            System.err.println("Los apellidos no puede ser nulo, por favor, introdúzcalo de nuevo:");

            apellidos = sc.nextLine();
        }

        clienteEntity.setApellidos(apellidos);

        //Asignamos la fecha de alta del cliente
        System.out.println("Introduzca la fecha de alta del cliente (yyyy/MM/dd HHmmss):");


        String fechaAlta = sc.nextLine();

        while (!ControladorValidaciones.validarFecha(fechaAlta)) {
            System.err.println("Introduzca la fecha de alta del cliente (yyyy/MM/dd HHmmss):");

            fechaAlta = sc.nextLine();
        }

        clienteEntity.setFechaAlta(Controlador.devolverFechaAlta(fechaAlta));

        //Asignamos el tipo de cliente
        System.out.println("Introduzca el tipo del cliente:"
                + "\n0.Registrado"
                + "\n1.Socio");

        String tipo = sc.nextLine();

        while (!ControladorValidaciones.validarTipo(tipo)) {
            System.err.println("El tipo no es válido, por favor, introdúzcalo de nuevo:");

            tipo = sc.nextLine();
        }

        clienteEntity.setTipo(Byte.parseByte(tipo));

        //Asignamos la cuota máxima del cliente cuando sea necesaria
        if (Byte.parseByte(tipo) == 0) {
            System.out.println("Introduzca la cuota máxima del cliente:");


            String cuota = sc.nextLine();

            while (!ControladorValidaciones.validarCuota(cuota)) {
                System.err.println("La cuota máxima no es válida, por favor, introdúzcala de nuevo:");

                cuota = sc.nextLine();
            }

            BigDecimal cuotaMax = new BigDecimal(cuota);
            clienteEntity.setCuotaMaxima(cuotaMax);
        }

        System.out.println("Cargando...");

        try {
            Controlador.insertarCliente(clienteEntity);
            System.out.println("\u001B[32m" + "Cliente añadido a la base de datos." + "\u001B[0m");
        } catch (ClienteException exception) {
            System.err.println("No ha sido posible modificar el cliente: " + exception.getMessage());
        }
    }

}
