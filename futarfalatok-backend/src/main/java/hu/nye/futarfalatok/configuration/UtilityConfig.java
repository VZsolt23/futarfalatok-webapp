package hu.nye.futarfalatok.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilityConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
