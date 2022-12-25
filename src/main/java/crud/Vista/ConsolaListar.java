package crud.Vista;

import crud.Controlador.Controlador;
import crud.Controlador.ControladorValidaciones;
import crud.Modelo.ClienteEntity;
import crud.Otros.Enumeraciones.OpcionesOrdenacionCliente;

import java.util.List;
import java.util.Scanner;


/**
 * @author Jose Maria
 */
public class ConsolaListar {

    /**
     * Método encargado de mostrar por pantalla el proceso para listar los clientes de la base de datos actuales
     */
    public static void listarClientes() {
        String Si = "Si";
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Desea ordenar la lista?(Si,No)");

        String respuesta = sc.nextLine();

        while (!ControladorValidaciones.validarSiNo(respuesta)) {
            System.err.println("Por favor, introduzca únicamente 'Si' o 'No.");
            respuesta = sc.nextLine();
        }

        if (respuesta.equals(Si)) {
            listarOrdenado();
        } else {
            listarDesordenado();
        }
    }

    /**
     * Método encargado de recuperar y mostrar los clientes de forma desordenada
     */
    private static void listarDesordenado() {
        System.out.println("Cargando...");

        List<ClienteEntity> clientes = Controlador.devolverTodos();

        mostrar(clientes);
    }

    /**
     * Método encargado de recuperar y mostrar los clientes de forma ordenada
     */
    private static void listarOrdenado() {
        String fechaAlta = "Fecha de alta";
        Scanner sc = new Scanner(System.in);
        List<ClienteEntity> clientes;

        System.out.println("Especifique el orden que desea(Fecha de alta/DNI)");

        String orden = sc.nextLine();

        while (!ControladorValidaciones.validarOrden(orden)) {
            System.err.println("Introduzca 'Fecha de alta' o 'DNI' para ordenar");

            orden = sc.nextLine();
        }

        System.out.println("Cargando...");

        if (orden.equals(fechaAlta)) {
            clientes = Controlador.devolverTodosOrdenados(OpcionesOrdenacionCliente.fechaAlta);
        } else {
            clientes = Controlador.devolverTodosOrdenados(OpcionesOrdenacionCliente.dni);
        }

        mostrar(clientes);
    }

    /**
     * @param clientes Lista con todos los clientes recuperados de la base de datos
     *                 <p>
     *                 Método encargado de mostrar por pantalla todos los clientes pasados por parámetro
     */
    private static void mostrar(final List<ClienteEntity> clientes) {
        for (int i = 0; i < clientes.size(); i++) {
            StringBuilder salida = new StringBuilder();

            String tipo = (clientes.get(i).getTipo() == 0) ? "REGISTRADO" : "SOCIO";
            salida.append("-------------------------"
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
