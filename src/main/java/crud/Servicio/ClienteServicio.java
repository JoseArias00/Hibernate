package crud.Servicio;

import crud.DAO.ClienteDAO;
import crud.IServicio.IClienteServicio;
import crud.Modelo.ClienteEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static crud.Servicio.UtileríaClienteServicio.*;

public class ClienteServicio implements IClienteServicio {

    private static final Logger LOGGER = LogManager.getLogger(ClienteDAO.class);

    @Override
    public void insertClientes(final ClienteEntity... clientes) {
        ClienteDAO clienteDAO = new ClienteDAO();

        for (ClienteEntity contadorClientes : clientes) {
            if (camposValidos(contadorClientes)) {
                if (validarIdentificador(contadorClientes.getDni())) {
                    if (!comprobarRepetecionCliente(contadorClientes)) {
                        LOGGER.info("Cliente no repetido");
                        if (!dniRepetido(contadorClientes)) {
                            LOGGER.debug("Cliente con DNI distinto");
                            clienteDAO.insertCliente(contadorClientes);
                        }
                    }
                } else {
                    if (LOGGER.isInfoEnabled()) {
                        LOGGER.info("El cliente con DNI : " + contadorClientes.getDni() + " no se ha introducido ya que su DNI no es correcto.");
                    }
                }
            }
        }

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Añadidos todos los clientes correctos a la base de datos.");
        }
    }

    @Override
    public List<ClienteEntity> getClientes() {
        //TODO
        return null;
    }

    @Override
    public void removeClientes(ClienteEntity... clientes) {
        //TODO
    }

    @Override
    public void setClientes(ClienteEntity... clientes) {
        //TODO
    }

}
