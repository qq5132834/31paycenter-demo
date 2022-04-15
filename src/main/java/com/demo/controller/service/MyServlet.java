package com.demo.controller.service;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.mitre.dsmiley.httpproxy.ProxyServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 * https://copyfuture.com/blogs-details/20200628083454250gp87poo5go3pvf8
 *
 * jara -jar -Dspring.config.location=D:/development/gitlab/demo/src/main/resources/application.yml demo-1.5.9.RELEASE.jar
 */
public class MyServlet extends ProxyServlet {


    @Override
    protected HttpResponse doExecute(HttpServletRequest servletRequest,
                                     HttpServletResponse servletResponse,
                                     HttpRequest proxyRequest) throws IOException {
        return super.doExecute(servletRequest, servletResponse, proxyRequest);
    }
}

