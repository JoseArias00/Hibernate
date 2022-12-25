package crud.Vista;

import crud.Controlador.ControladorValidaciones;

import java.util.Scanner;


/**
 * @author Jose Maria
 */
public class ConsolaPrincipal {

    /**
     * Método inicial que muestra todas la acciones posibles a realizar en la aplicación
     */
    public static void inicio() {
        Scanner sc = new Scanner(System.in);
        String opcion;
        String salir = "6";

        System.out.println("Bienvenido al programa CRUD.");
        System.out.println("Introduzca el comando /exit para volver al menú principal.");

        do {
            System.out.println("Que acción desea realizar: "
                    + "\n1.Añadir cliente"
                    + "\n2.Consultar cliente"
                    + "\n3.Borrar cliente"
                    + "\n4.Editar cliente"
                    + "\n5.Listar clientes"
                    + "\n6.Salir");

            opcion = sc.nextLine();

            while (!ControladorValidaciones.validarAcciones(opcion)) {
                System.err.println("Por favor, seleccione una de las opciones indicadas.");
                opcion = sc.nextLine();
            }

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

        } while (!opcion.equals(salir));

        System.out.println("Tenga un buen día.");
    }

}
