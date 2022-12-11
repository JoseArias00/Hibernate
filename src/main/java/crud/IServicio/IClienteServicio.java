package crud.IServicio;

import crud.Modelo.ClienteEntity;

import java.util.List;

public interface IClienteServicio {

    //Método para añadir clientes
    public void insertClientes(final ClienteEntity ... clientes) throws IllegalAccessException;

    //Método para listar todos los clientes
    public List<ClienteEntity> getClientes();

    //Método para consultar todas las instancias de un cliente en la base de datos por su identificador
    public List<ClienteEntity> getCliente(final String identificador);

    //Método para borrar clientes
    public void removeClientes(final ClienteEntity ... clientes);

    //Método para modificar cliente
    public void setClientes(final ClienteEntity ... clientes);
}
