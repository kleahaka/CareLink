package dao;

import db.DatabaseManager;
import model.IMoshuar;

import db.DatabaseManager.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcIMoshuarDao implements IMoshuarDao {

    @Override
    public IMoshuar create(IMoshuar i) throws SQLException {
        String sql = "INSERT INTO i_moshuar (mosha, adresa, kontakt_emergjence, userid) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            if (i.getMosha() != null) ps.setInt(1, i.getMosha());
            else ps.setNull(1, Types.INTEGER);
            ps.setString(2, i.getAdresa());
            ps.setString(3, i.getKontaktEmergjence());
            if (i.getUserId() != null) ps.setInt(4, i.getUserId());
            else ps.setNull(4, Types.INTEGER);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) i.setImoshuarId(rs.getInt(1));
            }
        }
        return i;
    }



    @Override
    public Optional<IMoshuar> findById(int id) throws SQLException {
        String sql = "SELECT * FROM i_moshuar WHERE imoshuarId = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(mapRow(rs));
            }
        }
        return Optional.empty();
    }

    @Override
    public List<IMoshuar> findAll() throws SQLException {
        String sql = "SELECT * FROM i_moshuar";
        List<IMoshuar> list = new ArrayList<>();
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        }
        return list;
    }

    @Override
    public IMoshuar update(IMoshuar i) throws SQLException {
        String sql = "UPDATE i_moshuar SET mosha=?, adresa=?, kontakt_emergjence=?, userid=? WHERE imoshuarId=?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            if (i.getMosha() != null) ps.setInt(1, i.getMosha());
            else ps.setNull(1, Types.INTEGER);
            ps.setString(2, i.getAdresa());
            ps.setString(3, i.getKontaktEmergjence());
            if (i.getUserId() != null) ps.setInt(4, i.getUserId());
            else ps.setNull(4, Types.INTEGER);
            ps.setLong(5, i.getImoshuarId());
            ps.executeUpdate();
        }
        return i;
    }



    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM i_moshuar WHERE imoshuarId=?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    private IMoshuar mapRow(ResultSet rs) throws SQLException {
        IMoshuar i = new IMoshuar();
        i.setImoshuarId(rs.getInt("imoshuarId"));
        int m = rs.getInt("mosha");
        i.setMosha(rs.wasNull() ? null : m);
        i.setAdresa(rs.getString("adresa"));
        i.setKontaktEmergjence(rs.getString("kontakt_emergjence"));
        int uid = rs.getInt("userid");
        i.setUserId(rs.wasNull() ? null : uid);
        return i;
    }
}