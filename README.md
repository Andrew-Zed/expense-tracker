# **Expense Tracker**

## **Overview**

As a software engineer, I continuously look for opportunities to strengthen my understanding of database-driven applications and improve my ability to integrate databases with software systems.

This software is a Java console application that allows users to manage expenses using a SQLite relational database. Users can add, view, update, and delete expenses while generating spending summaries from stored data.

The purpose of this software is to strengthen my knowledge of SQL database operations, JDBC connectivity, and relational database design while building a practical expense tracking application.

[Software Demo Video](https://youtu.be/yY72NirRFJ4)

## **Relational Database**

The application uses SQLite as its relational database engine.

The database contains two tables:

#### **Categories**

Column | Type 
id  | INTEGER 
name    | TEXT

#### **Expenses**


Column	Type
id	INTEGER
description	TEXT
amount	REAL
category_id	INTEGER


The relationship between the tables is established using a foreign key from Expenses to Categories.

### **Development Environment**

Tools used:

* Java 25
* Maven
* SQLite JDBC Driver
* IntelliJ IDEA
* Git
* GitHub
* DBeaver

Language:

* Java

Libraries:

* SQLite JDBC Driver

### **Useful Websites**

* https://sqlite.org
* https://github.com/xerial/sqlite-jdbc
* https://docs.oracle.com/en/java/
* https://www.baeldung.com/java-connect-sqlite
* https://dbeaver.io/

### **Future Work**

* Add expense categories dynamically through the application
* Generate monthly and yearly reports
* Add data validation and exception handling improvements
* Add export to CSV functionality
* Add a graphical user interface