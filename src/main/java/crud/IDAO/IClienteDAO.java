package crud.IDAO;

import crud.Modelo.ClienteEntity;
import crud.Otros.Enumeraciones.OpcionesOrdenacionCliente;

import java.util.List;

/**
 * Interfaz que extiende de la interfaz IDAO pero que contiene métodos más propios de clientes
 */
public interface IClienteDAO extends IDAO<ClienteEntity, Integer> {

    //Método para listar clientes ordenandolos de una forma concreta
    public List<ClienteEntity> obtenerClientes(OpcionesOrdenacionCliente orden);

    //Método para recuperar una o todas las instancias de un cliente y editarlo
    public void editarCliente(ClienteEntity cliente, boolean todasInstancias);

}
