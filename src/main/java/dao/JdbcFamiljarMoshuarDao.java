package dao;

import db.DatabaseManager;
import model.FamiljarMoshuar;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcFamiljarMoshuarDao implements FamiljarMoshuarDao {

    @Override
    public FamiljarMoshuar create(FamiljarMoshuar fm) throws SQLException {
        String sql = "INSERT INTO familjar_moshuar (familjarId, imoshuarId, lidhja_f_m) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, fm.getFamiljarId());
            ps.setInt(2, fm.getImoshuarId());
            ps.setString(3, fm.getLidhjaFM());
            ps.executeUpdate();
        }
        return fm;
    }

    // Për primary key composite, kthejmë Optional bazuar në të dy ID-të
    public Optional<FamiljarMoshuar> findById(int familjarId, int imoshuarId) throws SQLException {
        String sql = "SELECT * FROM familjar_moshuar WHERE familjarId = ? AND imoshuarId = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, familjarId);
            ps.setInt(2, imoshuarId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(mapRow(rs));
            }
        }
        return Optional.empty();
    }

    @Override
    public List<FamiljarMoshuar> findAll() throws SQLException {
        List<FamiljarMoshuar> list = new ArrayList<>();
        String sql = "SELECT * FROM familjar_moshuar";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) list.add(mapRow(rs));
        }
        return list;
    }

    @Override
    public FamiljarMoshuar update(FamiljarMoshuar fm) throws SQLException {
        String sql = "UPDATE familjar_moshuar SET lidhja_f_m = ? WHERE familjarId = ? AND imoshuarId = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, fm.getLidhjaFM());
            ps.setInt(2, fm.getFamiljarId());
            ps.setInt(3, fm.getImoshuarId());
            ps.executeUpdate();
        }
        return fm;
    }

    public void delete(int familjarId, int imoshuarId) throws SQLException {
        String sql = "DELETE FROM familjar_moshuar WHERE familjarId = ? AND imoshuarId = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, familjarId);
            ps.setInt(2, imoshuarId);
            ps.executeUpdate();
        }
    }

    private FamiljarMoshuar mapRow(ResultSet rs) throws SQLException {
        FamiljarMoshuar fm = new FamiljarMoshuar();
        fm.setFamiljarId(rs.getInt("familjarId"));
        fm.setImoshuarId(rs.getInt("imoshuarId"));
        fm.setLidhjaFM(rs.getString("lidhja_f_m"));
        return fm;
    }
}
