package com.ms.vm.exc;

public class NotSufficientChangeException extends Exception {
    public NotSufficientChangeException(String msg) {
        this.msg = msg;
    }

    public NotSufficientChangeException(String message, String msg) {
        super(message);
        this.msg = msg;
    }

    public NotSufficientChangeException(String message, Throwable cause, String msg) {
        super(message, cause);
        this.msg = msg;
    }

    public NotSufficientChangeException(Throwable cause, String msg) {
        super(cause);
        this.msg = msg;
    }

    public NotSufficientChangeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String msg) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.msg = msg;
    }

    String msg;
}
