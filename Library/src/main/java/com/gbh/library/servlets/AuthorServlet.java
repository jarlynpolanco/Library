/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gbh.library.servlets;

import com.gbh.library.contracts.IServletAction;
import com.gbh.library.entities.Author;
import com.gbh.library.services.AuthorService;
import com.gbh.library.services.DbLogService;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jarlynpolanco
 */
public class AuthorServlet extends ActionsHttpServlet {
    
    AuthorService authorService;
    private final String GET_AUTHORS_PATTERN = "/?";
    private final String GET_AUTHOR_PATTERN = "^/(\\d+)$";
    private final String GET_AUTHOR_BOOKS_PATTERN = "^/(\\d+)/books$";
    
    public AuthorServlet(){
        super(new DbLogService());
        authorService = new AuthorService();
    }
    
    @Override
    protected Map<String, IServletAction> getActions() {
        Map<String, IServletAction> routes = new HashMap<>();
        routes.put(GET_AUTHORS_PATTERN, this::getAuthors);
        routes.put(GET_AUTHOR_PATTERN, this::findAuthor);
        routes.put(GET_AUTHOR_BOOKS_PATTERN, this::findAuthorBooks);
        return routes;
    }
    
    private void getAuthors() throws Exception {
        sendJson(authorService.getAuthors());
    }
    
    private void findAuthor() throws Exception {
        int authorId = getPathParamAsInt(0);
        Author author = authorService.getAuthorById(authorId);
        if (author == null) {
            sendNotFound();
            return;
        }
        sendJson(author);
    }
    
     private void findAuthorBooks() throws Exception {
        int authorId = getPathParamAsInt(0);
        Author author = authorService.getAuthorById(authorId);
        if (author == null) {
            sendNotFound();
            return;
        }
        sendJson(author.getBooks());
    }
}
