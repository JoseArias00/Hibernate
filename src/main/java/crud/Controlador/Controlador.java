package crud.Controlador;

import crud.Excepciones.ClienteException;
import crud.Modelo.ClienteEntity;
import crud.Otros.OpcionesOrdenacionCliente;
import crud.Servicio.ClienteServicio;
import crud.Servicio.UtileriaClienteServicio;

import java.util.List;

/**
 * @author Jose Maria
 */
public class Controlador {

    /**
     * @param cliente Cliente a insertar en la base de datos
     * @throws ClienteException     Ocurre cuando el cliente no pasa las validaciones previas a una inserción
     * @throws NullPointerException Ocurre cuando se pasa un parámetro nulo por parámetro
     */
    public static void insertarCliente(final ClienteEntity cliente) throws ClienteException, NullPointerException {
        if (cliente == null) {
            throw new NullPointerException("El cliente pasado por parámetros es nulo");
        }

        ClienteServicio clienteServicio = new ClienteServicio();
        clienteServicio.insertar(cliente);
    }

    /**
     * @return Todos los clientes de la base de datos
     */
    public static List<ClienteEntity> devolverTodos() {
        ClienteServicio clienteServicio = new ClienteServicio();
        List<ClienteEntity> listaClientes = clienteServicio.obtenerTodos();

        return listaClientes;
    }

    /**
     * @param orden El orden por el que se quiere ordenar los clientes recuperados
     * @return Una lista con todos los clientes de la base de datos ordenados según lo pasado por parámetro
     */
    public static List<ClienteEntity> devolverTodosOrdenados(final OpcionesOrdenacionCliente orden) {
        if (orden == null) {
            return null;
        }

        ClienteServicio clienteServicio = new ClienteServicio();

        return clienteServicio.getClientes(orden);
    }

    /**
     * @param DNI El DNI del cliente a consultar en la base de datos
     * @return La lista con todas las instancias del cliente solicitado
     */
    public static List<ClienteEntity> consultarPorDNI(final String DNI) {
        if (DNI == null) {
            return null;
        }

        ClienteServicio clienteServicio = new ClienteServicio();
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setDni(DNI);

        return clienteServicio.obtener(clienteEntity);
    }

    /**
     * @param cliente El cliente a borrar de la base de datos
     */
    public static void borrarCliente(final ClienteEntity cliente) {
        if (cliente == null) {
            throw new NullPointerException("El cliente pasado por parámetro es nulo");
        }

        ClienteServicio clienteServicio = new ClienteServicio();

        clienteServicio.borrarPorPK(cliente.getClienteId());
    }

    /**
     * @param cliente El cliente a editar de la base de datos con los nuevos valores
     * @throws ClienteException Ocurre cuando el cliente no pasa las validaciones previas a una inserción
     */
    public static void editarCliente(final ClienteEntity cliente) throws ClienteException {
        if (cliente == null) {
            throw new NullPointerException("El cliente pasado por parámetro es nulo");
        }

        ClienteServicio clienteServicio = new ClienteServicio();

        if (UtileriaClienteServicio.validarEdicionCliente(cliente)) {
            clienteServicio.editar(cliente);
        }

    }
}
