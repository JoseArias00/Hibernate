package crud.IServicio;

import crud.Excepciones.ClienteTipoException;

import java.util.List;

public interface IServicio<T,PK> {

    public void insert(T entity) throws ClienteTipoException;

    public List<T> getAll();

    public List<T> get(T... entity);

    public T getByPK(PK entityPK);

    public void remove(T entity);

    public void removeByPK(PK entityPK);

    public void edit(T entity) throws ClienteTipoException;
}
