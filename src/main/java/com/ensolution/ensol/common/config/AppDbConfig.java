package com.ensolution.ensol.common.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.ensolution.ensol.repository.app",
    entityManagerFactoryRef = "appEntityManagerFactory",
    transactionManagerRef = "appTransactionManager"
)
public class AppDbConfig {
  @Primary
  @Bean
  @ConfigurationProperties(prefix = "spring.datasource.app")
  public DataSource appDataSource() {
    return DataSourceBuilder.create().build();
  }

  @Primary
  @Bean
  public LocalContainerEntityManagerFactoryBean appEntityManagerFactory(EntityManagerFactoryBuilder builder) {
    return builder
        .dataSource(appDataSource())
        .packages("com.ensolution.ensol.entity.app")
        .persistenceUnit("app")
        .build();
  }

  @Primary
  @Bean
  public PlatformTransactionManager appTransactionManager(@Qualifier("appEntityManagerFactory") EntityManagerFactory emf) {
    return new JpaTransactionManager(emf);
  }
}
