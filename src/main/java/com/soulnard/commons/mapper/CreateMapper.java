package com.soulnard.commons.mapper;

/**
 * Mapper que convierte un DTO de creación a una entidad.
 *
 * @param <E> Entidad
 * @param <C> DTO de creación
 */
public interface CreateMapper<E, C> extends BaseMapper {

    E fromCreateDto(C createDto);
}
