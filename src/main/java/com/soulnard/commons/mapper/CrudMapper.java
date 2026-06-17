package com.soulnard.commons.mapper;

/**
 * Mapper que combina lectura, creación y actualización.
 * Extender en mappers concretos con MapStruct.
 *
 * @param <D> DTO de lectura
 * @param <E> Entidad
 * @param <C> DTO de creación
 * @param <U> DTO de actualización
 */
public interface CrudMapper<D, E, C, U> extends ReadMapper<E, D>, CreateMapper<E, C>, UpdateMapper<E, U> {
}
