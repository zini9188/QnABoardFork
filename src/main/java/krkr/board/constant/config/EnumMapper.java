package krkr.board.constant.config;

import krkr.board.constant.Scope;
import krkr.board.constant.Status;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class EnumMapper {

    @Bean
    public EnumMapperFactory createEnumMapperFactory() {
        EnumMapperFactory enumMapperFactory = new EnumMapperFactory(new LinkedHashMap<>());
        enumMapperFactory.put("Status", Status.class);
        enumMapperFactory.put("Scope", Scope.class);
        return enumMapperFactory;
    }
}
