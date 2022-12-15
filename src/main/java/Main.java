import crud.Controlador.Controlador;
import crud.Excepciones.ClienteTipoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

import static crud.Vista.Consola.*;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws ClienteTipoException {
        Scanner sc = new Scanner(System.in);

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Bienvenido al programa CRUD.");
            LOGGER.info("Que acción desea realizar: "
                    + "\n1.Añadir clientes"
                    + "\n2.Consultar clientes"
                    + "\n3.Borrar clientes"
                    + "\n4.Editar clientes"
                    + "\n5.Listar clientes"
                    + "\n6.Salir");
        }

        String opcion = sc.nextLine();

        if (Controlador.validarAccion(opcion)) {
            switch (opcion) {
                case "1":
                    insertarClientes();
                    break;
                case "2":
                    consultarClientes();
                    break;
                case "3":
                    borrarClientes();
                    break;
                case "4":
                    editarClientes();
                    break;
                case "5":
                    listarClientes();
                default:
            }
        }

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Tenga un buen día.");
        }
    }
}
