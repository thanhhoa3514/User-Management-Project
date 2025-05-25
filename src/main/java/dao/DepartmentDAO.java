package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import model.Department;
import util.DatabaseConnection;
public class DepartmentDAO {
	private static final Logger LOGGER = Logger.getLogger(DepartmentDAO.class.getName());

    /**
     * Get all departments from the database
     * @return List of Department objects
     * @throws SQLException if database access error occurs
     */
    public List<Department> getAll() throws SQLException {
        List<Department> departments = new ArrayList<>();
        String sql = "SELECT * FROM departments";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Department dept = new Department();
                dept.setDepartmentId(rs.getInt("department_id"));
                dept.setName(rs.getString("name"));
                dept.setManagerId(rs.getObject("manager_id") != null ? rs.getInt("manager_id") : null);
                dept.setCreatedAt(rs.getTimestamp("created_at"));
                departments.add(dept);
            }
        } catch (SQLException e) {
            LOGGER.severe("Error fetching departments: " + e.getMessage());
            throw e;
        }
        return departments;
    }

    /**
     * Add a new department to the database
     * @param department Department object to add
     * @throws SQLException if database access error occurs
     */
    public void add(Department department) throws SQLException {
        if (department == null || department.getName() == null || department.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Department name cannot be null or empty");
        }

        String sql = "INSERT INTO departments (name, manager_id) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, department.getName());
            if (department.getManagerId() != null) {
                stmt.setInt(2, department.getManagerId());
            } else {
                stmt.setNull(2, java.sql.Types.INTEGER);
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe("Error adding department: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Update an existing department
     * @param department Department object with updated information
     * @throws SQLException if database access error occurs
     */
    public void update(Department department) throws SQLException {
        if (department == null || department.getDepartmentId() <= 0 || department.getName() == null || department.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid department data for update");
        }

        String sql = "UPDATE departments SET name = ?, manager_id = ? WHERE department_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, department.getName());
            if (department.getManagerId() != null) {
                stmt.setInt(2, department.getManagerId());
            } else {
                stmt.setNull(2, java.sql.Types.INTEGER);
            }
            stmt.setInt(3, department.getDepartmentId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe("Error updating department: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Delete a department by ID
     * @param id Department ID to delete
     * @throws SQLException if database access error occurs
     */
    public void delete(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid department ID");
        }

        String sql = "DELETE FROM departments WHERE department_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe("Error deleting department: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Find a department by ID
     * @param id Department ID to search for
     * @return Department object or null if not found
     * @throws SQLException if database access error occurs
     */
    public Department findById(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid department ID");
        }

        String sql = "SELECT * FROM departments WHERE department_id = ?";
        Department department = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    department = new Department();
                    department.setDepartmentId(rs.getInt("department_id"));
                    department.setName(rs.getString("name"));
                    department.setManagerId(rs.getObject("manager_id") != null ? rs.getInt("manager_id") : null);
                    department.setCreatedAt(rs.getTimestamp("created_at"));
                }
            }
        } catch (SQLException e) {
            LOGGER.severe("Error finding department by ID: " + e.getMessage());
            throw e;
        }
        return department;
    }

    /**
     * Find a department by name
     * @param name Department name to search for
     * @return Department object or null if not found
     * @throws SQLException if database access error occurs
     */
    public Department findByName(String name) throws SQLException {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Department name cannot be empty");
        }

        String sql = "SELECT * FROM departments WHERE name = ?";
        Department department = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    department = new Department();
                    department.setDepartmentId(rs.getInt("department_id"));
                    department.setName(rs.getString("name"));
                    department.setManagerId(rs.getObject("manager_id") != null ? rs.getInt("manager_id") : null);
                    department.setCreatedAt(rs.getTimestamp("created_at"));
                }
            }
        } catch (SQLException e) {
            LOGGER.severe("Error finding department by name: " + e.getMessage());
            throw e;
        }
        return department;
    }
}
