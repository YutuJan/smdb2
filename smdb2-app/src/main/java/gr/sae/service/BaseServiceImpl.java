package gr.sae.service;

import gr.sae.base.AbstractLogComponent;
import gr.sae.domain.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class BaseServiceImpl<T extends BaseModel> extends AbstractLogComponent
        implements BaseService<T, Long> {

    abstract JpaRepository<T, Long> getRepository();

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,
            rollbackFor = Exception.class)
    public T create(T entity) {
        return getRepository().save(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,
            rollbackFor = Exception.class)
    public List<T> createAll(T... entities) {
        return createAll(Arrays.asList(entities));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,
            rollbackFor = Exception.class)
    public List<T> createAll(List<T> entities) {
        return getRepository().saveAll(entities);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,
            rollbackFor = Exception.class)
    public void update(T entity) {
        getRepository().save(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,
            rollbackFor = Exception.class)
    public void delete(T entity) {
        getRepository().delete(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,
            rollbackFor = Exception.class)
    public void deleteById(Long id) {
        getRepository().deleteById(id);
    }

    @Override
    public boolean exists(T entity) {
        return getRepository().existsById(entity.getId());
    }

    @Override
    public List<T> findAll() {
        return getRepository().findAll();
    }

    @Override
    public T find(Long id) {
        return getRepository().findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public T get(Long id) {
        return getRepository().getById(id);
    }
}
