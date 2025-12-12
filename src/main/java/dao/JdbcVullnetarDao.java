package dao;

import db.DatabaseManager;
import model.Vullnetar;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcVullnetarDao implements VullnetarDao {

    @Override
    public Vullnetar create(Vullnetar v) throws SQLException {
        String sql = "INSERT INTO vullnetar (zoni_sherbimit, disponueshmeria, userid) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, v.getZoniSherbimit());
            ps.setString(2, v.getDisponueshmeria());
            if (v.getUserId() != null) ps.setInt(3, v.getUserId());
            else ps.setNull(3, Types.INTEGER);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) v.setVullnetarId(rs.getInt(1));
            }
        }
        return v;
    }

    @Override
    public Optional<Vullnetar> findById(int id) throws SQLException {
        String sql = "SELECT vullnetarId, zoni_sherbimit, disponueshmeria, userid FROM vullnetar WHERE vullnetarId = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(mapRow(rs));
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Vullnetar> findAll() throws SQLException {
        List<Vullnetar> list = new ArrayList<>();
        String sql = "SELECT vullnetarId, zoni_sherbimit, disponueshmeria, userid FROM vullnetar";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        }
        return list;
    }

    @Override
    public Vullnetar update(Vullnetar v) throws SQLException {
        String sql = "UPDATE vullnetar SET zoni_sherbimit = ?, disponueshmeria = ?, userid = ? WHERE vullnetarId = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, v.getZoniSherbimit());
            ps.setString(2, v.getDisponueshmeria());
            if (v.getUserId() != null) ps.setInt(3, v.getUserId());
            else ps.setNull(3, Types.INTEGER);
            ps.setInt(4, v.getVullnetarId());
            ps.executeUpdate();
        }
        return v;
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM vullnetar WHERE vullnetarId = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private Vullnetar mapRow(ResultSet rs) throws SQLException {
        Vullnetar v = new Vullnetar();
        v.setVullnetarId(rs.getInt("vullnetarId"));
        v.setZoniSherbimit(rs.getString("zoni_sherbimit"));
        v.setDisponueshmeria(rs.getString("disponueshmeria"));
        int u = rs.getInt("userid");
        v.setUserId(rs.wasNull() ? null : u);
        return v;
    }
}
