package crud.Servicio;

import static crud.Servicio.UtileriaClienteServicio.*;

import crud.DAO.ClienteDAO;
import crud.Excepciones.ClienteException;
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
     * @param cliente Cliente que deseamos insertar en la base de datos
     * @throws ClienteException Ocurre cuando el cliente no pasa las validaciones pertinentes
     * @see UtileriaClienteServicio El método validar cliente
     */
    @Override
    public void insertar(final ClienteEntity cliente) throws ClienteException {
        ClienteDAO clienteDAO = new ClienteDAO();

        if (validarCliente(cliente)) {
            clienteDAO.insertar(cliente);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Añadido el cliente a la base de datos.");
            }
        } else {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("El cliente no se puede insertar por errores en su validación.");
            }

            throw new ClienteException("El cliente que desea insertar en la base de datos no es válido");
        }
    }

    /**
     * @return Lista con todos los clientes registrados en el momento de la consulta en la base de datos
     */
    @Override
    public List<ClienteEntity> obtenerTodos() {
        ClienteDAO clienteDAO = new ClienteDAO();

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Recuperados todos los clientes.");
        }

        return clienteDAO.buscarTodos();
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


        return clienteDAO.obtenerClientes(orden);
    }

    /**
     * @param cliente El cliente que queremos recuperar de la base de datos
     * @return Una lista con todas las instancias del cliente solicitado
     * @throws NullPointerException Ocurre cuando se pasa un identificador nulo por parámetro
     */
    @Override
    public List<ClienteEntity> obtener(final ClienteEntity... cliente) throws NullPointerException {
        if (cliente == null) {
            throw new NullPointerException("El cliente pasado por parámetro es nulo.");
        }

        ClienteDAO clienteDAO = new ClienteDAO();
        List<ClienteEntity> clientes = new ArrayList<>();

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Recuperados todos los clientes indicados.");
        }

        for (ClienteEntity clienteEntity : cliente) {
            clientes.addAll(clienteDAO.buscar(clienteEntity));
        }

        return clientes;
    }

    /**
     * @param id El Id del cliente que queremos recuperar de la base de datos
     * @return El cliente solicitado por Id
     * @throws NullPointerException Ocurre cuando se pasa un Id nulo por parámetro
     */
    @Override
    public ClienteEntity obtenerPorPK(final Integer id) throws NullPointerException {
        if (id == null) {
            throw new NullPointerException("El Id pasado por parámetro es nulo.");
        }

        ClienteDAO clienteDAO = new ClienteDAO();

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Recuperados todos los clientes indicados.");
        }

        return clienteDAO.buscarPorPK(id);
    }

    /**
     * @param cliente Los clientes de los cuales queremos borrar todas las instancias de la base de datos
     * @throws NullPointerException Ocurre cuando se pasa un identificador nulo por parámetro
     */
    @Override
    public void borrar(final ClienteEntity cliente) throws NullPointerException {
        if (cliente == null) {
            throw new NullPointerException("El identificador pasado por parámetro es nulo.");
        }

        ClienteDAO clienteDAO = new ClienteDAO();

        clienteDAO.borrar(cliente);

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Borrados todos los clientes indicados.");
        }
    }

    /**
     * @param id El Id de la instancia del cliente que queremos borrar en concreto
     * @throws NullPointerException Ocurre cuando se pasa un Id nulo por parámetro
     */
    @Override
    public void borrarPorPK(final Integer id) throws NullPointerException {
        if (id == null) {
            throw new NullPointerException("El identificador pasado por parámetro es nulo.");
        }

        ClienteDAO clienteDAO = new ClienteDAO();

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Borrados todos los clientes indicados.");
        }

        clienteDAO.borrarPorPK(id);
    }

    /**
     * @param cliente Los clientes que queremos modificar de la base de datos
     * @throws ClienteException Ocurre cuando el cliente no pasa las validaciones pertinentes
     */
    @Override
    public void editar(final ClienteEntity cliente) throws ClienteException {
        ClienteDAO clienteDAO = new ClienteDAO();

        if (validarCliente(cliente)) {
            clienteDAO.editar(cliente);
        }

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Modificados todos los clientes indicados.");
        }
    }

}
