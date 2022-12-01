import crud.DAO.ClienteDAO;
import crud.Modelo.ClienteEntity;
import crud.Servicio.ClienteServicio;
import crud.Servicio.CondicionesClientes;
import crud.Servicio.OpcionesOrdenacion;
import crud.Servicio.UtileríaClienteServicio;
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
        clienteEntity.setDni("44437583L");
        clienteEntity.setNombre("Michael");
        clienteEntity.setApellidos("Jordan");
        clienteEntity.setFechaAlta(Timestamp.valueOf(fecha));
        clienteEntity.setTipo((byte) SOCIO.ordinal());

        ClienteEntity clienteEntity1 = new ClienteEntity();
        clienteEntity1.setDni("44437593L");
        clienteEntity1.setNombre("Kobe");
        clienteEntity1.setApellidos("Bryant");
        clienteEntity1.setFechaAlta(Timestamp.valueOf(fecha));
        clienteEntity1.setTipo((byte) SOCIO.ordinal());

        ClienteServicio clienteServicio = new ClienteServicio();
        clienteServicio.insertClientes(clienteEntity,clienteEntity1);

        //clienteDAO.insertCliente(clienteEntity);

        //List<ClienteEntity> lista = clienteDAO.getClientes(OpcionesOrdenacion.dni);

        //clienteDAO.removeCliente(clienteEntity.getDni());

        //clienteDAO.setCliente(clienteEntity,false);

        //clienteDAO.removeTodos();

    }
}
