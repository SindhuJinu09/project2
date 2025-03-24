package com.studentmanagement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private static final String URL = "jdbc:postgresql://database-1.culss6gmw8na.us-east-1.rds.amazonaws.com:5432/database-1";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Sindhuj12";

    public void createStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students (name, email, age) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.setInt(3, student.getAge());
            stmt.executeUpdate();
        }
    }

    public Student getStudent(int id) throws SQLException {
        String sql = "SELECT * FROM students WHERE id=?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Student(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getInt("age"));
            }
        }
        return null;
    }

    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                students.add(new Student(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getInt("age")));
            }
        }
        return students;
    }

    public void updateStudent(Student student) throws SQLException {
        String sql = "UPDATE students SET name=?, email=?, age=? WHERE id=?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.setInt(3, student.getAge());
            stmt.setInt(4, student.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteStudent(int id) throws SQLException {
        String sql = "DELETE FROM students WHERE id=?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
