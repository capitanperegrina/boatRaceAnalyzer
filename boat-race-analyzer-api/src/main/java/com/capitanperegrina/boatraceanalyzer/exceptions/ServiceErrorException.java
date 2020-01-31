package com.capitanperegrina.boatraceanalyzer.exceptions;

public class ServiceErrorException extends RuntimeException {

    private static final long serialVersionUID = 5949421522472458314L;
    
    /**
     * Información que causó el error.
     */
    private Object contextInfo;

    public ServiceErrorException() {
        super();
    }

    public ServiceErrorException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ServiceErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceErrorException(String message) {
        super(message);
    }

    public ServiceErrorException(Throwable cause) {
        super(cause);
    }

    public ServiceErrorException(Object contextInfo) {
        super();
        this.contextInfo = contextInfo;
    }

    public ServiceErrorException(Object contextInfo, String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.contextInfo = contextInfo;
    }

    public ServiceErrorException(Object contextInfo, String message, Throwable cause) {
        super(message, cause);
        this.contextInfo = contextInfo;
    }

    public ServiceErrorException(Object contextInfo, String message) {
        super(message);
        this.contextInfo = contextInfo;
    }

    public ServiceErrorException(Object contextInfo, Throwable cause) {
        super(cause);
        this.contextInfo = contextInfo;
    }
}
