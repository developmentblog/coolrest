/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CoolRestCode;

import CoolRestException.EmptyUrlException;
import CoolRestException.ServerErrorResponseException;
import java.io.IOException;

/**
 *
 * @author jeanr
 */
public class AsyncCallBackRest implements Runnable {

    private ICoolRestCallBack parentClass;
    private Connection connection;
    private RestFulHttpMethods method;
    private String url;
    private String jsonData;

    public AsyncCallBackRest() {
        connection = new Connection();
    }

    public AsyncCallBackRest(final ICoolRestCallBack parentClass) {
        this();
        this.parentClass = parentClass;
    }

    public void getResource(final String url) {
        method = RestFulHttpMethods.get;
        this.url = url;
    }

    public void postResource(final String url, final String jsonData) {
        method = RestFulHttpMethods.post;
        this.url = url;
        this.jsonData = jsonData;
    }

    public void putResource(final String url, final String jsonData) {
        method = RestFulHttpMethods.put;
        this.url = url;
        this.jsonData = jsonData;
    }

    public void deleteResource(final String url, final String jsonData) {
        method = RestFulHttpMethods.delete;
        this.url = url;
        this.jsonData = jsonData;
    }

    @Override
    public void run() {
        try {
            String Result;
            switch (method) {
                case get:
                    Result = connection.getRequest(url);
                    break;
                case post:
                    Result = connection.postRequest(url, jsonData);
                    break;
                case delete:
                    Result = connection.deleteRequest(url, jsonData);
                    break;
                case put:
                    Result = connection.putRequest(url, jsonData);
                    break;
                default:
                    Result = "Function does not exist";
                    break;
            }

            if (parentClass != null) {
                parentClass.callback(Result);
            }
        } catch (IOException | EmptyUrlException | ServerErrorResponseException ex) {
            System.out.println(ex);
        }
    }

}
