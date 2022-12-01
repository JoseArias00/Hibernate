package crud.IDAO;

import crud.Modelo.ClienteEntity;
import crud.Servicio.OpcionesOrdenacion;

import java.util.List;

public interface IClienteDAO {

    //Método para añadir clientes
    public void insertCliente(final ClienteEntity cliente);

    //Métodos para listar clientes
    public List<ClienteEntity> getClientes();
    public List<ClienteEntity> getClientes(final OpcionesOrdenacion orden);
    public List<ClienteEntity> getCliente(final String DNI);

    //Métodos para borrar clientes
    public void removeCliente(final String DNI);
    public void removeTodos();

    //Método para recuperar un cliente y editarlo
    public void setCliente(final ClienteEntity cliente,final boolean todasInstancias);

}
