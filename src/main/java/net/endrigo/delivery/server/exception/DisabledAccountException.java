package net.endrigo.delivery.server.exception;

public class DisabledAccountException extends RuntimeException {

	private static final long serialVersionUID = 3367185350314301284L;

    public DisabledAccountException() {
        super("Account disabled");
    }
}

