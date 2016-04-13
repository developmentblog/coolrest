/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CoolRestException;

/**
 *
 * @author JeanCarlos
 */
public class ServerErrorResponseException extends Exception {
    
    public ServerErrorResponseException(final int response)
    {
        super("Looks like server could process your request: [Error code:" + response + "]");
    }
    
}
