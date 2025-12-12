package dao;

import db.DatabaseManager;
import model.Familjar;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcFamiljarDao implements FamiljarDao {

    @Override
    public Familjar create(Familjar f) throws SQLException {
        String sql = "INSERT INTO familjar (lidhja_familjare, kontakt, userid) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, f.getLidhjaFamiljare());
            ps.setString(2, f.getKontakt());
            if (f.getUserId() != null) ps.setInt(3, f.getUserId());
            else ps.setNull(3, Types.INTEGER);
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    f.setFamiljarId(rs.getInt(1));
                }
            }
        }
        return f;
    }

    @Override
    public Optional<Familjar> findById(int id) throws SQLException {
        String sql = "SELECT * FROM familjar WHERE familjarId = ?";
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
    public List<Familjar> findAll() throws SQLException {
        List<Familjar> list = new ArrayList<>();
        String sql = "SELECT * FROM familjar";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) list.add(mapRow(rs));
        }
        return list;
    }

    @Override
    public Familjar update(Familjar f) throws SQLException {
        String sql = "UPDATE familjar SET lidhja_familjare = ?, kontakt = ?, userid = ? WHERE familjarId = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, f.getLidhjaFamiljare());
            ps.setString(2, f.getKontakt());
            if (f.getUserId() != null) ps.setInt(3, f.getUserId());
            else ps.setNull(3, Types.INTEGER);
            ps.setInt(4, f.getFamiljarId());

            ps.executeUpdate();
        }
        return f;
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM familjar WHERE familjarId = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private Familjar mapRow(ResultSet rs) throws SQLException {
        Familjar f = new Familjar();
        f.setFamiljarId(rs.getInt("familjarId"));
        f.setLidhjaFamiljare(rs.getString("lidhja_familjare"));
        f.setKontakt(rs.getString("kontakt"));
        int u = rs.getInt("userid");
        f.setUserId(rs.wasNull() ? null : u);
        return f;
    }
}
