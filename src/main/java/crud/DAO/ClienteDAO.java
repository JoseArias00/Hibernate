package crud.DAO;

import crud.IDAO.IClienteDAO;
import crud.Modelo.ClienteEntity;
import crud.Otros.OpcionesOrdenacionCliente;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Jose María
 * <p>
 * Clase ClienteDAO con los métodos de la interfaz IClienteDAO implementados.
 */
public class ClienteDAO implements IClienteDAO, Serializable {

    /**
     * Variables finales usadas para establecer una conexión con la base de datos
     */
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("proconsiHibernate");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    /**
     * Variables finales inicializadas para realizar consultas con criteria contra la base de datos
     */
    private final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    private final CriteriaQuery<ClienteEntity> criteriaQuery = criteriaBuilder.createQuery(ClienteEntity.class);
    //Creamos el tipo root de querys que referencia a la clase ClientesEntity
    private final Root<ClienteEntity> cliente = criteriaQuery.from(ClienteEntity.class);

    /**
     * Inicializado el logger a usar en la clase
     */
    private static final Logger LOGGER = LogManager.getLogger(ClienteDAO.class);

    /**
     * @param cliente Cliente a insertar en la base de datos
     *                <p>
     *                Método encargado de insertar en la base de datos un cliente pasado por parámetro.
     */
    @Override
    public void insertar(final ClienteEntity cliente) {
        entityManager.getTransaction().begin();
        entityManager.persist(cliente);

        entityManager.getTransaction().commit();

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Creado cliente con identificador: " + cliente.getDni() + ".");
        }
    }

    /**
     * @return Una lista con todos los clientes de la base de datos.
     */
    @Override
    public List<ClienteEntity> buscarTodos() {
        criteriaQuery.select(cliente);
        List<ClienteEntity> clientes = entityManager.createQuery(criteriaQuery).getResultList();

        entityManager.clear();

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Completada la solicitud del listado de todos los clientes.");
        }

        return clientes;
    }

    /**
     * @param orden Indica el campo por el que se realizará la ordenación de la lista devuelta
     * @return Una lista con todos los clientes de la base de datos ordenados.
     */
    @Override
    public List<ClienteEntity> obtenerClientes(final OpcionesOrdenacionCliente orden) {
        criteriaQuery.select(cliente).orderBy(criteriaBuilder.asc(cliente.get(orden.toString())));
        List<ClienteEntity> clientes = entityManager.createQuery(criteriaQuery).getResultList();

        entityManager.clear();

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Completada la solicitud del listado de todos los clientes ordenados por el campo: " + orden);
        }

        return clientes;
    }

    /**
     * @param cliente Cliente que buscaremos en la base de datos por su DNI
     * @return La lista con todas las distintas instancias del cliente indicado.
     */
    @Override
    public List<ClienteEntity> buscar(final ClienteEntity cliente) {
        Predicate condicion = criteriaBuilder.equal(this.cliente.get("dni"), cliente.getDni());
        criteriaQuery.select(this.cliente).where(condicion);
        List<ClienteEntity> clientes = entityManager.createQuery(criteriaQuery).getResultList();

        entityManager.clear();


        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Se han recuperado todas las instancias de la base de datos del cliente con identificador: " + cliente.getDni());
        }

        return clientes;
    }

    /**
     * @param Id Id del cliente que queremos obtener de la base de datos
     * @return La lista con la instancia del cliente seleccionada por su Id
     */
    @Override
    public ClienteEntity buscarPorPK(final Integer Id) {
        Predicate condicion = criteriaBuilder.equal(this.cliente.get("clienteId"), Id.intValue());
        criteriaQuery.select(this.cliente).where(condicion);
        List<ClienteEntity> cliente = entityManager.createQuery(criteriaQuery).getResultList();

        entityManager.clear();

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Se han recuperado todas las instancias de la base de datos del cliente con Id: " + Id);
        }

        return cliente.get(0);
    }

    /**
     * @param cliente Cliente que queremos borrar de la base de datos por su DNI
     *                <p>
     *                Método encargado de borrar todas las instancias del cliente con el DNI indicado de la base de datos.
     */
    @Override
    public void borrar(final ClienteEntity cliente) {
        Predicate condicion = criteriaBuilder.equal(this.cliente.get("dni"), cliente.getClienteId());
        criteriaQuery.select(this.cliente).where(condicion);
        List<ClienteEntity> clientes = entityManager.createQuery(criteriaQuery).getResultList();

        entityManager.getTransaction().begin();

        for (ClienteEntity contadorCliente : clientes) {
            entityManager.remove(contadorCliente);
        }

        entityManager.getTransaction().commit();

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("El cliente con identificador: " + cliente.getClienteId() + ", ha sido borrado.");
        }
    }

    /**
     * @param Id Id del cliente dentro de la base de datos
     *           <p>
     *           Método que borrar un cliente de la base de datos por su Id.
     */
    @Override
    public void borrarPorPK(final Integer Id) {
        Predicate condicion = criteriaBuilder.equal(this.cliente.get("clienteId"), Id.intValue());
        criteriaQuery.select(this.cliente).where(condicion);
        List<ClienteEntity> clientes = entityManager.createQuery(criteriaQuery).getResultList();

        entityManager.getTransaction().begin();

        entityManager.remove(clientes.get(0));

        entityManager.getTransaction().commit();

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("El cliente con Id: " + Id.intValue() + ", ha sido borrado.");
        }
    }

    /**
     * @param cliente Cliente a editar en la base de datos.
     *                <p>
     *                Método encargado de editar un cliente buscandolo mediante su Id.
     */
    @Override
    public void editar(final ClienteEntity cliente) {
        String clienteId = "clienteId";
        Predicate condicion = criteriaBuilder.equal(this.cliente.get(clienteId), cliente.getClienteId());

        entityManager.getTransaction().begin();
        criteriaQuery.select(this.cliente).where(condicion);
        List<ClienteEntity> clientes = entityManager.createQuery(criteriaQuery).getResultList();

        for (ClienteEntity clienteEntity : clientes) {
            cliente.setClienteId(clienteEntity.getClienteId());
            entityManager.merge(cliente);
        }

        entityManager.getTransaction().commit();

        String loggerInfo = "El cliente con Id: " + cliente.getClienteId() + ", ha sido modificado.";

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(loggerInfo);
        }
    }

    /**
     * @return El número total de clientes que tenemos en la base de datos
     */
    @Override
    public int contar() {
        criteriaQuery.select(cliente);
        List<ClienteEntity> clientes = entityManager.createQuery(criteriaQuery).getResultList();

        entityManager.clear();

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Completada la solicitud del listado de todos los clientes para conocer el total de clientes en la base de datos.");
        }

        return clientes.size();
    }

    /**
     * Método encargado de borrar todos los clientes de la base de datos.
     */
    @Override
    public void borrarTodos() {
        criteriaQuery.select(this.cliente);
        List<ClienteEntity> clientes = entityManager.createQuery(criteriaQuery).getResultList();

        entityManager.getTransaction().begin();

        for (ClienteEntity contadorCliente : clientes) {
            entityManager.remove(contadorCliente);
        }

        entityManager.getTransaction().commit();

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Todos los clientes de la base de datos han sido borrados.");
        }
    }


    /**
     * @param cliente         Cliente a modificar
     * @param todasInstancias Parámetro usado para conocer si realizar los cambios en una o todas las instancias de un cliente.
     *                        <p>
     *                        Método encargado de modificar el cliente pasado por parámetro.
     *                        Se puede modificar solo una instancia indicando por parámetro false, o todas, indicando por parámetro true.
     */
    @Override
    public void editarCliente(final ClienteEntity cliente, final boolean todasInstancias) {
        String dni = "dni";
        String clienteId = "clienteId";
        Predicate condicion = (todasInstancias) ?
                criteriaBuilder.equal(this.cliente.get(dni), cliente.getDni()) :
                criteriaBuilder.equal(this.cliente.get(clienteId), cliente.getClienteId());

        entityManager.getTransaction().begin();
        criteriaQuery.select(this.cliente).where(condicion);
        List<ClienteEntity> clientes = entityManager.createQuery(criteriaQuery).getResultList();

        for (ClienteEntity clienteEntity : clientes) {
            cliente.setClienteId(clienteEntity.getClienteId());
            entityManager.merge(cliente);
        }

        entityManager.getTransaction().commit();

        String loggerInfo = (todasInstancias) ?
                "El cliente con identificador: " + cliente.getDni() + ", ha sido modificado." :
                "El cliente con Id: " + cliente.getClienteId() + ", ha sido modificado.";

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(loggerInfo);
        }
    }

}
