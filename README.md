# **Expense Tracker**

## **Overview**

As a software engineer, I continuously look for opportunities to strengthen my understanding of database-driven applications and improve my ability to integrate databases with software systems.

This software is a Java console application that allows users to manage expenses stored in Google Cloud Firestore. Users can add, view, update, and delete expenses while generating spending summaries from stored data.

SQLite was used in the initial iteration. Its database and DAO code remain in the project, but the application currently uses Firestore only; the two backends are not yet connected.

[Software Demo Video](https://youtu.be/yY72NirRFJ4)

## **Persistence**

The active backend is Firestore. Expenses are stored in the `expenses` collection with a Firestore-generated document ID and fields for description, amount, and category ID.

To run the application, provide a Firebase service account file at `firebase/service-account.json`. This credential file is ignored by Git and must not be committed.

The original SQLite implementation is retained in `DatabaseManager` and `ExpenseDAO`, but is not currently selected by the application. Supporting both backends in a future iteration will require a shared repository interface and an explicit strategy for synchronizing or selecting storage.

### **Development Environment**

Tools used:

* Java 25
* Maven
* SQLite JDBC Driver
* Firebase Admin SDK
* IntelliJ IDEA
* Git
* GitHub
* DBeaver

Language:

* Java

Libraries:

* SQLite JDBC Driver
* Firebase Admin SDK

### **Useful Websites**

* https://sqlite.org
* https://github.com/xerial/sqlite-jdbc
* https://docs.oracle.com/en/java/
* https://www.baeldung.com/java-connect-sqlite
* https://dbeaver.io/

### **Future Work**

* Add an abstraction that allows selecting Firestore or SQLite storage
* Define how data is migrated or synchronized when both backends are enabled
* Generate monthly and yearly reports
* Add data validation and exception handling improvements
* Add export to CSV functionality
* Add a graphical user interface