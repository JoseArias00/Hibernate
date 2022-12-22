package crud.Vista;

import crud.Controlador.Controlador;
import crud.Modelo.ClienteEntity;

import java.util.List;
import java.util.Scanner;

/**
 * @author Jose Maria
 */
public class ConsolaConsultar {

    /**
     * Método que muestra por pantalla el proceso para consultar clientes
     */
    public static void consultarClientes() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduzca el DNI del cliente que desea consultar: ");
        String DNI = sc.nextLine();
        System.out.println("Cargando...");
        List<ClienteEntity> clientes = Controlador.consultarPorDNI(DNI);


        while (clientes.isEmpty()) {
            System.err.print("No existe ningún cliente con ese DNI, introduzca otro si lo desea: ");
            System.out.println("Cargando...");
            clientes = Controlador.consultarPorDNI(sc.nextLine());
        }

        mostrar(clientes);
    }

    /**
     * @param clientes Lista con los clientes recuperados de la base de datos
     *                 <p>
     *                 Método encargado de mostrar por pantalla todos los clientes pasados por parámetro
     */
    private static void mostrar(final List<ClienteEntity> clientes) {
        for (ClienteEntity clienteEntity : clientes) {
            StringBuilder salida = new StringBuilder();

            String tipo = (clienteEntity.getTipo() == 0) ? "REGISTRADO" : "SOCIO";
            salida.append("-------------------------"
                    + "\nNombre: " + clienteEntity.getNombre()
                    + "\nApellidos: " + clienteEntity.getApellidos()
                    + "\nFecha de alta: " + clienteEntity.getFechaAlta()
                    + "\nTipo: " + tipo);

            if (clienteEntity.getTipo() == 0) {
                salida.append("\nCuota máxima: " + clienteEntity.getCuotaMaxima());
            }

            System.out.println("\u001B[34m" + salida + "\u001B[0m");
        }
    }
}
