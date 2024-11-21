package com.fullstack.labb2journal;

import com.fullstack.labb2journal.entitys.User;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<User.Gender, String> {
    @Override
    public String convertToDatabaseColumn(User.Gender gender) {
        return gender != null ? gender.name() : null;
    }

    @Override
    public User.Gender convertToEntityAttribute(String dbData) {
        try {
            return dbData != null ? User.Gender.valueOf(dbData.toUpperCase()) : null;
        } catch (IllegalArgumentException e) {
            return null; // Or handle the error as needed
        }
    }
}
