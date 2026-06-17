package com.soulnard.commons.mapper;

import org.mapstruct.MappingTarget;

/**
 * Mapper que actualiza una entidad existente con datos de un DTO de actualización.
 *
 * @param <E> Entidad
 * @param <U> DTO de actualización
 */
public interface UpdateMapper<E, U> extends BaseMapper {

    E merge(@MappingTarget E entity, U updateDto);
}
