package crud.DAO;

import crud.IDAO.IClienteDAO;
import crud.Modelo.ClienteEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.List;

public class ClienteDAO implements IClienteDAO, Serializable {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("proconsiHibernate");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();

    private CriteriaBuilder cbuilder = entityManager.getCriteriaBuilder();
    private CriteriaQuery<ClienteEntity> cquery = cbuilder.createQuery(ClienteEntity.class);
    //Creamos el tipo root de querys que referencia a la clase ClientesEntity
    private Root<ClienteEntity> cliente = cquery.from(ClienteEntity.class);

    private Logger logger = LogManager.getLogger(ClienteDAO.class);


    @Override
    public void insertCliente(ClienteEntity cliente) {
        entityManager.getTransaction().begin();
        entityManager.persist(cliente);

        logger.info("Creado cliente con DNI: " + cliente.getDni() + ".");
        entityManager.getTransaction().commit();
    }

    @Override
    public List<ClienteEntity> getClientes() {
        cquery.select(cliente);
        List<ClienteEntity> clientes = entityManager.createQuery(cquery).getResultList();

        entityManager.clear();

        logger.info("Completada la solicitud del listado de todos los clientes.");
        return clientes;
    }

    @Override
    public List<ClienteEntity> getClientes(String orden) {
        cquery.select(cliente).orderBy(cbuilder.asc(cliente.get(orden)));
        List<ClienteEntity> clientes = entityManager.createQuery(cquery).getResultList();

        entityManager.clear();

        logger.info("Completada la solicitud del listado de todos los clientes.");
        return clientes;
    }

    @Override
    public void removeCliente(final String DNI) {
        Predicate condicion = cbuilder.equal(this.cliente.get("dni"), DNI);
        cquery.select(this.cliente).where(condicion);
        List<ClienteEntity> clientes = entityManager.createQuery(cquery).getResultList();

        entityManager.getTransaction().begin();

        for (ClienteEntity contadorCliente : clientes) {
            entityManager.remove(contadorCliente);
        }

        logger.info("El cliente con DNI: " + DNI + ", ha sido borrado.");
        entityManager.getTransaction().commit();

    }

    @Override
    public void removeTodos() {
        cquery.select(this.cliente);
        List<ClienteEntity> clientes = entityManager.createQuery(cquery).getResultList();

        entityManager.getTransaction().begin();

        for (ClienteEntity contadorCliente : clientes) {
            entityManager.remove(contadorCliente);
        }

        logger.info("Todas los clientes de la base de datos han sido borrados.");
        entityManager.getTransaction().commit();
    }

    @Override
    public void setCliente(final ClienteEntity cliente) {
        String campoNombre = "nombre";
        String campoApellidos = "apellidos";
        String campoFechaAlta = "fechaAlta";
        String campoTipo = "tipo";
        String campoCuotaMaxima = "cuotaMaxima";

        Predicate condicion = cbuilder.equal(this.cliente.get("dni"), cliente.getDni());
        CriteriaUpdate<ClienteEntity> update = cbuilder.createCriteriaUpdate(ClienteEntity.class);
        Root root = update.from(ClienteEntity.class);

        entityManager.getTransaction().begin();
        //cquery.select(this.cliente).where(condicion);

        update.set(campoNombre, cliente.getNombre());
        update.set(campoApellidos, cliente.getApellidos());
        update.set(campoFechaAlta, cliente.getFechaAlta());
        update.set(campoTipo, cliente.getTipo());
        if (cliente.getTipo() == 0) {
            update.set(campoCuotaMaxima, cliente.getCuotaMaxima());
        }
        update.where(condicion);
        this.entityManager.createQuery(update).executeUpdate();
        if (logger.isInfoEnabled()) {
            logger.info("El cliente con DNI: " + cliente.getDni() + ", ha sido modificado.");
        }
        entityManager.getTransaction().commit();
    }

}
