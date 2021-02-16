/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gbh.library.contracts;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jarlynpolanco
 * @param <T>
 */
public interface IGenericDAO<T> {
    
    public T read(Integer id) throws SQLException;
    
    public T create(T obj) throws SQLException;
    
    public T update(T obj) throws SQLException;
    
    public void delete(T obj) throws SQLException;
    
    public List<T> readAll() throws SQLException;    
    
    public List<T> readListWithRestrictions(Map<String, Object> criteria) throws SQLException;
    
    public T readSingleWithRestrictions(Map<String, Object> criteria) throws SQLException;
}
