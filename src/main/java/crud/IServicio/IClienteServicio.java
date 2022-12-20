package crud.IServicio;

import crud.Modelo.ClienteEntity;
import crud.Otros.OpcionesOrdenacionCliente;

import java.util.List;

public interface IClienteServicio extends IServicio<ClienteEntity, Integer>{

    //Método para recuperar todos los clientes ordenados por su fecha de alta o su DNI
    public List<ClienteEntity> getClientes(OpcionesOrdenacionCliente orden);

//    //Método para añadir clientes
//    public void insertClientes(ClienteEntity ... clientes) throws IllegalAccessException, ClienteTipoException;
//
//    //Método para listar todos los clientes
//    public List<ClienteEntity> getClientes();
//
//    //Método para consultar todas las instancias de un cliente en la base de datos por su identificador
//    public List<ClienteEntity> getCliente(String identificador);
//
//    //Método para consultar una instancia concreta de un cliente por su ID
//    public ClienteEntity getCliente(int ID);
//
//    //Método para borrar todas las instancias de un cliente
//    public void removeClientes(String identificador);
//
//    //Método para borrar un cliente por Id
//    public void removeClientes(int Id);

}
