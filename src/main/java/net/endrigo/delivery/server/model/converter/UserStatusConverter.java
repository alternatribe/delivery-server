package net.endrigo.delivery.server.model.converter;

import javax.persistence.AttributeConverter;

import net.endrigo.delivery.server.model.EnumUtil;
import net.endrigo.delivery.server.model.UserStatusEnum;

public class UserStatusConverter implements AttributeConverter<UserStatusEnum, String> {

    @Override
    public String convertToDatabaseColumn(UserStatusEnum enumeration) {
        if (enumeration != null) {
            return enumeration.getValue();
        }
        return null;
    }

    @Override
    public UserStatusEnum convertToEntityAttribute(String value) {
        if (value != null) {
            return EnumUtil.getByValue(UserStatusEnum.values(), value);
        }
        return null;
    }
}
