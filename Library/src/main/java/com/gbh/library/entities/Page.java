/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gbh.library.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author jarlynpolanco
 */
@Entity
@Table(name = "Pages")
public class Page implements Serializable{
    
    @Id
    @Column(name = "PageId")
    private Integer pageId;

    @Column(name = "PageNumber")
    private Integer pageNumber;
    
    @Column(name = "Content")
    private String content;
    
    @Column(name = "BookId", insertable = false, updatable = false)
    private Integer bookId;
    
    @ManyToOne
    @JoinColumn(name = "BookId", referencedColumnName = "BookId")
    private Book book;
        

    public Integer getId() {
        return pageId;
    }

    public void setId(Integer id) {
        this.pageId = id;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
    
    public Integer getPageId() {
        return pageId;
    }

    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
    
    
}
