package com.bitbank.config;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@Profile("local")
public class Config {

    private final String host;

    private final Integer port;

    private final String user;

    private final String senha;

    private String dbName;

    @Autowired
    public Config() throws Exception {

        this.user = "bitbank";
        this.senha = "bitbank";
        this.host = "129.0.0.221";
        this.port = 5350;
        this.dbName = "bitbank";

        // this.user = "admin";
        // this.senha = "RDMXKGKVYEDIHYSB";
        // this.host = "sl-us-south-1-portal.3.dblayer.com";
        // this.port = 21287;
        // this.dbName = "compose";

    }

    @Bean
    @Primary
    public DataSource dataSource() {

        String url = String.format("jdbc:postgresql://%s:%s/%s", this.host, this.port, this.dbName);
        System.out.println("@@@@@@ LOCAL DATABASE: " + url);
        return DataSourceBuilder.create().username(this.user).password(this.senha).url(url).build();
    }

    @Bean
    public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager();
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() throws SQLException {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(false);
        vendorAdapter.setShowSql(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.bitbank.domain.model");
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();

        return factory.getObject();

    }

}