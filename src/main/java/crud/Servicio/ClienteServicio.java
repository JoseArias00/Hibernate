package crud.Servicio;

import crud.DAO.ClienteDAO;
import crud.IServicio.IClienteServicio;
import crud.Modelo.ClienteEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static crud.Servicio.UtileríaClienteServicio.*;

public class ClienteServicio implements IClienteServicio {

    private final Logger logger = LogManager.getLogger(ClienteDAO.class);

    @Override
    public void insertClientes(ClienteEntity... clientes) {
        //TODO comprobar que no se introduzcan dos clientes con exactamente los mismos campos
        //TODO comprobar que no haya dos dni iguales con distintos nombres y apellidos
        ClienteDAO clienteDAO = new ClienteDAO();

        for (ClienteEntity contadorClientes : clientes) {
            validarCampos(contadorClientes);
            if (validarIdentificador(contadorClientes.getDni())) {
                clienteDAO.insertCliente(contadorClientes);
            } else {
                if (logger.isInfoEnabled()) {
                    logger.info("El cliente con DNI : " + contadorClientes.getDni() + " no se ha introducido ya que su DNI no es correcto.");
                }
            }

        }

        if (logger.isInfoEnabled()) {
            logger.info("Añadidos todos los clientes solicitados a la base de datos.");
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
