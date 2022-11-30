import crud.DAO.ClienteDAO;
import crud.Modelo.ClienteEntity;
import crud.Servicio.CondicionesClientes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static crud.Servicio.CondicionesClientes.REGISTRADO;
import static crud.Servicio.CondicionesClientes.SOCIO;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);


    public static void main(String[] args) {
        logger.info("Prueba de la persistencia");
        LocalDateTime fecha = LocalDateTime.now();
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setDni("1234555L");
        clienteEntity.setNombre("Pepito");
        clienteEntity.setApellidos("Pedro");
        clienteEntity.setFechaAlta(Timestamp.valueOf(fecha));
        clienteEntity.setTipo((byte) REGISTRADO.ordinal());
        ClienteDAO clienteDAO = new ClienteDAO();

        logger.info("Insertamos un cliente de prueba en bbdd");
        clienteDAO.insertCliente(clienteEntity);

        //logger.info("Obtenemos los clientes de la base de datos");
        //List<ClienteEntity> lista = clienteDAO.getClientes("fechaAlta");

        //logger.info("Borramos el cliente de la base de datos");
        //clienteDAO.removeCliente(clienteEntity.getDni());

        //clienteDAO.setCliente(clienteEntity);

        //clienteDAO.removeTodos();
    }
}
