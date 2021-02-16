/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gbh.library.application;

import com.gbh.library.contracts.ILogService;
import com.gbh.library.enums.LogTypeEnum;
import com.gbh.library.services.DbLogService;

/**
 *
 * @author jarlynpolanco
 */
public class Startup {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        JettyServer jettyServer = new JettyServer();
        ILogService logService = new DbLogService();
        try { 
            jettyServer.start();
            logService.writeLog("JettyServer started succesfully.", LogTypeEnum.INFO.toString());
        } catch (Exception ex) {
            logService.writeLog(ex.getMessage(), LogTypeEnum.ERROR.toString());
        }
    }
}
