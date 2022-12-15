package crud.Vista;

import crud.Controlador.Controlador;
import crud.Excepciones.ClienteTipoException;
import crud.Modelo.ClienteEntity;
import crud.Servicio.ClienteServicio;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.Scanner;

import static crud.Controlador.Controlador.*;

public class Consola {

    private static final Logger LOGGER = LogManager.getLogger(Consola.class);

    public static void insertarClientes() throws ClienteTipoException {
        ClienteEntity clienteEntity = new ClienteEntity();
        ClienteServicio clienteServicio = new ClienteServicio();
        Scanner sc = new Scanner(System.in);

        //Asignamos el identificador del cliente
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Introduzca el DNI/NIE/CIF del cliente:");
        }

        String identificador = sc.nextLine();

        while (!validarIdentificador(identificador)) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("El DNI/NIE/CIF no puede ser nulo, por favor, introdúzcalo de nuevo:");
            }
            identificador = sc.nextLine();
        }

        clienteEntity.setDni(identificador);

        //Asignamos el nombre del cliente
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Introduzca el nombre del cliente:");
        }

        String nombre = sc.nextLine();

        while (nombre.equals("")) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("El nombre no puede ser nulo, por favor, introdúzcalo de nuevo:");
            }
            nombre = sc.nextLine();
        }

        clienteEntity.setNombre(nombre);

        //Asignamos los apellidos del cliente
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Introduzca los apellidos del cliente:");
        }

        String apellidos = sc.nextLine();

        while (apellidos.equals("")) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("Los apellidos no puede ser nulo, por favor, introdúzcalo de nuevo:");
            }
            apellidos = sc.nextLine();
        }

        clienteEntity.setApellidos(apellidos);

        //Asignamos la fecha de alta del cliente
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Introduzca la fecha de alta del cliente (yyyy/MM/dd HHmmss):");
        }

        String fechaAlta = sc.nextLine();

        while(!validarFecha(fechaAlta)){
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

        String tipo = sc.nextLine();

        while (!validarTipo(tipo)) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("El tipo no es válido, por favor, introdúzcalo de nuevo:");
            }
            tipo = sc.nextLine();
        }

        clienteEntity.setTipo(Byte.parseByte(tipo));

        //Asignamos la cuota máxima del cliente cuando sea necesaria
        if (Byte.parseByte(tipo) == 0) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Introduzca la cuota máxima del cliente:");
            }

            String cuota = sc.nextLine();

            while (!validarCuota(cuota)) {
                if (LOGGER.isWarnEnabled()) {
                    LOGGER.warn("La cuota máxima no es válida, por favor, introdúzcala de nuevo:");
                }
                cuota = sc.nextLine();
            }

            BigDecimal cuotaMax = new BigDecimal(cuota);
            clienteEntity.setCuotaMaxima(cuotaMax);
        }

        clienteServicio.insert(clienteEntity);
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
