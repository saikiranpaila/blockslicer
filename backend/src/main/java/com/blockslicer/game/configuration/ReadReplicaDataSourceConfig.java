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
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.blockslicer.game.dao1",
        entityManagerFactoryRef = "entityManagerFactory2",
        transactionManagerRef = "transactionManager2"
)
public class ReadReplicaDataSourceConfig {

    @Value("${readreplica.url}")
    private String datasource2Url;

    @Value("${readreplica.username}")
    private String datasource2Username;

    @Value("${readreplica.password}")
    private String datasource2Password;

    @Bean(name = "readReplicaDataSource")
    public DataSource readReplicaDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(datasource2Url);
        dataSource.setUsername(datasource2Username);
        dataSource.setPassword(datasource2Password);
        return dataSource;
    }

    @Bean(name = "entityManagerFactory2")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory2(EntityManagerFactoryBuilder builder, @Qualifier("readReplicaDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.blockslicer.game.entity1")
                .persistenceUnit("db2")
                .build();
    }

    @Bean(name = "transactionManager2")
    public PlatformTransactionManager transactionManager2(@Qualifier("entityManagerFactory2") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
