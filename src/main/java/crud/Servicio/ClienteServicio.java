package crud.Servicio;

import static crud.Servicio.UtileriaClienteServicio.*;

import crud.DAO.ClienteDAO;
import crud.Excepciones.ClienteTipoException;
import crud.IServicio.IClienteServicio;
import crud.Modelo.ClienteEntity;

import crud.Otros.OpcionesOrdenacionCliente;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jose María
 */
public class ClienteServicio implements IClienteServicio {

    private static final Logger LOGGER = LogManager.getLogger(ClienteDAO.class);

    /**
     * @param cliente Los clientes que queremos añadir a la base de datos
     *                <p>
     *                Método que comprueba los campos y sus valores antes de añadir los clientes a la base de datos.
     */
    @Override
    public void insert(final ClienteEntity cliente) throws ClienteTipoException {
        ClienteDAO clienteDAO = new ClienteDAO();

        if (validarCliente(cliente)) {
            clienteDAO.insert(cliente);
        }

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Añadidos todos los clientes correctos a la base de datos.");
        }
    }

    /**
     * @return Lista con todos los clientes registrados en el momento de la consulta en la base de datos
     */
    @Override
    public List<ClienteEntity> getAll() {
        ClienteDAO clienteDAO = new ClienteDAO();

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Recuperados todos los clientes.");
        }

        return clienteDAO.findAll();
    }

    /**
     * @param orden El orden por el que se ordenará la lista de clientes
     * @return La lista de clientes ordenadas como se pasa por parámetro
     * @throws NullPointerException Ocurre cuando se pasa un orden nulo por parámetro
     * @see OpcionesOrdenacionCliente Las opciones válidas de orden
     */
    @Override
    public List<ClienteEntity> getClientes(final OpcionesOrdenacionCliente orden) throws NullPointerException {
        if (orden == null) {
            throw new NullPointerException("El orden pasado por parámetro es nulo.");
        }

        ClienteDAO clienteDAO = new ClienteDAO();

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Recuperados todos los clientes con el órden indicado.");
        }


        return clienteDAO.getClientes(orden);
    }

    /**
     * @param cliente El cliente que queremos recuperar de la base de datos
     * @return Una lista con todas las instancias del cliente solicitado
     * @throws NullPointerException Ocurre cuando se pasa un identificador nulo por parámetro
     */
    @Override
    public List<ClienteEntity> get(final ClienteEntity... cliente) throws NullPointerException {
        if (cliente == null) {
            throw new NullPointerException("El cliente pasado por parámetro es nulo.");
        }

        ClienteDAO clienteDAO = new ClienteDAO();
        List<ClienteEntity> clientes = new ArrayList<>();

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Recuperados todos los clientes indicados.");
        }

        for (ClienteEntity clienteEntity : cliente) {
            clientes.addAll(clienteDAO.find(clienteEntity));
        }

        return clientes;
    }

    /**
     * @param id El Id del cliente que queremos recuperar de la base de datos
     * @return El cliente solicitado por Id
     * @throws NullPointerException Ocurre cuando se pasa un Id nulo por parámetro
     */
    @Override
    public ClienteEntity getByPK(final Integer id) throws NullPointerException {
        if (id == null) {
            throw new NullPointerException("El Id pasado por parámetro es nulo.");
        }

        ClienteDAO clienteDAO = new ClienteDAO();

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Recuperados todos los clientes indicados.");
        }

        return clienteDAO.findByPK(id);
    }

    /**
     * @param cliente Los clientes de los cuales queremos borrar todas las instancias de la base de datos
     * @throws NullPointerException Ocurre cuando se pasa un identificador nulo por parámetro
     */
    @Override
    public void remove(final ClienteEntity cliente) throws NullPointerException {
        if (cliente == null) {
            throw new NullPointerException("El identificador pasado por parámetro es nulo.");
        }

        ClienteDAO clienteDAO = new ClienteDAO();

        clienteDAO.remove(cliente);


        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Borrados todos los clientes indicados.");
        }
    }

    /**
     * @param id El Id de la instancia del cliente que queremos borrar en concreto
     * @throws NullPointerException Ocurre cuando se pasa un Id nulo por parámetro
     */
    @Override
    public void removeByPK(final Integer id) throws NullPointerException {
        if (id == null) {
            throw new NullPointerException("El identificador pasado por parámetro es nulo.");
        }

        ClienteDAO clienteDAO = new ClienteDAO();

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Borrados todos los clientes indicados.");
        }

        clienteDAO.removeByPK(id);
    }

    /**
     * @param cliente Los clientes que queremos modificar de la base de datos
     */
    @Override
    public void edit(final ClienteEntity cliente) throws ClienteTipoException {
        ClienteDAO clienteDAO = new ClienteDAO();

        if (validarCliente(cliente)) {
            clienteDAO.edit(cliente);
        }

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Modificados todos los clientes indicados.");
        }
    }

}
