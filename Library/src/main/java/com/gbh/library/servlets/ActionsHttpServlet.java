/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gbh.library.servlets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gbh.library.contracts.ILogService;
import com.gbh.library.enums.LogTypeEnum;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.gbh.library.contracts.IServletAction;

/**
 *
 * @author jarlynpolanco
 */
public abstract class ActionsHttpServlet extends HttpServlet {

    private final ILogService logService;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String[] pathParams;

    public ActionsHttpServlet(ILogService logService) {
        this.logService = logService;
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        this.request = request;
        this.response = response;
        String pathInfo = this.request.getPathInfo();
        if (pathInfo == null) {
            pathInfo = "";
        }
        IServletAction actionMethod = null;
        pathParams = new String[0];
        Map<String, IServletAction> actions = getActions();
        for (Map.Entry<String, IServletAction> action : actions.entrySet()) {
            Pattern pattern = Pattern.compile(String.format("^%s$", action.getKey()));
            Matcher matcher = pattern.matcher(pathInfo);
            if (matcher.find()) {
                actionMethod = action.getValue();
                pathParams = IntStream.rangeClosed(1, matcher.groupCount()).mapToObj(idx -> matcher.group(idx)).toArray(String[]::new);
                break;
            }
        }
        if (actionMethod == null) {
            sendBadRequest();
            return;
        }
        try {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            actionMethod.execute();
        } catch (Throwable ex) {
            logService.writeLog(ex.getMessage(), LogTypeEnum.ERROR.toString());
            sendError(ex);
        }
    }

    protected abstract Map<String, IServletAction> getActions();

    protected String getPathParam(int index) {
        if (index >= pathParams.length) {
            return null;
        }
        return pathParams[index];
    }

    protected Integer getPathParamAsInt(int index) {
        String value = getPathParam(index);
        if (value == null) {
            return null;
        }
        return Integer.parseInt(value);
    }

    protected void sendNotFound() {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }
    
    protected void sendBadRequest() {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    protected void sendError(Throwable ex) {
        String json = "{ \"error\": \"" + ex.getMessage().replace("\"", "\\\"") + "\" }";
        sendContent(json, "application/json", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

    protected void sendContent(String content, String contentType, int status) {
        response.setStatus(status);
        response.setContentType(contentType);
        try {
            response.getWriter().println(content);
        } catch (IOException ex) {
            logService.writeLog(ex.getMessage(), LogTypeEnum.ERROR.toString());
        }
    }

    protected void sendHtml(String html) {
        sendContent(html, "text/html", HttpServletResponse.SC_OK);
    }

    protected void sendJson(Object object) {
        String json;
        if (object.getClass() == String.class) {
            json = (String) object;
        } else {
            ObjectMapper mapper = new ObjectMapper();
            try {
                json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
            } catch (JsonProcessingException ex) {
                logService.writeLog(ex.getMessage(), LogTypeEnum.ERROR.toString());
                sendError(ex);
                return;
            }
        }
        sendContent(json, "application/json", HttpServletResponse.SC_OK);
    }
}
