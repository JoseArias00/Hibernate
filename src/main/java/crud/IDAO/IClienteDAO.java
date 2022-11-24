package crud.IDAO;

import crud.Modelo.ClienteEntity;

import java.util.List;

public interface IClienteDAO {

    //Método para añadir clientes
    public void insertarCliente(ClienteEntity cliente);

    //Métodos para listar clientes
    public List<ClienteEntity> obtenerClientes();
    public ClienteEntity obtenerCliente();

    //Métodos para borrar clientes
    public void borrarClientes();

    //Método para recuperar un cliente y editarlo
    public void editarCliente();

    //Método para listar todos los clientes
    public List<ClienteEntity> listarClientes();
}
