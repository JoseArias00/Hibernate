package crud.Vista;

import crud.Controlador.Controlador;
import crud.Modelo.ClienteEntity;

import java.util.List;
import java.util.Scanner;

public class ConsolaConsultar {

    public static void consultarClientes() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduzca el DNI del cliente que desea consultar: ");
        String DNI = sc.nextLine();
        List<ClienteEntity> clientes = Controlador.consultarPorDNI(DNI);

        while (clientes.isEmpty()) {
            System.err.print("No existe ningún cliente con ese DNI, introduzca otro si lo desea: ");
            clientes = Controlador.consultarPorDNI(sc.nextLine());
        }

        mostrar(clientes);
    }

    private static void mostrar(final List<ClienteEntity> clientes){
        for(ClienteEntity clienteEntity : clientes){
            StringBuilder salida = new StringBuilder();

            String tipo = (clienteEntity.getTipo() == 0) ? "REGISTRADO" : "SOCIO";
            salida.append("-------------------------"
                    + "\nNombre: " + clienteEntity.getNombre()
                    + "\nApellidos: " + clienteEntity.getApellidos()
                    + "\nFecha de alta: " + clienteEntity.getFechaAlta()
                    + "\nTipo: " + tipo);

            if(clienteEntity.getTipo()==0){
                salida.append("\nCuota máxima: " + clienteEntity.getCuotaMaxima());
            }

            System.out.println(salida);
        }
    }
}
