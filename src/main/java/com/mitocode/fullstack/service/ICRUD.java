package com.mitocode.fullstack.service;

import java.util.List;

public interface ICRUD<T, ID> {
    T create(T t) throws Exception;
    T update(ID id,T t) throws Exception;
    List<T> readAll() throws Exception;
    T readById(ID id) throws Exception;
    void deleteById(ID id) throws Exception;
}
