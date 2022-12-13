import crud.DAO.ClienteDAO;
import crud.Excepciones.ClienteTipoException;
import crud.Modelo.ClienteEntity;
import crud.Servicio.ClienteServicio;
import crud.Servicio.OpcionesOrdenacion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static crud.Servicio.CondicionesClientes.REGISTRADO;
import static crud.Servicio.CondicionesClientes.SOCIO;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);


    public static void main(String[] args) throws ClienteTipoException {
        LocalDateTime fecha = LocalDateTime.now();
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setClienteId(40);
        clienteEntity.setDni("44437583L");
        clienteEntity.setNombre("Kobe");
        clienteEntity.setApellidos("Bryant");
        clienteEntity.setFechaAlta(Timestamp.valueOf(fecha));
        clienteEntity.setTipo((byte) SOCIO.ordinal());
        clienteEntity.setCuotaMaxima(null);

        ClienteEntity clienteEntity1 = new ClienteEntity();
        clienteEntity1.setDni("65473882N");
        clienteEntity1.setNombre("Michael");
        clienteEntity1.setApellidos("Jordan");
        clienteEntity1.setFechaAlta(Timestamp.valueOf(fecha));
        clienteEntity1.setTipo((byte) SOCIO.ordinal());

        /*ClienteEntity clienteEntity1 = new ClienteEntity();
        clienteEntity1.setDni("44437593L");
        clienteEntity1.setNombre("Kobe");
        clienteEntity1.setApellidos("Bryant");
        clienteEntity1.setFechaAlta(Timestamp.valueOf(fecha));
        clienteEntity1.setTipo((byte) SOCIO.ordinal());*/

        ClienteServicio clienteServicio = new ClienteServicio();
        //List<ClienteEntity> cliente = clienteServicio.removeClientes(37);
        //clienteServicio.removeClientes("44437583L");

        clienteServicio.setClientes(false,clienteEntity,clienteEntity1);

        //clienteDAO.insertCliente(clienteEntity);

        //List<ClienteEntity> lista = clienteDAO.getClientes(OpcionesOrdenacion.dni);

        //clienteDAO.removeCliente(clienteEntity.getDni());

        //clienteDAO.setCliente(clienteEntity,false);

        //ClienteDAO clienteDAO = new ClienteDAO();
        //clienteDAO.removeTodos();

    }
}
