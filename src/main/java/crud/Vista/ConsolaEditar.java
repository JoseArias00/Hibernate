package crud.Vista;

import crud.Controlador.Controlador;
import crud.Modelo.ClienteEntity;

import java.util.List;
import java.util.Scanner;

public class ConsolaEditar {

    public static void editarClientes() {
        Scanner sc = new Scanner(System.in);
        List<ClienteEntity> clientes = Controlador.devolverTodos();

        mostrar(clientes);

        System.out.println("Que cliente desea modificar, introduzca su número");

        int numeroCliente = sc.nextInt();

        while (numeroCliente < 0 || numeroCliente > clientes.size()) {
            System.err.println("Por favor,introduzca un número de cliente");

            numeroCliente = sc.nextInt();
        }

        modificar(clientes.get(numeroCliente));
    }

    private static void modificar(ClienteEntity cliente) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Que campo desea modificar:"
                + "\n1.Nombre"
                + "\n2.Apellidos"
                + "\n3.Fecha de alta"
                + "\n4.Tipo"
                + "\n5.Cuota máxima");

        String opcion = sc.nextLine();

        while(!UtileriaValidaciones.validarCampos(opcion)){
            System.err.println("Introduzca el número de un campo válido");

            opcion = sc.nextLine();
        }

    }

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
