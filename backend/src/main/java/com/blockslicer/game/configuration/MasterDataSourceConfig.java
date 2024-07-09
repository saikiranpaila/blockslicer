package com.blockslicer.game.configuration;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.blockslicer.game.dao0", // Adjust basePackages if repositories are in a different package
        entityManagerFactoryRef = "entityManagerFactory1",
        transactionManagerRef = "transactionManager1"
)
public class MasterDataSourceConfig {

    @Value("${master.url}")
    private String datasource1Url;

    @Value("${master.username}")
    private String datasource1Username;

    @Value("${master.password}")
    private String datasource1Password;

    @Bean(name = "Master_DataSource")
    public DataSource masterDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(datasource1Url);
        dataSource.setUsername(datasource1Username);
        dataSource.setPassword(datasource1Password);
        return dataSource;
    }

    @Bean(name = "entityManagerFactory1")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory1(
            EntityManagerFactoryBuilder builder,
            @Qualifier("Master_DataSource") DataSource dataSource) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.show_sql", true);
        // Add additional properties as needed

        return builder
                .dataSource(dataSource)
                .packages("com.blockslicer.game.entity0")
                .persistenceUnit("db1")
                .properties(properties)
                .build();
    }

    @Bean(name = "transactionManager1")
    public PlatformTransactionManager transactionManager1(@Qualifier("entityManagerFactory1") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
