/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coolrest;

import CoolRestCode.AsyncRest;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author jeanr
 */
public class CoolRestRequest {

    private final AsyncRest restFul;
    private final ExecutorService service;

    public CoolRestRequest() {
        restFul = new AsyncRest();
        service = Executors.newSingleThreadExecutor();
    }

    public String getResource(final String url) throws InterruptedException, ExecutionException {
        restFul.getResource(url);
        return runTask();
    }

    public String postResource(final String url, final String jsonData) throws InterruptedException, ExecutionException {
        restFul.postResource(url, jsonData);
        return runTask();
    }

    public String putResource(final String url, final String jsonData) throws InterruptedException, ExecutionException {
        restFul.putResource(url, jsonData);
        return runTask();
    }

    public String deleteResource(final String url, final String jsonData) throws InterruptedException, ExecutionException {
        restFul.deleteResource(url, jsonData);
        return runTask();
    }

    private String runTask() throws InterruptedException, ExecutionException {
        Future<String> result = service.submit(restFul);
        service.shutdown();
        return result.get();
    }
}
