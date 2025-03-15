package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Configuration;

/**
 * description    :
 * packageName    : com.example.demo.command
 * fileName       : ModelMapperConfig
 * author         : cho
 * date           : 2025. 3. 14.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025. 3. 14.        cho       최초 생성
 */
@Slf4j
@Configuration
public class ModelMapperConfig {
    private static ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//	    modelMapper.getConfiguration().setDeepCopyEnabled(true);
    }

    public <D> D map(Object source, Class<D> destinationType) {
        try {
            return modelMapper.map(source, destinationType);
        } catch (Exception e) {
            log.error("Error mapping object: {}", e.getMessage(), e);
            return null;
        }
    }

    public <D> D object2Map(Object source, Class<D> destinationType) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        try {
            return modelMapper.map(source, destinationType);
        } catch (Exception e) {
            log.error("Error in object2Map: {}", e.getMessage(), e);
            return null;
        }
    }
}
