package com.andrew.expensetracker;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

/**
 * Handles Firestore CRUD operations for expenses.
 */
public class FirestoreExpenseDAO {

    private final Firestore db;

    /**
     * Creates DAO and obtains Firestore instance.
     */
    public FirestoreExpenseDAO() {

        db = FirebaseConfig.getFirestore();

    }

    /**
     * Creates a new expense document in Firestore.
     */
    public void addExpense(String description, double amount, String categoryId) throws Exception {

        Map<String, Object> expense = new HashMap<>();

        expense.put("description", description);

        expense.put("amount", amount);

        expense.put("categoryId", categoryId);

        db.collection("expenses")
                .add(expense)
                .get();

        System.out.println("Expense saved successfully.");
    }

    /**
     * Retrieves all expense documents from Firestore.
     */
    public void viewExpenses() throws Exception {

        ApiFuture<QuerySnapshot> future = db.collection("expenses").get();

        QuerySnapshot snapshot = future.get();

        System.out.println("\nExpenses:");

        if (snapshot.isEmpty()) {
            System.out.println("No expenses found.");
            return;
        }

        for (DocumentSnapshot doc : snapshot.getDocuments()) {

            System.out.println("Document ID: " + doc.getId());
            System.out.println("Description: " + doc.getString("description"));
            System.out.println("Amount: " + doc.get("amount"));
            System.out.println("Category: " + doc.get("categoryId"));

            System.out.println("----------------------");

        }
    }

    /**
     * Updates an existing expense document in Firestore.
     */
    public void updateExpense(String documentId, String description, double amount) throws Exception {
        String id = documentId.trim();
        if (id.isEmpty()) {
            System.out.println("Expense ID cannot be blank.");
            return;
        }

        DocumentReference document = db.collection("expenses").document(id);
        if (!document.get().get().exists()) {
            System.out.println("Expense not found.");
            return;
        }

        Map<String, Object> updates = new HashMap<>();
        updates.put("description", description);
        updates.put("amount", amount);
        document.update(updates).get();

        System.out.println("Expense updated.");

    }

    /**
     * Deletes an existing expense document from Firestore.
     */
    public void deleteExpense(String documentId) throws Exception {
        String id = documentId.trim();
        if (id.isEmpty()) {
            System.out.println("Expense ID cannot be blank.");
            return;
        }

        DocumentReference document = db.collection("expenses").document(id);
        if (!document.get().get().exists()) {
            System.out.println("Expense not found.");
            return;
        }

        document.delete().get();

        System.out.println("Expense deleted.");

    }


    /**
     * Displays total and average
     * expense values.
     */
    public void showSummary() throws Exception {

        ApiFuture<QuerySnapshot> future = db.collection("expenses").get();

        QuerySnapshot snapshot = future.get();

        double total = 0;

        int count = 0;

        for (DocumentSnapshot doc : snapshot.getDocuments()) {

            Object value = doc.get("amount");
            if (value instanceof Number amount) {
                total += amount.doubleValue();
                count++;
            }
        }

        double average = count > 0
                        ? total / count
                        : 0;

        System.out.println("Total Spending: " + total);

        System.out.println("Average Expenses: " + average);
    }


}
