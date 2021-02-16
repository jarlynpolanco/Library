/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gbh.library.services;

import com.gbh.library.contracts.IGenericDAO;
import com.gbh.library.contracts.ILogService;
import com.gbh.library.dao.impl.GenericDAOImpl;
import com.gbh.library.entities.Log;
import com.gbh.library.factory.EntityManagerFactory;
import com.gbh.library.servlets.BookServlet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author jarlynpolanco
 */
public class DbLogService implements ILogService {

    EntityManager entityManager;
    IGenericDAO<Log> logDAO;

    @Override
    public void writeLog(String message, String logType) {
        try {
            entityManager = EntityManagerFactory.getInstance();
            logDAO = new GenericDAOImpl<>(Log.class, entityManager);
            logDAO.create(new Log(message, logType));
        } catch (SQLException ex) {
            Logger.getLogger(BookServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            entityManager.close();
        }
    }
}
