package crud.Vista;

import crud.Controlador.Controlador;
import crud.Modelo.ClienteEntity;
import crud.Servicio.ClienteServicio;

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

        System.out.println("Desea borrar"
                + "\n1.Una instancia"
                + "\n2.Varias instancias"
                + "\n3.Todas las instancias");

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
                borrarVariasInstancias(mapClientes);
                break;
            case "3":
                borrarTodas(clientes);
            default:
        }

    }

    private static void borrarUnaInstancia(HashMap<Integer, ClienteEntity> clientes) {
        //TODO comprobar entrada diferente rango
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca el número de la instancia que desea borrar: ");
        int numero = sc.nextInt();

        while (numero > 0 && numero < clientes.size()) {
            System.err.println("Introduzca una opción válida.");

            numero = sc.nextInt();
        }

        Controlador.borrarCliente(clientes.get(numero));
    }

    private static void borrarVariasInstancias(HashMap<Integer, ClienteEntity> clientes) {
        //TODO
        Scanner sc = new Scanner(System.in);
        ClienteServicio clienteServicio = new ClienteServicio();
        System.out.println("Introduzca los números de las instancia que desea borrar separados por comas: ");
        int numero = sc.nextInt();

        while (numero > 0 && numero < clientes.size()) {
            System.err.println("Introduzca una opción válida.");

            numero = sc.nextInt();
        }

        clienteServicio.removeByPK(clientes.get(numero).getClienteId());
    }

    private static void borrarTodas(List<ClienteEntity> clientes) {

    }

    private static void mostrar(final List<ClienteEntity> clientes) {
        for (int i = 0; i < clientes.size(); i++) {
            StringBuilder salida = new StringBuilder();

            String tipo = (clientes.get(i).getTipo() == 0) ? "REGISTRADO" : "SOCIO";
            salida.append("-------------------------"
                    + "\nInstancia número " + (i+1)
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
