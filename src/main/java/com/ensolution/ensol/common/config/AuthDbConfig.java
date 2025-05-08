package com.ensolution.ensol.common.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.ensolution.ensol.repository.auth",
    entityManagerFactoryRef = "authEntityManagerFactory",
    transactionManagerRef = "authTransactionManager"
)
public class AuthDbConfig {
  @Bean
  @ConfigurationProperties(prefix = "spring.datasource.auth")
  public DataSource authDataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean authEntityManagerFactory(EntityManagerFactoryBuilder builder) {
    return builder
        .dataSource(authDataSource())
        .packages("com.ensolution.ensol.entity.auth")
        .persistenceUnit("auth")
        .build();
  }

  @Bean
  public PlatformTransactionManager authTransactionManager(@Qualifier("authEntityManagerFactory") EntityManagerFactory emf) {
    return new JpaTransactionManager(emf);
  }
}
