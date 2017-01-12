package com.raiyee.pigeon.select.exceptions;

/**
 * TODO: Document Me!!
 *
 * @author wanglei
 * @date 17/1/12 下午5:35
 */
public class SelectException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public SelectException(String cause) {
        super(cause);
    }

    public SelectException(Throwable t) {
        super(t);
    }

    public SelectException(String cause, Throwable t) {
        super(cause, t);
    }
}
