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
import javax.persistence.Table;

/**
 *
 * @author jarlynpolanco
 */
@Entity
@Table(name = "Logs")
public class Log implements Serializable {

    public Log() {
    }

    public Log(String message, String logType) {
        this.message = message;
        this.logType = logType;
    }
    
    @Id
    @Column(name = "LogId")
    private Integer bookId;

    @Column(name = "Message")
    private String message;
    
    @Column(name = "LogType")
    private String logType;
    

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }
}
