package pl.coderslab.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.coderslab.AuthorConverter;
import pl.coderslab.PublisherConverter;

import javax.persistence.EntityManagerFactory;

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


}
