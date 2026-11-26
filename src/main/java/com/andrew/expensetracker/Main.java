package com.andrew.expensetracker;

import java.util.Scanner;

/**
 * Expense Tracker application.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Application entry point.
     */
    static void main() throws Exception {

        FirebaseConfig.initializeFirebase();

        System.out.println("Expense Tracker Started.");

        FirestoreExpenseDAO dao =
                new FirestoreExpenseDAO();

//        DatabaseManager.initializeDatabase();

//        ExpenseDAO dao = new ExpenseDAO();

//        seedCategories(dao);

        boolean running = true;

        while (running) {

            System.out.println("\n=== Expense Tracker ===");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Update Expense");
            System.out.println("4. Delete Expense");
            System.out.println("5. Expense Summary");
            System.out.println("6. Exit");

            System.out.print("Choice: ");

            String input = scanner.nextLine().trim();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Invalid choice. Please enter a number from 1 to 6.");
                continue;
            }

            switch (choice) {

                case 1 -> addExpense(dao);

                case 2 -> dao.viewExpenses();

                case 3 -> updateExpense(dao);

                case 4 -> deleteExpense(dao);

                case 5 -> dao.showSummary();

                case 6 -> running = false;

                default -> System.out.println("Invalid choice.");

            }

        }

        System.out.println("Application closed.");

    }

    /**
     * Add sample categories.
     */
    private static void seedCategories(ExpenseDAO dao) {

        dao.addCategory("Food");

        dao.addCategory("Transport");

        dao.addCategory("Entertainment");

    }


    /**
     * Add expense.
     */
    private static void addExpense(FirestoreExpenseDAO dao) throws Exception {

        System.out.print("Description: ");

        String description = scanner.nextLine();

        System.out.print("Amount: ");

        double amount = Double.parseDouble(scanner.nextLine());

        System.out.print("Category (1-Food, 2-Transport, 3-Entertainment): ");

        String categoryId = scanner.nextLine();

        dao.addExpense(description, amount, categoryId);

    }


    /**
     * Update expense.
     */
    private static void updateExpense(FirestoreExpenseDAO dao) throws Exception {

        System.out.print("Document ID (shown by View Expenses): ");

        String documentId = scanner.nextLine();

        System.out.print("New Description: ");

        String description = scanner.nextLine();

        System.out.print("New Amount: ");

        double amount = Double.parseDouble(scanner.nextLine());

        dao.updateExpense(documentId, description, amount);

    }

    /**
     * Delete expense.
     */
    private static void deleteExpense(FirestoreExpenseDAO dao) throws Exception {

        System.out.print("Document ID (shown by View Expenses): ");

        String id = scanner.nextLine();

        dao.deleteExpense(id);
    }

}
