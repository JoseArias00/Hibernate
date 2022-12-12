package crud.Servicio;

import crud.DAO.ClienteDAO;
import crud.IServicio.IClienteServicio;
import crud.Modelo.ClienteEntity;
import javafx.scene.layout.CornerRadii;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static crud.Servicio.UtileríaClienteServicio.*;

public class ClienteServicio implements IClienteServicio {

    private static final Logger LOGGER = LogManager.getLogger(ClienteDAO.class);

    /**
     * @param clientes Los clientes que queremos añadir a la base de datos
     *                 <p>
     *                 Método que comprueba los campos y sus valores antes de añadir los clientes a la base de datos.
     */
    @Override
    public void insertClientes(final ClienteEntity... clientes) {
        ClienteDAO clienteDAO = new ClienteDAO();

        for (ClienteEntity contadorClientes : clientes) {
            if (camposValidos(contadorClientes)) {
                if (validarIdentificador(contadorClientes.getDni())) {
                    if (!comprobarRepetecionCliente(contadorClientes)) {
                        LOGGER.info("Cliente no repetido");
                        if (!identificadorRepetido(contadorClientes)) {
                            LOGGER.debug("Cliente con identificador distinto");
                            clienteDAO.insertCliente(contadorClientes);
                        }
                    }
                } else {
                    if (LOGGER.isInfoEnabled()) {
                        LOGGER.info("El cliente con identificador : " + contadorClientes.getDni() + " no se ha introducido ya que su DNI no es correcto.");
                    }
                }
            }
        }

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Añadidos todos los clientes correctos a la base de datos.");
        }
    }

    /**
     * @return Lista con todos los clientes registrados en el momento de la consulta en la base de datos
     */
    @Override
    public List<ClienteEntity> getClientes() {
        ClienteDAO clienteDAO = new ClienteDAO();

        return clienteDAO.getClientes();
    }

    /**
     * @param orden El orden por el que se ordenará la lista de clientes
     * @return La lista de clientes ordenadas como se pasa por parámetro
     * @throws NullPointerException Ocurre cuando se pasa un orden nulo por parámetro
     * @see OpcionesOrdenacion Las opciones válidas de orden
     */
    @Override
    public List<ClienteEntity> getClientes(final OpcionesOrdenacion orden) throws NullPointerException {
        if (orden == null) {
            throw new NullPointerException("El orden pasado por parámetro es nulo.");
        }

        ClienteDAO clienteDAO = new ClienteDAO();

        return clienteDAO.getClientes(orden);
    }

    /**
     * @param identificador El identificador del cliente que queremos recuperar de la base de datos
     * @return Una lista con todas las instancias del cliente solicitado
     * @throws NullPointerException Ocurre cuando se pasa un identificador nulo por parámetro
     */
    @Override
    public List<ClienteEntity> getCliente(final String identificador) throws NullPointerException {
        if (identificador == null) {
            throw new NullPointerException("El identificador pasado por parámetro es nulo.");
        }

        ClienteDAO clienteDAO = new ClienteDAO();

        return clienteDAO.getCliente(identificador);
    }

    /**
     * @param Id El identificador del cliente que queremos recuperar de la base de datos
     * @return Una lista con todas las instancias del cliente solicitado
     * @throws NullPointerException Ocurre cuando se pasa un identificador nulo por parámetro
     */
    @Override
    public ClienteEntity getCliente(int Id) throws NullPointerException {
        String numero = String.valueOf(Id);
        if (numero == null) {
            throw new NullPointerException("El Id pasado por parámetro es nulo.");
        }

        ClienteDAO clienteDAO = new ClienteDAO();

        return clienteDAO.getCliente(Id);
    }

    @Override
    public void removeClientes(String DNI) {

    }

    @Override
    public void removeClientes(int Id) {

    }


    @Override
    public void setClientes(ClienteEntity... clientes) {
        //TODO
    }

}
