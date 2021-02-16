/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gbh.library.services;

import com.gbh.library.contracts.IGenericDAO;
import com.gbh.library.dao.impl.GenericDAOImpl;
import com.gbh.library.entities.Page;
import com.gbh.library.factory.EntityManagerFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;

/**
 *
 * @author jarlynpolanco
 */
public class PageService {
    private EntityManager entityManager;
    private IGenericDAO<Page> pageDAO;
        
    public List<Page> getPagesByBook(Integer bookId) throws Exception {
        List<Page> pageList = null;
        try{
            entityManager = EntityManagerFactory.getInstance();
            pageDAO = new GenericDAOImpl<>(Page.class, entityManager);
            Map<String, Object> mapCriteria = new HashMap<>();
            mapCriteria.put("bookId", bookId);
            pageList = pageDAO.readListWithRestrictions(mapCriteria);
        }finally{
            entityManager.close();
        }
        return pageList;
    }
    
    public Page getPageByBookAndPageNum(Integer bookId, Integer pageNum) throws Exception {
        Page page = new Page();
        try{
            entityManager = EntityManagerFactory.getInstance();
            pageDAO = new GenericDAOImpl<>(Page.class, entityManager);
            Map<String, Object> mapCriteria = new HashMap<>();
            mapCriteria.put("bookId", bookId);
            mapCriteria.put("pageNumber", pageNum);
            page = pageDAO.readSingleWithRestrictions(mapCriteria);
        }finally{
            entityManager.close();
        }
        return page;
    } 
}
