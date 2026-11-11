package com.andrew.expensetracker;

import java.sql.*;

public class ExpenseDAO {

    /**
     * Adds a category.
     */
    public void addCategory(String categoryName) {
        String sql =
                "INSERT INTO categories(name) VALUES(?)";

        try (
                Connection connection = DatabaseManager.getConnection();

                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, categoryName);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Adds expense.
     */
    public void addExpense(
            String description,
            double amount,
            int categoryId) {

        String sql =
                """
                INSERT INTO expenses(
                description,
                amount,
                category_id)
                VALUES(?,?,?)
                """;

        try (
                Connection connection = DatabaseManager.getConnection();

                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, description);
            statement.setDouble(2, amount);
            statement.setInt(3, categoryId);

            statement.executeUpdate();

            System.out.println("Expense added.");

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    /**
     * Retrieve expenses using JOIN.
     */
    public void viewExpenses() {

        String sql =
                """
                SELECT
                e.id,
                e.description,
                e.amount,
                c.name
                FROM expenses e
                JOIN categories c
                ON e.category_id = c.id
                """;

        try (
                Connection connection = DatabaseManager.getConnection();

                Statement statement = connection.createStatement();

                ResultSet rs = statement.executeQuery(sql)
        ) {

            while (rs.next()) {

                System.out.println(
                        rs.getInt("id")
                                + " | "
                                + rs.getString("description")
                                + " | "
                                + rs.getDouble("amount")
                                + " | "
                                + rs.getString("name"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Update expense.
     */
    public void updateExpense(int id,String description, double amount) {

        String sql =
                """
                UPDATE expenses
                SET description = ?,
                    amount = ?
                WHERE id = ?
                """;

        try (
                Connection connection = DatabaseManager.getConnection();

                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, description);
            statement.setDouble(2, amount);
            statement.setInt(3, id);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Expense updated.");
            } else {
                System.out.println("Expense not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete expense.
     */
    public void deleteExpense(int id) {
        String sql = "DELETE FROM expenses WHERE id = ?";

        try (
                Connection connection = DatabaseManager.getConnection();

                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            int rows = statement.executeUpdate();

            if (rows > 0) {

                System.out.println("Expense deleted.");

            } else {

                System.out.println("Expense not found.");

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    /**
     * Expense summary.
     */
    public void showSummary() {

        String sql =
                """
                SELECT
                SUM(amount) AS total,
                AVG(amount) AS average
                FROM expenses
                """;

        try (
                Connection connection = DatabaseManager.getConnection();

                Statement statement = connection.createStatement();

                ResultSet rs = statement.executeQuery(sql)
        ) {

            System.out.println("Total Spending: " + rs.getDouble("total"));

            System.out.println("Average Expense: " + rs.getDouble("average"));

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

}