/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.springboot;

/**
 *
 * @author USER
 */
public class HttpRetour {
    int status;
    String message;
    Object[] data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object[] getData() {
        return data;
    }

    public void setData(Object[] data) {
        this.data = data;
    }
    
    public void setHttpRetour(HttpRetour h, int status, String message, Object[] data) {
        h.setStatus(status);
        h.setMessage(message);
        h.setData(data);
    }
}
