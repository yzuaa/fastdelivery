package com.laioffer.fastdelivery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.*;

import java.sql.*;


@SpringBootApplication
public class FastDeliveryApplication {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(FastDeliveryApplication.class);
        Connection connection = null;
        Statement statement = null;
        String postgresUrl = "localhost";
        try {
            logger.debug("Creating database if not exist...");
            connection = DriverManager.getConnection("jdbc:postgresql://" + postgresUrl + ":5432/", "postgres", "secret");
            statement = connection.createStatement();
            statement.executeQuery("SELECT count(*) FROM pg_database WHERE datname = 'fastdelivery'");
            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            int count = resultSet.getInt(1);

            if (count <= 0) {
                statement.executeUpdate("CREATE DATABASE fastdelivery");
                logger.debug("Database created.");
            } else {
                logger.debug("Database already exist.");
            }
        } catch (SQLException e) {
            logger.error(e.toString());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                    logger.debug("Closed Statement.");
                }
                if (connection != null) {
                    logger.debug("Closed Connection.");
                    connection.close();
                }
            } catch (SQLException e) {
                logger.error(e.toString());
            }
        }



        SpringApplication.run(FastDeliveryApplication.class, args);
    }



}
