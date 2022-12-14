package crud.Controlador;

import crud.Modelo.ClienteEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Scanner;

import static crud.Controlador.UtileríaControlador.*;

public class ClienteControlador {

    private static final Logger LOGGER = LogManager.getLogger(ClienteControlador.class);

    public static void insertarClientes() {
        //TODO validar las entradas que sean distintas de vacío
        ClienteEntity clienteEntity = new ClienteEntity();
        Scanner sc = new Scanner(System.in);

        //Asignamos el identificador del cliente
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Introduzca el DNI/NIE/CIF del cliente:");
        }

        String identificador = sc.nextLine();

        while(identificador.equals("")){
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("El DNI/NIE/CIF no puede ser nulo, porfavor introduzcalo de nuevo:");
            }
            identificador = sc.nextLine();
        }

        clienteEntity.setDni(identificador);

        //Asignamos el nombre del cliente
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Introduzca el nombre del cliente:");
        }

        String nombre = sc.nextLine();
        if(nombre != null){
            clienteEntity.setNombre(nombre);
        }

        //Asignamos los apellidos del cliente
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Introduzca los apellidos del cliente:");
        }

        String apellidos = sc.nextLine();
        if(apellidos != null){
            clienteEntity.setApellidos(apellidos);
        }

        //Asignamos la fecha de alta del cliente
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Introduzca la fecha de alta del cliente (yyyy/MM/dd HHmmss):");
        }

        String fechaAlta = sc.nextLine();

        while (devolverFechaAlta(fechaAlta) == null) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Introduzca la fecha de alta del cliente (yyyy/MM/dd HHmmss):");
            }
            fechaAlta = sc.nextLine();
        }

        clienteEntity.setFechaAlta(devolverFechaAlta(fechaAlta));

        //Asignamos el tipo de cliente
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Introduzca el tipo del cliente:"
                    + "\n0.Registrado"
                    + "\n1.Socio");
        }

    }

    public static void consultarClientes() {

    }

    public static void borrarClientes() {

    }

    public static void editarClientes() {

    }

    public static void listarClientes() {

    }
}