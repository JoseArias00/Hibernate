import crud.DAO.ClienteDAO;
import crud.Modelo.ClienteEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);


    public static void main(String[] args) {
        logger.info("Prueba de la persistencia");
        ClienteEntity cliente = new ClienteEntity();
        LocalDateTime fecha = LocalDateTime.now();
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setDni("1234589L");
        clienteEntity.setNombre("Ejemplo1");
        clienteEntity.setApellidos("Ejemplo2");
        clienteEntity.setFechaAlta(Timestamp.valueOf(fecha));
        clienteEntity.setTipo((byte) 1);
        ClienteDAO clienteDAO = new ClienteDAO();
        //clienteDAO.insertarCliente(clienteEntity);
        List<ClienteEntity> lista = clienteDAO.obtenerClientes();
        lista.get(0).getDni();
    }
}
