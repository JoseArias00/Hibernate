package crud.IDAO;

import java.util.List;

public interface IDAO<T,PK> {

    public void insert(T entity);

    public List<T> find(T entity);

    public T findByPK(PK entityPk);

    public List<T> findAll();

    public void removeAll();

    public void remove(T entity);

    public void removeByPK(PK entityPK);

    public void edit(T entity);

    public int count();
}
