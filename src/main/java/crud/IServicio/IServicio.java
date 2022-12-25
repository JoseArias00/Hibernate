package crud.IServicio;

import crud.Otros.Excepciones.ClienteException;

import java.util.List;

/**
 * @param <T>  Genérico referido a las entidades
 * @param <PK> Genérico referido a las 'primary keys'
 * @author Jose Maria
 * <p>
 * Interfaz IServicio
 */
public interface IServicio<T, PK> {

    public void insertar(T entity) throws ClienteException;

    public List<T> obtenerTodos();

    public List<T> obtener(T... entity);

    public T obtenerPorPK(PK entityPK);

    public void borrar(T entity);

    public void borrarPorPK(PK entityPK);

    public void editar(T entity) throws ClienteException;
}
