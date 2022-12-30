package crud.Vista;

import crud.Controlador.Controlador;
import crud.Controlador.ControladorValidaciones;
import crud.Modelo.ClienteEntity;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import static crud.Controlador.Controlador.consultarPorDNI;

/**
 * @author Jose Maria
 */
public class ConsolaBorrar {

    /**
     * Método que muestra por pantalla el proceso para borrar clientes
     */
    public static void borrarClientes() {
        Scanner sc = new Scanner(System.in);
        HashMap<Integer, ClienteEntity> mapClientes = new HashMap<>();

        System.out.print("Introduzca el DNI del cliente que desea consultar: ");
        String DNI = sc.nextLine();
        List<ClienteEntity> clientes = consultarPorDNI(DNI);
        System.out.println("Cargando...");

        while (clientes.isEmpty()) {
            System.err.print("No existe ningún cliente con ese DNI, introduzca otro si lo desea: ");
            clientes = consultarPorDNI(sc.nextLine());
            System.out.println("Cargando...");
        }

        for (int numero = 0; numero < clientes.size(); numero++) {
            mapClientes.put(numero + 1, clientes.get(numero));
        }

        mostrar(clientes);

        System.out.println("Desea borrar:"
                + "\n1.Una instancia"
                + "\n2.Todas las instancias");

        String opcion = sc.nextLine();

        while (!ControladorValidaciones.validarAccion(opcion)) {
            System.err.println("Introduzca una opción válida.");

            opcion = sc.nextLine();
        }

        switch (opcion) {
            case "1":
                borrarUnaInstancia(mapClientes);
                break;
            case "2":
                borrarTodas(clientes);
            default:
        }

    }

    /**
     * @param clientes Un mapa donde cada cliente de la base de datos tiene asignado un valor
     *                 identificativo propio de la aplicacion para luego ser seleccionado por
     *                 el usuario.
     *                 <p>
     *                 Método encargado de pedir por pantalla una única instancia de un cliente para borrarla
     */
    private static void borrarUnaInstancia(final HashMap<Integer, ClienteEntity> clientes) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca el número de la instancia que desea borrar: ");
        BigInteger numero = sc.nextBigInteger();

        while (numero.compareTo(BigInteger.valueOf(0)) < 0 || numero.compareTo(BigInteger.valueOf(clientes.size())) > 0) {
            System.err.println("Introduzca una opción válida.");

            numero = sc.nextBigInteger();
        }

        Controlador.borrarCliente(clientes.get(numero));

        System.out.println("\u001B[32m" + "Instancia del cliente borrada de la base de datos." + "\u001B[0m");
    }

    /**
     * @param clientes La lista de todos los clientes de la base de datos
     *                 <p>
     *                 Método encargado de borrar todas las instancias de un cliente
     */
    private static void borrarTodas(final List<ClienteEntity> clientes) {
        for (ClienteEntity cliente : clientes) {
            Controlador.borrarCliente(cliente);
        }

        System.out.println("\u001B[32m" + "Cliente borrado de la base de datos." + "\u001B[0m");
    }

    /**
     * @param clientes La lista de todos los clientes de la base de datos
     *                 <p>
     *                 Método encargado de mostrar por pantalla todos los clientes de la base de datos
     *                 con su respectivo número identificativo
     */
    public static void mostrar(final List<ClienteEntity> clientes) {
        for (int i = 0; i < clientes.size(); i++) {
            StringBuilder salida = new StringBuilder();

            String tipo = (clientes.get(i).getTipo() == 0) ? "REGISTRADO" : "SOCIO";
            salida.append("-------------------------"
                    + "\nInstancia número " + (i + 1)
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
