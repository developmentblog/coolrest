/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CoolRestCode;

import CoolRestException.EmptyUrlException;
import CoolRestException.ServerErrorResponseException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author jeanr
 */
public final class Connection {

    public static String version="1.1";
    public String postRequest(final String url, final String jsonData) throws IOException, MalformedURLException, EmptyUrlException, ServerErrorResponseException {

        return processRequest(url, "POST", jsonData);
    }

    public String putRequest(final String url, final String jsonData) throws IOException, MalformedURLException, EmptyUrlException, ServerErrorResponseException {

        return processRequest(url, "PUT", jsonData);
    }

    public String deleteRequest(final String url, final String jsonData) throws IOException, MalformedURLException, EmptyUrlException, ServerErrorResponseException {

        return processRequest(url, "DELETE", jsonData);
    }

    public String getRequest(final String url) throws IOException, MalformedURLException,EmptyUrlException {
          if(url.trim().isEmpty())
            throw new EmptyUrlException();
          
        InputStreamReader resource = null;
        try {
            resource = new InputStreamReader(new URL(url).openStream());

            String jsonResult = getStringFromInputStream(resource);

            return jsonResult;

        } catch (MalformedURLException ex) {
            throw ex;
        } catch (IOException ex) {
            throw ex;
        } finally {
            try {
                resource.close();
            } catch (IOException ex) {
                throw ex;
            }
        }
    }

    private String processRequest(final String url, final String RestEvent, final String jsonData) throws MalformedURLException, IOException,EmptyUrlException,ServerErrorResponseException {

        if(url.trim().isEmpty())
            throw new EmptyUrlException();

        HttpURLConnection httpConnection = (HttpURLConnection)new URL(url).openConnection();
        httpConnection.setDoOutput(true);
        httpConnection.setRequestMethod(RestEvent);
        httpConnection.setRequestProperty("Content-Type", "application/json");
        OutputStream outputStream = httpConnection.getOutputStream();
        outputStream.write(jsonData.getBytes());
        outputStream.flush();
        if (httpConnection.getResponseCode() != 200 && httpConnection.getResponseCode() != 204) {
            throw new ServerErrorResponseException(httpConnection.getResponseCode());
        }
        BufferedReader responseBuffer = new BufferedReader(new InputStreamReader((httpConnection.getInputStream())));
        String output;

        String outputResult = "";

        while ((output = responseBuffer.readLine()) != null) {
            outputResult += output;
        }

        httpConnection.disconnect();

        return outputResult;

    }

    private String getStringFromInputStream(InputStreamReader httpBody) throws IOException {

        BufferedReader bodyReader = null;
        StringBuilder bodyResponse = new StringBuilder();

        String line;
        try {

            bodyReader = new BufferedReader(httpBody);
            while ((line = bodyReader.readLine()) != null) {
                bodyResponse.append(line);
            }

        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bodyReader != null) {
                try {
                    bodyReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

        return bodyResponse.toString();

    }

}
