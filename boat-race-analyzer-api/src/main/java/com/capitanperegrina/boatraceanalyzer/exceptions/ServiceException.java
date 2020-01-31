package com.capitanperegrina.boatraceanalyzer.exceptions;

public class ServiceException extends Exception {

    private static final long serialVersionUID = 4710379827163384684L;

    /**
     * Información que causó el error.
     */
    private Object contextInfo;
    
    public ServiceException() {
        super();
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(Object contextInfo) {
        super();
        this.contextInfo = contextInfo;
    }

    public ServiceException(Object contextInfo, String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.contextInfo = contextInfo;
    }

    public ServiceException(Object contextInfo, String message, Throwable cause) {
        super(message, cause);
        this.contextInfo = contextInfo;
    }

    public ServiceException(Object contextInfo, String message) {
        super(message);
        this.contextInfo = contextInfo;
    }

    public ServiceException(Object contextInfo, Throwable cause) {
        super(cause);
        this.contextInfo = contextInfo;
    }

}
