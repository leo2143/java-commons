package com.soulnard.commons.service;

import com.soulnard.commons.exception.EntityNotFoundException;
import com.soulnard.commons.mapper.CreateMapper;
import com.soulnard.commons.mapper.ReadMapper;
import com.soulnard.commons.mapper.UpdateMapper;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Servicio CRUD abstracto genérico.
 *
 * @param <D> DTO de lectura
 * @param <E> Entidad JPA
 * @param <C> DTO de creación
 * @param <U> DTO de actualización
 * @param <ID> Tipo del identificador
 */
public abstract class CrudService<D, E, C, U, ID> {

    protected abstract JpaRepository<E, ID> getRepository();
    protected abstract ReadMapper<E, D> getReadMapper();
    protected abstract CreateMapper<E, C> getCreateMapper();
    protected abstract UpdateMapper<E, U> getUpdateMapper();

    protected abstract String getEntityName();

    @Transactional
    public D create(C createDto) {
        E entity = getCreateMapper().fromCreateDto(createDto);
        E saved = getRepository().save(entity);
        return getReadMapper().toDto(saved);
    }

    @Transactional
    public D update(ID id, U updateDto) {
        E entity = getRepository().findById(id)
                .orElseThrow(() -> new EntityNotFoundException(getEntityName(), id));
        E merged = getUpdateMapper().merge(entity, updateDto);
        E saved = getRepository().save(merged);
        return getReadMapper().toDto(saved);
    }

    @Transactional
    public void delete(ID id) {
        if (!getRepository().existsById(id)) {
            throw new EntityNotFoundException(getEntityName(), id);
        }
        getRepository().deleteById(id);
    }

    public D findById(ID id) {
        E entity = getRepository().findById(id)
                .orElseThrow(() -> new EntityNotFoundException(getEntityName(), id));
        return getReadMapper().toDto(entity);
    }

    public Optional<D> findByIdOptional(ID id) {
        return getRepository().findById(id)
                .map(getReadMapper()::toDto);
    }

    public Page<D> findAll(Pageable pageable) {
        return getRepository().findAll(pageable)
                .map(getReadMapper()::toDto);
    }
}
