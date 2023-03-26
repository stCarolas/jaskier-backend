package io.github.stcarolas.jaskier;

import org.bson.codecs.configuration.CodecRegistry;
import org.immutables.criteria.backend.Backend;
import org.immutables.criteria.mongo.MongoBackend;
import org.immutables.criteria.mongo.MongoSetup;
import org.immutables.criteria.mongo.bson4jackson.BsonModule;
import org.immutables.criteria.mongo.bson4jackson.IdAnnotationModule;
import org.immutables.criteria.mongo.bson4jackson.JacksonCodecs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.github.stcarolas.audd.api.AudDClient;
import io.vavr.jackson.datatype.VavrModule;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoDatabase;

@SpringBootApplication
public class JaskierApplication {

	public static void main(String[] args) {
		SpringApplication.run(JaskierApplication.class, args);
	}

    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new VavrModule());
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper;
    }

    @Bean
    public Backend mongoBackend(@Value("${spring.data.mongodb.uri}") String uri){
        ObjectMapper mapper = new ObjectMapper()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .registerModule(new BsonModule())
            .registerModule(new Jdk8Module())
            .registerModule(new VavrModule())
            .registerModule(new IdAnnotationModule());

        CodecRegistry registry = JacksonCodecs.registryFromMapper(mapper);
        MongoDatabase mongo = MongoClients.create(uri).getDatabase("jaskier").withCodecRegistry(registry);
        Backend backend = new MongoBackend(MongoSetup.of(mongo));
        return backend;
    }

    @Bean
    public AudDClient audDClient(@Value("${audD.key}") String key){
        return new AudDClient(key);
    }

}
