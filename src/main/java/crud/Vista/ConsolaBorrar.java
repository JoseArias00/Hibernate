package crud.Vista;

import crud.Controlador.Controlador;
import crud.Modelo.ClienteEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import static crud.Controlador.Controlador.consultarPorDNI;
import static crud.Vista.UtileriaValidaciones.*;

public class ConsolaBorrar {

    public static void borrarClientes() {
        Scanner sc = new Scanner(System.in);
        HashMap<Integer, ClienteEntity> mapClientes = new HashMap<>();

        System.out.print("Introduzca el DNI del cliente que desea consultar: ");
        String DNI = sc.nextLine();
        List<ClienteEntity> clientes = consultarPorDNI(DNI);

        while (clientes.isEmpty()) {
            System.err.print("No existe ningún cliente con ese DNI, introduzca otro si lo desea: ");
            clientes = consultarPorDNI(sc.nextLine());
        }

        for (int numero = 0; numero < clientes.size(); numero++) {
            mapClientes.put(numero + 1, clientes.get(numero));
        }

        mostrar(clientes);

        System.out.println("Desea borrar:"
                + "\n1.Una instancia"
                + "\n2.Todas las instancias");

        String opcion = sc.nextLine();

        while (!validarAccionBorrar(opcion)) {
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

    private static void borrarUnaInstancia(final HashMap<Integer, ClienteEntity> clientes) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca el número de la instancia que desea borrar: ");
        int numero = sc.nextInt();

        while (numero < 0 || numero > clientes.size()) {
            System.err.println("Introduzca una opción válida.");

            numero = sc.nextInt();
        }

        Controlador.borrarCliente(clientes.get(numero));

        System.out.println("Cliente con DNI: " + clientes.get(numero).getDni() + " ha sido borrado con éxito.");
    }

    private static void borrarTodas(final List<ClienteEntity> clientes) {
        for (ClienteEntity cliente : clientes) {
            Controlador.borrarCliente(cliente);
        }

        System.out.println("Han sido borradas con éxitos todas las instancias del cliente con DNI: " + clientes.get(0).getDni());
    }

    private static void mostrar(final List<ClienteEntity> clientes) {
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

            System.out.println("\u001B[30m" + salida + "\u001B[0m");
        }
    }
}
