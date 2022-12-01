package crud.IServicio;

import crud.Modelo.ClienteEntity;

import java.util.List;

public interface IClienteServicio {

    //Método para añadir clientes
    public void insertClientes(final ClienteEntity ... clientes) throws IllegalAccessException;

    //Método para listar clientes
    public List<ClienteEntity> getClientes();

    //Método para borrar clientes
    public void removeClientes(final ClienteEntity ... clientes);

    //Método para modificar cliente
    public void setClientes(final ClienteEntity ... clientes);
}
