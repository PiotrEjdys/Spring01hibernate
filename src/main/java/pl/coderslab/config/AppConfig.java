package pl.coderslab.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import pl.coderslab.converter.AuthorConverter;
import pl.coderslab.converter.PublisherConverter;
import pl.coderslab.dao.AuthorDao;
import pl.coderslab.dao.PublisherDao;

import javax.persistence.EntityManagerFactory;
import javax.validation.Validator;
import java.util.Locale;

@Configuration
@ComponentScan("pl.coderslab")
@EnableWebMvc
@EnableTransactionManagement
public class AppConfig implements WebMvcConfigurer {

    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactory() {
        LocalEntityManagerFactoryBean entityManagerFactoryBean
                = new LocalEntityManagerFactoryBean();
        entityManagerFactoryBean.setPersistenceUnitName("bookstorePersistenceUnit");
        return entityManagerFactoryBean;
    }
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager =
                new JpaTransactionManager(entityManagerFactory);
        return jpaTransactionManager;
    }
    @Override
    public void addFormatters(FormatterRegistry registry) {
//        registry.addConverter(getSkillConverter());
        registry.addConverter(getPublisherConverter());
        registry.addConverter(getAuthorConverter());
    }
    @Bean
    public PublisherConverter getPublisherConverter() {
        return new PublisherConverter(getPublisherDao());
    }

    @Bean
    public PublisherDao getPublisherDao() {
        return new PublisherDao();
    }
    @Bean
    public AuthorConverter getAuthorConverter() {
        return new AuthorConverter(getAuthorDao());
    }
    @Bean
    public AuthorDao getAuthorDao() {
        return new AuthorDao();
    }
    @Bean(name="localeResolver")
    public LocaleContextResolver getLocaleContextResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(new Locale("pl","PL"));
        return localeResolver;
    }
    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }


}
