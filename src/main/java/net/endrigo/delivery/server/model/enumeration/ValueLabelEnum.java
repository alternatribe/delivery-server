package net.endrigo.delivery.server.model.enumeration;


public interface ValueLabelEnum<C extends Enum<C>> {

    String getValue();

    String getLabel();
}
