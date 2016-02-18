package com.trubeacon.cloverandroidapi.client;
        

public class RESTException extends Exception {

    private static final long serialVersionUID = 9057087179772834267L;
    
    private com.trubeacon.cloverandroidapi.client.error.Error error;
    
    public RESTException(String message, com.trubeacon.cloverandroidapi.client.error.Error error) {
    	super(message);
    	this.error = error;
    }
    public RESTException(String message, Throwable cause, com.trubeacon.cloverandroidapi.client.error.Error error) {
    	super(message, cause);
    	this.error = error;
    }
    
    public com.trubeacon.cloverandroidapi.client.error.Error getError() {
    	return error;
    }
   	
}
