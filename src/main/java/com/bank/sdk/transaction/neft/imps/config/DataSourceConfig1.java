package com.bank.sdk.transaction.neft.imps.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

//@Configuration
public class DataSourceConfig1 {

    @Value("${app.database.type}")
    private String dbType;

    private final Environment env;

    public DataSourceConfig1(Environment env) {
        this.env = env;
    }

    @Bean(name = "oracleDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.oracle")
    public DataSource oracleDataSource() {
        return DataSourceBuilder.create().build();
        /*
            REMEMBER THIS CHANGE IS MANDATORY IN PROPERTIES FILE IF DataSource is used
        when you use DataSourceBuilder.create().build(), it looks for jdbc-url in properties file, not url.
        spring.datasource.oracle.url -> has to be changed to spring.datasource.oracle.jdbc-url
        because Hikari is being used due to spring-boot-starter-data-jpa or spring-boot-starter-jdbc
        so DataSource compels to expect jdbc-url in properties file
        */
    }

    @Bean(name = "postgresDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.postgres")
    public DataSource postgresDataSource() {
        return DataSourceBuilder.create().build();
    }



    /*

🔑 The mechanism: Bean method arguments = dependency injection

In a @Configuration class, when you declare a @Bean method with parameters, Spring resolves those parameters from the ApplicationContext.

So here’s what happens step by step:

Spring sees your @Configuration class → processes each @Bean method.

        For oracleDataSource() and postgresDataSource():

Spring calls those methods first (because activeDataSource needs them).

Registers beans "oracleDataSource" and "postgresDataSource".

When processing activeDataSource():

It notices the parameters (DataSource oracleDataSource, DataSource postgresDataSource).

It looks into the ApplicationContext:

Finds a bean of type DataSource with name "oracleDataSource".

Finds a bean of type DataSource with name "postgresDataSource".

It injects those beans as arguments into the method call.
*/

    /*
        NOW THE BELOW IS REQUIRED AS WE NEED TO DECIDE AT RUNTIME WHICH
        spring.jpa.database-platform -> THAT CONTAINS THE ORACLE OR THE POSTGRES DIALECT
        to use to connect else it will take the default which is oracle in the properties file
        but when there will be postgres selected it will FAIL

        EntityManagerFactory gives the bean of EntityManager which actually gives
        details and requirements to use JPA/Hibernate to connect to database
     */

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource activeDataSource) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(activeDataSource);
        emf.setPackagesToScan("com.bank.sdk.transaction.neft.imps.entity");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        emf.setJpaVendorAdapter(vendorAdapter);

        Map<String, Object> jpaProperties = new HashMap<>();

        String dialectProperty = env.getProperty("spring.jpa." + dbType + ".database-platform");
        System.out.println("dialectProperty : " + dialectProperty);
        if (dialectProperty != null) {
            jpaProperties.put("hibernate.dialect", dialectProperty);
        }

        // Postgres-specific LOB fix
        if ("postgres".equalsIgnoreCase(dbType)) {
            Boolean lobNonContextual = Boolean.parseBoolean(
                    env.getProperty("spring.jpa.postgres.hibernate.jdbc.lob.non_contextual_creation", "true")
            );
            jpaProperties.put("hibernate.jdbc.lob.non_contextual_creation", lobNonContextual);
        }

        jpaProperties.put("hibernate.show_sql", true);
        jpaProperties.put("hibernate.hbm2ddl.auto",
                env.getProperty("spring.jpa.hibernate.ddl-auto", "validate"));

        emf.setJpaPropertyMap(jpaProperties);

        return emf;
    }
}
