package net.endrigo.delivery.server.exception;

public class InvalidEnumException extends RuntimeException {

	private static final long serialVersionUID = -7264924131641591009L;

	public InvalidEnumException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidEnumException(String message) {
        super(message);
    }
}

