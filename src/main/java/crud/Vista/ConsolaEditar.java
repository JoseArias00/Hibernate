package crud.Vista;

import crud.Controlador.Controlador;
import crud.Controlador.ControladorValidaciones;
import crud.Otros.Excepciones.ClienteException;
import crud.Modelo.ClienteEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/**
 * @author Jose Maria
 */
public class ConsolaEditar {

    private static ClienteEntity cliente;

    /**
     * Método que muestra por pantalla el proceso para editar clientes
     */
    public static void editarClientes() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Cargando...");
        List<ClienteEntity> clientes = Controlador.devolverTodos();

        mostrar(clientes);

        System.out.println("Que cliente desea modificar, introduzca su número");

        int numeroCliente = sc.nextInt();

        while (numeroCliente < 0 || numeroCliente > clientes.size()) {
            System.err.println("Por favor,introduzca un número de cliente");

            numeroCliente = sc.nextInt();
        }

        cliente = clientes.get(numeroCliente - 1);
        boolean seguir;
        String si = "Si";

        do {
            modificar();
            System.out.println("Desea realizar alguna modificación más(Si/No)");
            String siNo;
            siNo = sc.nextLine();

            while (!ControladorValidaciones.validarSiNo(siNo)) {
                System.err.println("Introduzca 'Si' o 'No'");

                siNo = sc.nextLine();
            }

            seguir = siNo.equals(si);
        } while (seguir);

        try {
            Controlador.editarCliente(cliente);
            System.out.println("\u001B[34m" + "Cliente modificado" + "\u001B[0m");
        } catch (ClienteException exception) {
            System.err.println("No ha sido posible modificar el cliente: " + exception.getMessage());
        }
    }

    /**
     * Método encargado de pedir por pantalla los campos a modificar
     */
    private static void modificar() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Que campo desea modificar:"
                + "\n1.Nombre"
                + "\n2.Apellidos"
                + "\n3.Fecha de alta"
                + "\n4.Tipo"
                + "\n5.Cuota máxima");

        String opcion = sc.nextLine();

        while (!ControladorValidaciones.validarCampos(opcion)) {
            System.err.println("Introduzca el número de un campo válido");

            opcion = sc.nextLine();
        }

        byte campo = Byte.parseByte(opcion);

        switch (campo) {
            case 1:
                modificarNombre();
                break;
            case 2:
                modificarApellidos();
                break;
            case 3:
                modificarFechaAlta();
                break;
            case 4:
                modificarTipo();
                break;
            case 5:
                modificarCuotaMaxima();
            default:
        }
    }

    /**
     * Método encargado de modificar la cuota máxima del cliente
     */
    private static void modificarCuotaMaxima() {
        Scanner sc = new Scanner(System.in);
        if (cliente.getTipo() == 0) {
            System.out.println("Introduzca la nueva cuota máxima del cliente:");

            String cuota = sc.nextLine();

            while (!ControladorValidaciones.validarCuota(cuota)) {
                System.err.println("La cuota máxima no es válida, por favor, introdúzcala de nuevo:");

                cuota = sc.nextLine();
            }

            BigDecimal cuotaMax = new BigDecimal(cuota);
            cliente.setCuotaMaxima(cuotaMax);
        }
    }

    /**
     * Método encargado de modificar el tipo del cliente
     */
    private static void modificarTipo() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca el nuevo tipo del cliente:"
                + "\n0.Registrado"
                + "\n1.Socio");


        String tipo = sc.nextLine();

        while (!ControladorValidaciones.validarTipo(tipo)) {
            System.err.println("El tipo no es válido, por favor, introdúzcalo de nuevo:");

            tipo = sc.nextLine();
        }

        cliente.setTipo(Byte.parseByte(tipo));
    }

    /**
     * Método encargado de modificar la fecha de alta del cliente
     */
    private static void modificarFechaAlta() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca la nueva fecha de alta del cliente (yyyy/MM/dd HHmmss):");
        String fechaAlta = sc.nextLine();

        while (!ControladorValidaciones.validarFecha(fechaAlta)) {
            System.err.println("Introduzca una fecha de alta del cliente válida(yyyy/MM/dd HHmmss):");

            fechaAlta = sc.nextLine();
        }

        cliente.setFechaAlta(Controlador.devolverFechaAlta(fechaAlta));
    }

    /**
     * Método encargado de modificar los apellidos del cliente
     */
    private static void modificarApellidos() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduzca el nuevo nombre:");
        String nuevoApellidos = sc.nextLine();

        cliente.setApellidos(nuevoApellidos);
    }

    /**
     * Método encargado de modificar el nombre del cliente
     */
    private static void modificarNombre() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduzca el nuevo nombre:");
        String nuevoNombre = sc.nextLine();

        cliente.setNombre(nuevoNombre);
    }

    /**
     * @param clientes Lista con todos los clientes a mostrar por pantalla
     *                 <p>
     *                 Método encargado de mostrar por pantalla la lista de clientes pasada por parámetros
     */
    public static void mostrar(final List<ClienteEntity> clientes) {
        for (int i = 0; i < clientes.size(); i++) {
            StringBuilder salida = new StringBuilder();

            String tipo = (clientes.get(i).getTipo() == 0) ? "REGISTRADO" : "SOCIO";
            salida.append("-------------------------"
                    + "\nCliente número " + (i + 1)
                    + "\nNombre: " + clientes.get(i).getNombre()
                    + "\nApellidos: " + clientes.get(i).getApellidos()
                    + "\nFecha de alta: " + clientes.get(i).getFechaAlta()
                    + "\nTipo: " + tipo);

            if (clientes.get(i).getTipo() == 0) {
                salida.append("\nCuota máxima: " + clientes.get(i).getCuotaMaxima());
            }

            System.out.println("\u001B[34m" + salida + "\u001B[0m");
        }
    }

}
