/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CoolRestCode;

import CoolRestException.EmptyUrlException;
import CoolRestException.ServerErrorResponseException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.Callable;

/**
 *
 * @author jeanr
 */
public class AsyncRest implements Callable<String> {

    private final Connection connection;
    private RestFulHttpMethods method;
    private String url;
    private String jsonData;

    public AsyncRest() {
        connection = new Connection();
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

    public void deleteResource(final String url, final String jsonData) {
        method = RestFulHttpMethods.delete;
        this.url = url;
        this.jsonData = jsonData;
    }

    public void putResource(final String url, final String jsonData) {
        method = RestFulHttpMethods.put;
        this.url = url;
        this.jsonData = jsonData;
    }

    @Override
    public String call() throws IOException, MalformedURLException, EmptyUrlException, ServerErrorResponseException {
        switch (method) {
            case get:
                return connection.getRequest(url);
            case post:
                return connection.postRequest(url, jsonData);
            case put:
                return connection.putRequest(url, jsonData);
            case delete:
                return connection.deleteRequest(url, jsonData);
            default:
                return "Function does not exist";
        }
    }

}
