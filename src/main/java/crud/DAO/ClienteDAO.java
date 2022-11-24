package crud.DAO;

import crud.IDAO.IClienteDAO;
import crud.Modelo.ClienteEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public class ClienteDAO implements IClienteDAO, Serializable {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("proconsiHibernate");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public void insertarCliente(ClienteEntity cliente) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(cliente);
        this.entityManager.getTransaction().commit();
    }

    @Override
    public List<ClienteEntity> obtenerClientes() {
        //Inicializamos un criteria builder para las futuruas querys
        CriteriaBuilder cbuilder = entityManager.getCriteriaBuilder();

        //Creamos la query que usaremos luego para recuperar clientes
        CriteriaQuery<ClienteEntity> cquery = cbuilder.createQuery(ClienteEntity.class);

        //Creamos el tipo root de querys que referencia a la clase ClientesEntity
        Root<ClienteEntity> cliente = cquery.from(ClienteEntity.class);

        //Indicamos que la query seleccionará todos los clientes de la clase ClienteEntity que referencia a la tabla Clientes en MySQL
        cquery.select(cliente);

        //Creamos una typedQuery ya que ya conocemos el tipo de dato devuelto por la query para ahorrarnos posibles casteos y la ejecutamos
        TypedQuery<ClienteEntity> tquery = entityManager.createQuery(cquery);

        //Almacenamos los clientes obtenidos en una lista la cual devolverá el método
        List<ClienteEntity> clientes = tquery.getResultList();

        return clientes;
    }

    @Override
    public ClienteEntity obtenerCliente() {
        return null;
    }

    @Override
    public void borrarClientes() {

    }

    @Override
    public void editarCliente() {

    }

    @Override
    public List<ClienteEntity> listarClientes() {
        return null;
    }
}
