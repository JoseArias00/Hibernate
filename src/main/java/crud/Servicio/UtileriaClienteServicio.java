package crud.Servicio;

import crud.DAO.ClienteDAO;
import crud.Excepciones.ClienteException;
import crud.Modelo.ClienteEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @author Jose María
 * <p>
 * Utileria creada para la clase ClienteServicio
 */
public class UtileriaClienteServicio {

    private static final Logger LOGGER = LogManager.getLogger(UtileriaClienteServicio.class);

    /**
     * Constructor vacío para evitar la creación de objetos de la utilería
     */
    private UtileriaClienteServicio() {
    }

    /**
     * @param cliente Cliente que queremos validar de forma completa
     * @return Si el cliente es correcto después de ser validado para insertarse en la base de datos
     * @throws ClienteException Ocurre cuando al crear un cliente no pasa las validaciones relacionadas con la base de datos
     */
    public static boolean validarCliente(final ClienteEntity cliente) throws ClienteException {
        if (camposValidos(cliente)) {
            if (!comprobarRepetecionCliente(cliente)) {
                if (!identificadorRepetido(cliente)) {
                    if (comprobarTipo(cliente)) {
                        return true;
                    } else {
                        if (LOGGER.isWarnEnabled()) {
                            LOGGER.warn("No se corresponde el tipo del cliente : " + cliente.getDni() + " con su cuota máxima asignada.");
                        }
                    }
                } else {
                    if (LOGGER.isWarnEnabled()) {
                        LOGGER.warn("Existe ya un cliente distinto con el identificador : " + cliente.getDni() + ".");
                    }
                }
            } else {
                if (LOGGER.isWarnEnabled()) {
                    LOGGER.warn("El cliente con identificador : " + cliente.getDni() + " tiene otra instancia con los mismos datos en la base de datos.");
                }
            }

        } else {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("El cliente con identificador : " + cliente.getDni() + " tiene mínimo un campo inválido.");
            }
        }
        return false;
    }

    /**
     * @param cliente Cliente pasado por parámetro para validar sus campos y comprobar que no de errores
     * @throws NullPointerException Ocurre cuando algún campo del cliente o el propio es null
     */
    public static boolean camposValidos(final ClienteEntity cliente) throws NullPointerException {
        if (cliente.equals(null)) {
            throw new NullPointerException("El cliente pasado por parámetro es nulo.");
        } else {
            if (cliente.getDni().equals(null)) {
                throw new NullPointerException("El DNI del cliente es nulo.");
            }
            if (cliente.getNombre().equals(null)) {
                throw new NullPointerException("El nombre del cliente es nulo.");
            }
            if (cliente.getApellidos().equals(null)) {
                throw new NullPointerException("El apellido del cliente es nulo.");
            }
            if (cliente.getFechaAlta().equals(null)) {
                throw new NullPointerException("La fecha de alta del cliente es nulo.");
            }
            if (!(cliente.getTipo() == 0 || cliente.getTipo() == 1)) {
                throw new NullPointerException("El tipo de cliente es nulo.");
            }
            if (cliente.getTipo() == 0 && cliente.getCuotaMaxima() == null) {
                throw new NullPointerException("La cuota máxima del cliente de tipo registrado es null");
            }
        }
        return true;
    }

    /**
     * @param cliente El cliente que comprobaremos si ya existe otro igual dentro de la base de datos
     * @return True si está repetido, y false si no lo está
     */
    public static boolean comprobarRepetecionCliente(final ClienteEntity cliente) {
        if (camposValidos(cliente)) {
            ClienteDAO clienteDAO = new ClienteDAO();
            List<ClienteEntity> clientesBaseDatos = clienteDAO.findAll();

            for (ClienteEntity contadorClientesBD : clientesBaseDatos) {
                if (contadorClientesBD.getDni().equalsIgnoreCase(cliente.getDni()) &&
                        contadorClientesBD.getNombre().equalsIgnoreCase(cliente.getNombre()) &&
                        contadorClientesBD.getApellidos().equalsIgnoreCase(cliente.getApellidos()) &&
                        contadorClientesBD.getFechaAlta().equals(cliente.getFechaAlta()) &&
                        contadorClientesBD.getTipo() == cliente.getTipo()) {
                    if (cliente.getTipo() == 0 && contadorClientesBD.getTipo() == 0) {
                        if (contadorClientesBD.getCuotaMaxima().equals(cliente.getCuotaMaxima())) {
                            return true;
                        }
                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * @param cliente El cliente del que queremos saber si ya existe otro con ese mismo DNI y distintos nombres o apellidos
     * @return True si está repetido, y false si no lo está
     * @throws NullPointerException Ocurre cuando el cliente pasado por parámetro es nulo
     */
    public static boolean identificadorRepetido(final ClienteEntity cliente) throws NullPointerException {
        if (cliente.equals(null)) {
            throw new NullPointerException("El cliente pasado por parámetro es nulo.");
        }
        if (camposValidos(cliente)) {
            ClienteDAO clienteDAO = new ClienteDAO();
            List<ClienteEntity> clientesBaseDatos = clienteDAO.find(cliente);

            for (ClienteEntity contadorClientes : clientesBaseDatos) {
                if (!contadorClientes.getNombre().equals(cliente.getNombre()) || !contadorClientes.getApellidos().equals(cliente.getApellidos())) {
                    return true;
                }
            }

        }
        return false;
    }

    /**
     * @param cliente El cliente que queremos comprobar su cuota máxima en relacion con el tipo de cliente que es
     * @return Si se corresponde el valor de la cuota con el tipo de cliente que es
     * @throws ClienteException Ocurre cuando se crea un cliente de tipo Socio con una couta maxima, o
     *                              un cliente de tipo registrado sin cuota máxima
     */
    public static boolean comprobarTipo(final ClienteEntity cliente) throws ClienteException {
        if (cliente.getTipo() == 1 && cliente.getCuotaMaxima() != null) {
            throw new ClienteException("No se puede aplicar una cuota máxima a un cliente de tipo Socio");
        }

        if (cliente.getTipo() == 0 && cliente.getCuotaMaxima() == null) {
            throw new ClienteException("Los clientes de tipo Registrado deben tener una cuota máxima asignada.");
        }

        return true;
    }


}
