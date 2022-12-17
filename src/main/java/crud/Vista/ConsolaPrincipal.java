package crud.Vista;

import crud.Excepciones.ClienteTipoException;

import java.util.Scanner;

import static crud.Vista.UtileriaValidaciones.validarAccion;

public class ConsolaPrincipal {

    public static void inicio() throws ClienteTipoException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Bienvenido al programa CRUD.");
        System.out.println("Que acción desea realizar: "
                + "\n1.Añadir cliente"
                + "\n2.Consultar cliente"
                + "\n3.Borrar cliente"
                + "\n4.Editar cliente"
                + "\n5.Listar clientes"
                + "\n6.Salir");

        String opcion = sc.nextLine();

        if (validarAccion(opcion)) {
            switch (opcion) {
                case "1":
                    ConsolaInsertar.insertarClientes();
                    break;
                case "2":
                    ConsolaConsultar.consultarClientes();
                    break;
                case "3":
                    ConsolaBorrar.borrarClientes();
                    break;
                case "4":
                    ConsolaEditar.editarClientes();
                    break;
                case "5":
                    ConsolaListar.listarClientes();
                default:
            }
        }

        System.out.println("Tenga un buen día.");
    }


}
