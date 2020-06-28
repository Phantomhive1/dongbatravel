package com.tedu.pj.common.exception;

public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = -409059933523685834L;
    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

}
