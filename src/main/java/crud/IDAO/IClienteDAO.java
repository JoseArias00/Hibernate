package crud.IDAO;

import crud.Modelo.ClienteEntity;

import java.util.List;

public interface IClienteDAO<T> {

    //Método para añadir clientes
    public void insertCliente(ClienteEntity cliente);

    //Métodos para listar clientes
    public List<ClienteEntity> getClientes();
    public List<ClienteEntity> getClientes(String orden);


    //Métodos para borrar clientes
    public void removeCliente(String DNI);
    public void removeTodos();

    //Método para recuperar un cliente y editarlo
    public void setCliente(ClienteEntity cliente);

}
