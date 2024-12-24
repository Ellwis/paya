//package com.paya.pleaserservice.config;
//
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//@Component
//public class PersistConfig {
//
//
//    @Bean
//    public HikariDataSource getConfig() {
//        try {
//            HikariConfig config = new HikariConfig();
//            config.setDriverClassName("com.mysql.jdbc.Driver");
//            config.setJdbcUrl("jdbc:mysql://localhost:3306/pleasure");
//            config.setUsername("root");
//            config.setPassword("root");
//            return new HikariDataSource(config);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
