package cz.devfire.auction_system_orm;

import cz.devfire.auction_system_orm.database.connection.ConnectionProvider;
import cz.devfire.auction_system_orm.database.connection.NamedParameterStatement;
import cz.devfire.auction_system_orm.database.dao.UserDao;
import cz.devfire.auction_system_orm.database.model.User;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

@Log4j2(topic = "App")
public class Program {

    public static void main(String[] args) {
        loadProperties();

        // Testing connection
        try (Connection con = ConnectionProvider.getConnection()) {
            log.info("Connected: {}", con != null);
        } catch (SQLException e) {
            log.error("Connection error", e);
            return;
        }

        User user = User.builder()
                .login("son28")
                .name("Tonda")
                .surname("Sobota")
                .address("Fialová 8, Ostrava, 70833")
                .telephone("420596784213")
                .maximumUnfinishedAuctions(0)
                .type("U")
                .build();

        try (Connection connection = ConnectionProvider.getConnection()) {
            UserDao dao = new UserDao();
            int count = dao.insert(user, connection);

            if (count >= 1) {
                log.info("User inserted");
            } else {
                log.info("User not inserted");
            }

            count = dao.select(connection).size();
            log.info("Count: {}", count);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection = ConnectionProvider.getConnection()) {
            UserDao dao = new UserDao();
            int count = dao.delete(5, connection);

            if (count >= 1) {
                log.info("User deleted");
            } else {
                log.info("User not deleted");
            }

            count = dao.select(connection).size();
            log.info("Count: {}", count);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Custom select or different statement
        // Example: Provided select, will select all users with the same type as the user
        try (Connection connection = ConnectionProvider.getConnection()) {
            NamedParameterStatement statement = new NamedParameterStatement(connection, "SELECT * FROM [User] WHERE type = :type");

            UserDao dao = new UserDao();
            dao.prepareCommand(statement, user);

            try (ResultSet resultSet = statement.executeQuery()) {
                Collection<User> users = dao.read(resultSet);

                for (User u : users) {
                    log.info(u);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void loadProperties() {
        try (InputStream is = Program.class.getResourceAsStream("/application.properties")) {
            System.getProperties().load(is);
        } catch (IOException e) {
            log.error("loadProperties", e);
        }
    }
}
