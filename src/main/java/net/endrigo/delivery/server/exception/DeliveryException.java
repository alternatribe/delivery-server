package net.endrigo.delivery.server.exception;

public class DeliveryException extends RuntimeException {

	private static final long serialVersionUID = 2722830826739694922L;

	public DeliveryException(String msg) {
        super(msg);
    }
}
