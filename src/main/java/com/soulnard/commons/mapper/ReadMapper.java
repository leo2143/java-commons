package com.soulnard.commons.mapper;

import java.util.List;

/**
 * Mapper que convierte una entidad a un DTO de lectura.
 *
 * @param <E> Entidad
 * @param <D> DTO de lectura
 */
public interface ReadMapper<E, D> extends BaseMapper {

    D toDto(E entity);

    List<D> toDtoList(List<E> entities);
}
