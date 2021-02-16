/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gbh.library.application;

import com.gbh.library.servlets.AuthorServlet;
import com.gbh.library.servlets.BookServlet;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.Servlet;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

/**
 *
 * @author jarlynpolanco
 */
public class JettyServer {
    
    private Server server;

    public void start() throws Exception {
        server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8090);
        server.setConnectors(new Connector[] {connector});
        ServletHandler servletHandler = new ServletHandler();
        server.setHandler(servletHandler);
        
        Map<Class<? extends Servlet>, String> routes = new HashMap<>();
        
        routes.put(BookServlet.class, "/book/*");
        routes.put(AuthorServlet.class, "/author/*");
        
        routes.entrySet().forEach(route -> {
            servletHandler.addServletWithMapping(route.getKey(), route.getValue());
        });
        server.start();
    }

}
