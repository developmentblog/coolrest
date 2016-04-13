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
public class EmptyUrlException extends Exception{
    
    public EmptyUrlException()
    {
        super("Url cannot be null or empty");
    }
    
}
