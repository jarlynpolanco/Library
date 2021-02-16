/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gbh.library.factory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author jarlynpolanco
 */
public class EntityManagerFactory {
    
    private final static String PERSITENCE_NAME = "LibraryDb";
    
    public static EntityManager getInstance(){
        return Persistence.createEntityManagerFactory(PERSITENCE_NAME).createEntityManager();
    }
}
