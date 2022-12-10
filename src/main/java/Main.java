import crud.DAO.ClienteDAO;
import crud.Modelo.ClienteEntity;
import crud.Servicio.ClienteServicio;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static crud.Servicio.CondicionesClientes.REGISTRADO;
import static crud.Servicio.CondicionesClientes.SOCIO;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);


    public static void main(String[] args) {
        LocalDateTime fecha = LocalDateTime.now();
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setDni("44437583L");
        clienteEntity.setNombre("Michael");
        clienteEntity.setApellidos("Jordan");
        clienteEntity.setFechaAlta(Timestamp.valueOf(fecha));
        clienteEntity.setTipo((byte) REGISTRADO.ordinal());
        clienteEntity.setCuotaMaxima(BigDecimal.valueOf(Double.valueOf("4321.12")));

        ClienteEntity clienteEntity1 = new ClienteEntity();
        clienteEntity1.setDni("44437583L");
        clienteEntity1.setNombre("Michael");
        clienteEntity1.setApellidos("Jordan");
        clienteEntity1.setFechaAlta(Timestamp.valueOf(fecha));
        clienteEntity1.setTipo((byte) SOCIO.ordinal());

       /* ClienteEntity clienteEntity1 = new ClienteEntity();
        clienteEntity1.setDni("44437593L");
        clienteEntity1.setNombre("Kobe");
        clienteEntity1.setApellidos("Bryant");
        clienteEntity1.setFechaAlta(Timestamp.valueOf(fecha));
        clienteEntity1.setTipo((byte) SOCIO.ordinal());*/

        ClienteServicio clienteServicio = new ClienteServicio();
        clienteServicio.insertClientes(clienteEntity,clienteEntity1);

        //clienteDAO.insertCliente(clienteEntity);

        //List<ClienteEntity> lista = clienteDAO.getClientes(OpcionesOrdenacion.dni);

        //clienteDAO.removeCliente(clienteEntity.getDni());

        //clienteDAO.setCliente(clienteEntity,false);

        //ClienteDAO clienteDAO = new ClienteDAO();
        //clienteDAO.removeTodos();

    }
}
