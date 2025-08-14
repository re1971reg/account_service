package faang.school.accountservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Locale;

@Configuration
@EnableWebMvc
public class CommonConfig {

    // @Bean
    // public MessageSource messageSource() {
    //     // ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
    //     // messageSource.setBasename("classpath:messages");
    //     // messageSource.setDefaultEncoding("UTF-8");
    //     // return messageSource;
    //
    //     ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
    //     // Укажите базовое имя ресурса (без расширения)
    //     resource.setBasename("classpath:messages");
    //     resource.setDefaultEncoding("UTF-8");
    //     return resource;
    // }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }

    @Bean
    public Locale locale() {
        // на первом этапе прибил гвоздём RU
        return new Locale("ru_RU");
    }
}
