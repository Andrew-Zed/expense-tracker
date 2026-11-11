package com.andrew.expensetracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private static final String DB_URL =
            "jdbc:sqlite:expense_tracker.db";

    /**
     * Creates database connection.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);

    }

    /**
     * Creates tables if they do not exist.
     */
    public static void initializeDatabase() {

        String categoryTable =
                """
                CREATE TABLE IF NOT EXISTS categories (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL
                );
                """;

        String expenseTable =
                """
                CREATE TABLE IF NOT EXISTS expenses (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    description TEXT NOT NULL,
                    amount REAL NOT NULL,
                    category_id INTEGER,
                    FOREIGN KEY(category_id)
                    REFERENCES categories(id)
                );
                """;

        try (
                Connection connection = getConnection();
                Statement statement = connection.createStatement()
        ) {
            statement.execute(categoryTable);
            statement.execute(expenseTable);

            System.out.println("Database initialized.");
        } catch (SQLException e) {

            System.out.println("Database error:");
            e.printStackTrace();

        }

    }

}
