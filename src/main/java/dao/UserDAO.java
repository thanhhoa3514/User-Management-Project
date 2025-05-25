package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import org.mindrot.jbcrypt.BCrypt;

import model.User;
import util.DatabaseConnection;

public class UserDAO {

	private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());

    /**
     * Get all users from the database
     * @return List of User objects
     * @throws SQLException if database access error occurs
     */
    public List<User> getAll() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                users.add(user);
            }
        } catch (SQLException e) {
            LOGGER.severe("Error fetching users: " + e.getMessage());
            throw e;
        }
        return users;
    }

    /**
     * Add a new user to the database
     * @param user User object to add
     * @throws SQLException if database access error occurs
     */
    public void add(User user) throws SQLException {
        if (user == null || user.getUsername() == null || user.getPassword() == null || user.getRole() == null) {
            throw new IllegalArgumentException("User data cannot be null");
        }

        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            stmt.setString(3, user.getRole());
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe("Error adding user: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Update an existing user
     * @param user User object with updated information
     * @throws SQLException if database access error occurs
     */
    public void update(User user) throws SQLException {
        if (user == null || user.getUserId() <= 0 || user.getUsername() == null || user.getRole() == null) {
            throw new IllegalArgumentException("Invalid user data for update");
        }

        String sql = "UPDATE users SET username = ?, password = ?, role = ? WHERE user_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword().isEmpty() ? user.getPassword() : BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            stmt.setString(3, user.getRole());
            stmt.setInt(4, user.getUserId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe("Error updating user: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Delete a user by ID
     * @param id User ID to delete
     * @throws SQLException if database access error occurs
     */
    public void delete(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }

        String sql = "DELETE FROM users WHERE user_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe("Error deleting user: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Find a user by username
     * @param username Username to search for
     * @return User object or null if not found
     * @throws SQLException if database access error occurs
     */
    public User findByUsername(String username) throws SQLException {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }

        String sql = "SELECT * FROM users WHERE username = ?";
        User user = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setUserId(rs.getInt("user_id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setRole(rs.getString("role"));
                }
            }
        } catch (SQLException e) {
            LOGGER.severe("Error finding user by username: " + e.getMessage());
            throw e;
        }
        return user;
    }

    /**
     * Validate user credentials
     * @param username Username to validate
     * @param password Password to validate
     * @return User object if valid, null otherwise
     * @throws SQLException if database access error occurs
     */
    public User validateUser(String username, String password) throws SQLException {
        User user = findByUsername(username);
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            return user;
        }
        return null;
    }
}
