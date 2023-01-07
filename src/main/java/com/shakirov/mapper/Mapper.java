package com.shakirov.mapper;

/**
 * @author igor@shakirov-dev.ru on 27.12.2022
 * @version 1.0
 */
public interface Mapper <F, T>{

    T map(F object);

    default T map (F fromObject, T toObject) {
        return toObject;
    }
}
