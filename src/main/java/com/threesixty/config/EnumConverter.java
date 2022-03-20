package com.threesixty.config;

import com.threesixty.commons.enums.ListingState;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EnumConverter implements Converter<String, ListingState> {

    @Override
    public ListingState convert(String value) {
        try {
            return ListingState.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }

    }

}