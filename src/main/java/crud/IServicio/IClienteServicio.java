package crud.IServicio;

import crud.Modelo.ClienteEntity;
import crud.Servicio.OpcionesOrdenacion;

import java.util.List;

public interface IClienteServicio {

    //Método para añadir clientes
    public void insertClientes(final ClienteEntity ... clientes) throws IllegalAccessException;

    //Método para listar todos los clientes
    public List<ClienteEntity> getClientes();

    //Metodo para recuperar todos los clientes ordenados por su fecha de alta o su DNI
    public List<ClienteEntity> getClientes(final OpcionesOrdenacion orden);

    //Método para consultar todas las instancias de un cliente en la base de datos por su identificador
    public List<ClienteEntity> getCliente(final String identificador);

    //Método para consultar una instancia concreta de un cliente por su ID
    public ClienteEntity getCliente(final int ID);

    //Método para borrar todas las instancias de un cliente
    public void removeClientes(final String DNI);

    //Método para borrar un cliente por Id
    public void removeClientes(final int Id);

    //Método para modificar cliente
    public void setClientes(final ClienteEntity ... clientes);
}
