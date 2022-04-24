package com.ms.vm.exc;

public class InsufficientBalanceException extends Exception {

    String msg;

    public InsufficientBalanceException() {
        this.msg = "Not enoght money provided";
        this.errCode = errCode;
    }

    public InsufficientBalanceException(String msg, String errCode) {
        this.msg = msg;
        this.errCode = errCode;
    }

    public InsufficientBalanceException(String message, String msg, String errCode) {
        super(message);
        this.msg = msg;
        this.errCode = errCode;
    }

    public InsufficientBalanceException(String message, Throwable cause, String msg, String errCode) {
        super(message, cause);
        this.msg = msg;
        this.errCode = errCode;
    }

    public InsufficientBalanceException(Throwable cause, String msg, String errCode) {
        super(cause);
        this.msg = msg;
        this.errCode = errCode;
    }

    public InsufficientBalanceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String msg, String errCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.msg = msg;
        this.errCode = errCode;
    }

    String errCode;



}
