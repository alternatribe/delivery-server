package net.endrigo.delivery.server.controller.exception;

import java.io.Serializable;

public class StandardError extends MessageResponse implements Serializable {

	private static final long serialVersionUID = -6680620642243081419L;

	private Long timestamp;
    private Integer status;
    private String path;

    public StandardError(Integer status, String message, String path) {
        super(message);
        this.timestamp = System.currentTimeMillis();
        this.status = status;
        this.path = path;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}