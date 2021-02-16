/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gbh.library.servlets;

import com.gbh.library.entities.Page;
import com.gbh.library.services.BookService;
import com.gbh.library.services.DbLogService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.gbh.library.entities.Book;
import com.gbh.library.contracts.IServletAction;
import com.gbh.library.services.PageService;
import java.util.List;

/**
 *
 * @author jarlynpolanco
 */
public class BookServlet extends ActionsHttpServlet {

    private final String HTML_TEMPLATE_PAGE = "<strong><center>Pagina #%s</center></strong><br><br><strong><center><p>%s</p></center></strong>";
    private final BookService bookService;
    private final PageService pageService;
    private final String GET_BOOKS_PATTERN = "/?";
    private final String FIND_BOOK_PATTERN = "/(\\d+)";
    private final String GET_PAGES_PATTERN = FIND_BOOK_PATTERN + "/page";
    private final String FIND_PAGE_PATTERN = GET_PAGES_PATTERN + "/(\\d+)";
    private final String HTML_PAGE_PATTERN = FIND_PAGE_PATTERN + "/html";

    public BookServlet() {
        super(new DbLogService());
        bookService = new BookService();
        pageService = new PageService();
    }
    
    @Override
    protected Map<String, IServletAction> getActions() {
        Map<String, IServletAction> routes = new HashMap<>();
        routes.put(GET_BOOKS_PATTERN, this::getBooks);
        routes.put(FIND_BOOK_PATTERN, this::findBook);
        routes.put(GET_PAGES_PATTERN, this::getPages);
        routes.put(FIND_PAGE_PATTERN, this::findPage);
        routes.put(HTML_PAGE_PATTERN, this::getPageAsHtml);
        return routes;
    }

    private void getBooks() throws Exception {
        sendJson(bookService.getBooks());
    }

    private void findBook() throws IOException, Exception {
        int bookId = getPathParamAsInt(0);
        Book book = bookService.getBookById(bookId);
        if (book == null) {
            sendNotFound();
            return;
        }
        sendJson(book);
    }

    private void getPages() throws IOException, Exception {
        int bookId = getPathParamAsInt(0);
        List<Page> pages = pageService.getPagesByBook(bookId);
        if (pages == null) {
            sendNotFound();
            return;
        }
        sendJson(pages);
    }

    private void findPage() throws IOException, Exception {

        int bookId = getPathParamAsInt(0);
        int pageNum = getPathParamAsInt(1);
        Page page = pageService.getPageByBookAndPageNum(bookId, pageNum);
        if (page == null) {
           sendNotFound();
            return;
        }    
        sendJson(page);
    }

    private void getPageAsHtml() throws IOException, Exception {
        int bookId = getPathParamAsInt(0);
        int pageNum = getPathParamAsInt(1);
        Page page = pageService.getPageByBookAndPageNum(bookId, pageNum);
        if (page == null) {
            sendNotFound();
            return;
        }      
        String html = String.format(HTML_TEMPLATE_PAGE, page.getPageNumber(), page.getContent());
        sendHtml(html);
    }
}
