/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gbh.library.services;

import com.gbh.library.contracts.IGenericDAO;
import com.gbh.library.dao.impl.GenericDAOImpl;
import com.gbh.library.entities.Book;
import com.gbh.library.factory.EntityManagerFactory;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author jarlynpolanco
 */
public class BookService {
    
    EntityManager entityManager;
    IGenericDAO<Book> bookDAO;
        
    public List<Book> getBooks() throws Exception {
        List<Book> bookList = null;
        try{
            entityManager = EntityManagerFactory.getInstance();
            bookDAO = new GenericDAOImpl<>(Book.class, entityManager);
            bookList = bookDAO.readAll();
        }finally{
            entityManager.close();
        }
        return bookList;
    }
    
    public Book getBookById(int id) throws Exception {
        Book book = null;
        try{
            entityManager = EntityManagerFactory.getInstance();
            bookDAO = new GenericDAOImpl<>(Book.class, entityManager);
            book = bookDAO.read(id);
        }finally{
            entityManager.close();
        }
        return book;
    } 
}
