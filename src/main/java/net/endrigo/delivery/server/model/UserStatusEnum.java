package net.endrigo.delivery.server.model;

public enum UserStatusEnum implements ValueLabelEnum<UserStatusEnum> {
	
    ACTIVE("A", "Ativo"),
    INACTIVE("I", "Inativo"),
    PENDING("P", "Pendente");
    
    private String value;
    private String label;

    private UserStatusEnum(String value, String label) {
        this.value = value;
        this.label = label;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public static UserStatusEnum toEnum(String value) {
        return EnumUtil.getByValue(UserStatusEnum.values(), value);
    }
}
