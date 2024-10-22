package Lab3;

import java.sql.*;

public class DB {

    static final String DB_URL = "jdbc:mysql://localhost:3306/dbslab3";
    static final String USERNAME = "root";
    static final String PASSWORD = "password";

    static void query(Connection connection, String queryString) throws SQLException {
        Statement stmt = connection.prepareStatement(queryString);
        stmt.executeUpdate(queryString);
        System.out.println("Query successful!");
    }

    static void updateQuery(Connection connection, String queryString) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(queryString);
        System.out.println("Query successful!");
    }

        public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);) {
            System.out.println("Connected!");

            /*
            query(connection, "DROP TABLE IF EXISTS cars");
            query(connection, "DROP TABLE IF EXISTS owners");

            query(connection,
                    "CREATE TABLE owners " +
                            "(owner_id INTEGER NOT NULL, " +
                            " first_name VARCHAR(255), " +
                            " last_name VARCHAR(255), " +
                            " address VARCHAR(255), " +
                            " PRIMARY KEY (owner_id))"
            );

            query(connection,
                    "CREATE TABLE cars " +
                            "(car_id INTEGER NOT NULL, " +
                            " name VARCHAR(255), " +
                            " registration VARCHAR(255), " +
                            " owner_id INTEGER, " +
                            " PRIMARY KEY (car_id), " +
                            " FOREIGN KEY (owner_id) REFERENCES owners(owner_id) ON DELETE CASCADE ON UPDATE CASCADE)"
            );

            query(connection,
                    "INSERT INTO owners VALUES (2, 'Mikasa', 'Ackerman', 'Mese Selimovica 13')"
            );

            query(connection,
                    "INSERT INTO cars VALUES (2, 'Audi A80', 'reg2', 2)"
                    );
             */

            /*
            String queryString = "SELECT c.name, o.first_name FROM cars c JOIN owners o ON c.owner_id = o.owner_id WHERE car_id = 1";
            Statement stmt = connection.prepareStatement(queryString);
            ResultSet rs = stmt.executeQuery(queryString);

            while(rs.next()) {
                System.out.println("Car name: " + rs.getString("name"));
                System.out.println("Owner name: " + rs.getString("first_name"));
            }

             */

            query(connection, "DELETE FROM cars WHERE car_id = 1");

            updateQuery(connection, "UPDATE owners SET first_name = 'Kaligula' WHERE owner_id = 1");

            String queryString = "SELECT * FROM owners";
            Statement stmt = connection.prepareStatement(queryString);
            ResultSet rs = stmt.executeQuery(queryString);

            while(rs.next()) {
                System.out.println("Owner name: " + rs.getString("first_name"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

/*
Through Java, create tables of cars and owners. Table cars should have car_id, name, registration and owner_id. Owner needs to have owner_id, first name, last name and address. Make sure you create connections between tables. (all of this needs to be done through JDBC queries).
Insert 3 rows of data in both tables (through JDBC)
Implement query that will read name of car and name of owner of car with id 1
Implement query that will delete one row
Implement one update query
 */