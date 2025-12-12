package dao;

import db.DatabaseManager;
import model.User;
import java.util.*;
import java.sql.*;
import java.util.Optional;

public class JdbcUserDao implements UserDao{
    @Override
    public User create(User user) throws SQLException{
        String sql = "INSERT INTO app_user (emri, mbiemri, email, password, roliId) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getEmri());
            ps.setString(2, user.getMbiemri());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            if (user.getRoliId() != null) ps.setInt(5, user.getRoliId());
            else ps.setNull(5, java.sql.Types.INTEGER);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    user.setUserId(rs.getInt(1));
                }
            }
        }
        return user;
    }

    @Override
    public Optional<User> findById(int id) throws SQLException {
        String sql = "SELECT userid, emri, mbiemri, email, password, roliId, created_at FROM app_user WHERE userid = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll() throws SQLException {
        String sql = "SELECT userid, emri, mbiemri, email, password, roliId, created_at FROM app_user";
        List<User> users = new ArrayList<>();
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                users.add(mapRow(rs));
            }
        }
        return users;
    }

    @Override
    public User update(User user) throws SQLException {
        String sql = "UPDATE app_user SET emri = ?, mbiemri = ?, email = ?, password = ?, roliId = ? WHERE userid = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getEmri());
            ps.setString(2, user.getMbiemri());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            if (user.getRoliId() != null) ps.setInt(5, user.getRoliId());
            else ps.setNull(5, java.sql.Types.INTEGER);
            ps.setInt(6, user.getUserId());
            ps.executeUpdate();
        }
        return user;
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM app_user WHERE userid = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
    @Override
    public Optional<User> findByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM app_user WHERE email=?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(mapRow(rs));
            }
        }
        return Optional.empty();
    }

    private User mapRow(ResultSet rs) throws SQLException {
        User u = new User();
        u.setUserId(rs.getInt("userid"));
        u.setEmri(rs.getString("emri"));
        u.setMbiemri(rs.getString("mbiemri"));
        u.setEmail(rs.getString("email"));
        u.setPassword(rs.getString("password"));
        int r = rs.getInt("roliId");
        u.setRoliId(rs.wasNull() ? null : r);
        Timestamp ts = rs.getTimestamp("created_at");
        if (ts != null) u.setCreatedAt(ts.toLocalDateTime());
        return u;
    }
}
