/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gbh.library.services;

import com.gbh.library.contracts.IGenericDAO;
import com.gbh.library.dao.impl.GenericDAOImpl;
import com.gbh.library.entities.Author;
import com.gbh.library.factory.EntityManagerFactory;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author jarlynpolanco
 */
public class AuthorService {
    
    private EntityManager entityManager;
    private IGenericDAO<Author> authorDAO;
        
    public List<Author> getAuthors() throws Exception {
        List<Author> authorList = null;
        try{
            entityManager = EntityManagerFactory.getInstance();
            authorDAO = new GenericDAOImpl<>(Author.class, entityManager);
            authorList = authorDAO.readAll();
        }finally{
            entityManager.close();
        }
        return authorList;
    }
    
    public Author getAuthorById(int id) throws Exception {
        Author author = null;
        try{
            entityManager = EntityManagerFactory.getInstance();
            authorDAO = new GenericDAOImpl<>(Author.class, entityManager);
            author = authorDAO.read(id);
        }finally{
            entityManager.close();
        }
        return author;
    } 
}
