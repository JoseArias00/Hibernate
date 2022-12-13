package crud.IDAO;

import crud.Modelo.ClienteEntity;
import crud.Servicio.OpcionesOrdenacion;

import java.util.List;

public interface IClienteDAO {

    //Método para añadir clientes
    public void insertCliente(ClienteEntity cliente);

    //Métodos para listar clientes
    public List<ClienteEntity> getClientes();
    public List<ClienteEntity> getClientes(OpcionesOrdenacion orden);
    public List<ClienteEntity> getCliente(String DNI);
    public ClienteEntity getCliente(int ID);

    //Métodos para borrar clientes
    public void removeCliente(String DNI);
    public void removeCliente(int Id);
    public void removeTodos();

    //Método para recuperar un cliente y editarlo
    public void setCliente(ClienteEntity cliente,boolean todasInstancias);

}
