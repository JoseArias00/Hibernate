package crud.Controlador;

import crud.Excepciones.ClienteException;
import crud.Modelo.ClienteEntity;
import crud.Otros.OpcionesOrdenacionCliente;
import crud.Servicio.ClienteServicio;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Controlador {

    private static final Logger LOGGER = LogManager.getLogger(Controlador.class);

    public static void insertarCliente(final ClienteEntity cliente) throws ClienteException, NullPointerException {
        if(cliente == null){
            throw new NullPointerException("El cliente pasado por parámetros es nulo");
        }

        ClienteServicio clienteServicio = new ClienteServicio();
        clienteServicio.insert(cliente);
    }

    public static List<ClienteEntity> devolverTodos(){
        ClienteServicio clienteServicio = new ClienteServicio();
        List<ClienteEntity> listaClientes = clienteServicio.getAll();

        return listaClientes;
    }

    public static List<ClienteEntity> devolverTodosOrdenados(final OpcionesOrdenacionCliente orden){
        if(orden == null){
            return null;
        }

        ClienteServicio clienteServicio = new ClienteServicio();

        return clienteServicio.getClientes(orden);
    }

    public static List<ClienteEntity> consultarPorDNI(final String DNI){
        if(DNI == null){
            return null;
        }

        ClienteServicio clienteServicio = new ClienteServicio();
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setDni(DNI);

        return clienteServicio.get(clienteEntity);
    }

    public static void borrarCliente(final ClienteEntity cliente){
        if(cliente == null){
            throw new NullPointerException("El cliente pasado por parámetro es nulo");
        }

        ClienteServicio clienteServicio = new ClienteServicio();

        clienteServicio.removeByPK(cliente.getClienteId());
    }

}
