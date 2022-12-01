import crud.DAO.ClienteDAO;
import crud.Modelo.ClienteEntity;
import crud.Servicio.CondicionesClientes;
import crud.Servicio.OpcionesOrdenacion;
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
        clienteEntity.setClienteId(21);
        clienteEntity.setDni("1114589L");
        clienteEntity.setNombre("Mario");
        clienteEntity.setApellidos("Pedro");
        clienteEntity.setFechaAlta(Timestamp.valueOf(fecha));
        clienteEntity.setTipo((byte) REGISTRADO.ordinal());
        ClienteDAO clienteDAO = new ClienteDAO();

        //clienteDAO.insertCliente(clienteEntity);

        //List<ClienteEntity> lista = clienteDAO.getClientes(OpcionesOrdenacion.dni);

        //clienteDAO.removeCliente(clienteEntity.getDni());

        clienteDAO.setCliente(clienteEntity,false);

        //clienteDAO.removeTodos();
    }
}
