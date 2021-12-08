package org.zerock.w3.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;

public enum MapperUtil {
    INSTANCE;

    private ModelMapper modelMapper;

    MapperUtil() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setFieldMatchingEnabled(true);
    }

    public ModelMapper get() {
        return modelMapper;
    }
}