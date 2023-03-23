package io.github.stcarolas.jaskier;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.infinispan.manager.DefaultCacheManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.vavr.jackson.datatype.VavrModule;

@SpringBootApplication
public class JaskierApplication {

	public static void main(String[] args) {
		SpringApplication.run(JaskierApplication.class, args);
	}

    @Bean
    public DefaultCacheManager cacheManager() {
        return new DefaultCacheManager();
    }

    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new VavrModule());
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper;
    }

}
