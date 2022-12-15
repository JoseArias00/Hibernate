package crud.IDAO;

import crud.Modelo.ClienteEntity;
import crud.Servicio.OpcionesOrdenacionCliente;

import java.util.List;

public interface IClienteDAO extends IDAO<ClienteEntity, Integer> {

    //Método para listar clientes ordenandolos de una forma concreta
    public List<ClienteEntity> getClientes(OpcionesOrdenacionCliente orden);

    //Método para recuperar una o todas las instancias de un cliente y editarlo
    public void editCliente(ClienteEntity cliente, boolean todasInstancias);

}
