/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gbh.library.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author jarlynpolanco
 */
@Entity
@Table(name = "Books")
public class Book implements Serializable {
    
    @Id
    @Column(name = "BookId")
    private Integer bookId;

    @Column(name = "Title")
    private String title;
    
    @Column(name = "Year")
    private Integer year;
    
    @Column(name = "ISBN")
    private String isbn;
    
    @Column(name = "Summary")
    private String summary;
    
    @Column(name = "Price")
    private Double price;

    @ManyToOne()
    @JoinColumn(name = "AuthorId", referencedColumnName = "AuthorId")
    private Author author;
    
    @OneToMany
    @JoinColumn(name = "BookId", referencedColumnName = "BookId")
    @JsonIgnore
    private List<Page> pages;
    

    public Integer getId() {
        return bookId;
    }

    public void setId(Integer id) {
        this.bookId = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }
  
    public Integer getAuthorId(){
        return this.author.getId();
    }   
}
