package net.endrigo.delivery.server.model;


public interface ValueLabelEnum<C extends Enum<C>> {

    String getValue();

    String getLabel();
}
