# Student Management System

A Java application for managing student records in a PostgreSQL database with CRUD operations.

## Setup
1. Clone reposity by opening a vscode terminal, navigating to desired location and executing git clone https://github.com/jonahp123/comp3005a3.git

2. Create PostgreSQL database via pgadmin with the name `A3` and run (via query tool):

CREATE TABLE students(
    student_id SERIAL PRIMARY KEY,
    first_name TEXT NOT NULL,
    last_name  TEXT NOT NULL,
    email      TEXT NOT NULL UNIQUE,
    enrollment_date DATE
);

INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES
('John', 'Doe', 'john.doe@example.com', '2023-09-01'),
('Jane', 'Smith', 'jane.smith@example.com', '2023-09-01'),
('Jim', 'Beam', 'jim.beam@example.com', '2023-09-02');


3. Update database credentials in `Main.java` if needed (default: postgres/postgres)

## Running

**With VSCode IDE:** Run `Main.java` directly (make sure you have the java extension pack downloaded)

## Usage

Uncomment desired operations in `main()` and change input parameters (see video for clarity):

app.getAllStudents();
app.addStudent("John", "Doe", "email@example.com", java sql.Date.valueOf("2024-09-01"));
app.updateStudentEmail(1, "newemail@example.com");
app.deleteStudent(1);

## YT link
video tutorial/demonstration: https://youtu.be/Qa1VL23oyEk