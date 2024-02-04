package com.vom.util;

public class VomException extends Exception {
    public VomException() {
        super();
    }

    public VomException(String msg) {
        super(msg);
    }

    public VomException(Throwable err) {
        super(err);
    }

    public VomException(String msg, Throwable err) {
        super(msg, err);
    }
}
