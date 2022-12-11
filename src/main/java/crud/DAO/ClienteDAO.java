package crud.DAO;

import crud.IDAO.IClienteDAO;
import crud.Modelo.ClienteEntity;
import crud.Servicio.OpcionesOrdenacion;
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
    public void insertCliente(final ClienteEntity cliente) {
        entityManager.getTransaction().begin();
        entityManager.persist(cliente);

        entityManager.getTransaction().commit();

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Creado cliente con DNI: " + cliente.getDni() + ".");
        }
    }

    /**
     * @return Una lista con todos los clientes de la base de datos.
     */
    @Override
    public List<ClienteEntity> getClientes() {
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
    public List<ClienteEntity> getClientes(final OpcionesOrdenacion orden) {
        criteriaQuery.select(cliente).orderBy(criteriaBuilder.asc(cliente.get(orden.toString())));
        List<ClienteEntity> clientes = entityManager.createQuery(criteriaQuery).getResultList();

        entityManager.clear();

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Completada la solicitud del listado de todos los clientes ordenados por el campo: " + orden);
        }

        return clientes;
    }

    /**
     * @param DNI DNI del cliente que queremos obtener de la base de datos
     * @return La lista con todas las distintas instancias del cliente indicado.
     */
    @Override
    public List<ClienteEntity> getCliente(final String DNI) {
        Predicate condicion = criteriaBuilder.equal(this.cliente.get("dni"), DNI);
        criteriaQuery.select(this.cliente).where(condicion);
        List<ClienteEntity> clientes = entityManager.createQuery(criteriaQuery).getResultList();

        entityManager.clear();

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Se han recuperado todas las instancias de la base de datos del cliente con DNI: " + DNI);
        }

        return clientes;
    }

    /**
     * @param DNI DNI del cliente que queremos borrar de la base de datos
     *            <p>
     *            Método encargado de borrar todas las instancias del cliente con el DNI indicado de la base de datos.
     */
    @Override
    public void removeCliente(final String DNI) {
        Predicate condicion = criteriaBuilder.equal(this.cliente.get("dni"), DNI);
        criteriaQuery.select(this.cliente).where(condicion);
        List<ClienteEntity> clientes = entityManager.createQuery(criteriaQuery).getResultList();

        entityManager.getTransaction().begin();

        for (ClienteEntity contadorCliente : clientes) {
            entityManager.remove(contadorCliente);
        }

        entityManager.getTransaction().commit();

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("El cliente con DNI: " + DNI + ", ha sido borrado.");
        }
    }

    /**
     * Método encargado de borrar todos los clientes de la base de datos.
     */
    @Override
    public void removeTodos() {
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
    public void setCliente(final ClienteEntity cliente, final boolean todasInstancias) {
        String dni = "dni";
        String clienteId = "clienteId";
        Predicate condicion = (todasInstancias) ?
                criteriaBuilder.equal(this.cliente.get(dni), cliente.getDni()) :
                criteriaBuilder.equal(this.cliente.get(clienteId), cliente.getClienteId());

        entityManager.getTransaction().begin();
        criteriaQuery.select(this.cliente).where(condicion);
        List<ClienteEntity> clientes = entityManager.createQuery(criteriaQuery).getResultList();

        for (int i = 0; i < clientes.size(); i++) {
            cliente.setClienteId(clientes.get(i).getClienteId());
            entityManager.merge(cliente);
        }

        entityManager.getTransaction().commit();

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("El cliente con DNI: " + cliente.getDni() + ", ha sido modificado.");
        }
    }

}
