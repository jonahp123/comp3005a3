# Student Management System

A simple Java console application for managing student records in a PostgreSQL database.

## Setup

1. **Clone the repository**:
   ```
   git clone https://github.com/jonahp123/comp3005a3.git
   ```

2. **Create the database**: In pgAdmin, create a database named `A3` and run:
   ```sql
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
   ```

3. **Update credentials**: In `Main.java`, change the database credentials if needed (default: postgres/postgres)

## Running

Run `Main.java` in VSCode (requires Java Extension Pack)

## Usage

The application shows a menu with 5 options:

1. **View All Students** - Shows all student records
2. **Add Student** - Add a new student (you'll be prompted for details)
3. **Update Email** - Change a student's email by ID
4. **Delete Student** - Remove a student by ID
5. **Exit** - Close the application

**Note**: Use date format `YYYY-MM-DD` (e.g., 2024-09-01)

## Video Demo

https://youtu.be/Qa1VL23oyEk