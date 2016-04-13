/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coolrest;

import CoolRestCode.AsyncCallBackRest;
import CoolRestCode.ICoolRestCallBack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author jeanr
 */
public class CoolRestCallBackRequest {

    private final AsyncCallBackRest restFul;
    private final ExecutorService service;

    public CoolRestCallBackRequest() {
        restFul = new AsyncCallBackRest();
        service = Executors.newSingleThreadExecutor();
    }

    public CoolRestCallBackRequest(final ICoolRestCallBack parentClass) {
        restFul = new AsyncCallBackRest(parentClass);
        service = Executors.newSingleThreadExecutor();
    }

    public void getResource(final String url) {
        restFul.getResource(url);
        runTask();
    }
    

    public void postResource(final String url, final String jsonData) {
        restFul.postResource(url, jsonData);
        runTask();
    }
    
    public void putResource(final String url, final String jsonData) {
        restFul.putResource(url, jsonData);
        runTask();
    }
    
    public void deleteResource(final String url, final String jsonData) {
        restFul.deleteResource(url, jsonData);
        runTask();
    }

    private void runTask() {
        service.submit(restFul);
        service.shutdown();
    }

}
