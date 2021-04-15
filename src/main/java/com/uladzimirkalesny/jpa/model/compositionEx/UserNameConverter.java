package com.uladzimirkalesny.jpa.model.compositionEx;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Locale;

@Converter(autoApply = true)
public class UserNameConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String userName) {
        return userName.toUpperCase(Locale.ROOT);
    }

    @Override
    public String convertToEntityAttribute(String USER_NAME) {
        return USER_NAME.toLowerCase(Locale.ROOT);
    }

}
