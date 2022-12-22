package crud.IDAO;

import java.util.List;

/**
 * @param <T>  Genérico referido a las entidades
 * @param <PK> Genérico referido a las 'primary keys'
 * @author Jose Maria
 * <p>
 * Interfaz IDAO
 */
public interface IDAO<T, PK> {

    public void insertar(T entity);

    public List<T> buscar(T entity);

    public T buscarPorPK(PK entityPk);

    public List<T> buscarTodos();

    public void borrarTodos();

    public void borrar(T entity);

    public void borrarPorPK(PK entityPK);

    public void editar(T entity);

    public int contar();
}
