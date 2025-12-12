package dao;

import db.DatabaseManager;
import model.KerkeseNdihme;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcKerkeseNdihmeDao implements KerkeseNdihmeDao {

    @Override
    public KerkeseNdihme create(KerkeseNdihme k) throws SQLException {
        String sql = "INSERT INTO kerkesandihme (data_kerkeses, lloji, pershkrimi, data_mbarimi, imoshuarId, vullnetarId, statusiId) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setDate(1, k.getDataKerkeses() != null ? Date.valueOf(k.getDataKerkeses()) : null);
            ps.setString(2, k.getLloji());
            ps.setString(3, k.getPershkrimi());
            ps.setDate(4, k.getDataMbarimi() != null ? Date.valueOf(k.getDataMbarimi()) : null);
            if (k.getImoshuarId() != null) ps.setInt(5, k.getImoshuarId());
            else ps.setNull(5, Types.INTEGER);
            if (k.getVullnetarId() != null) ps.setInt(6, k.getVullnetarId());
            else ps.setNull(6, Types.INTEGER);
            if (k.getStatusiId() != null) ps.setInt(7, k.getStatusiId());
            else ps.setNull(7, Types.INTEGER);

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) k.setKerkeseId(rs.getInt(1));
            }
        }
        return k;
    }

    @Override
    public Optional<KerkeseNdihme> findById(int id) throws SQLException {
        String sql = "SELECT * FROM kerkesandihme WHERE kerkeseId = ?";
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
    public List<KerkeseNdihme> findAll() throws SQLException {
        List<KerkeseNdihme> list = new ArrayList<>();
        String sql = "SELECT * FROM kerkesandihme";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) list.add(mapRow(rs));
        }
        return list;
    }

    @Override
    public KerkeseNdihme update(KerkeseNdihme k) throws SQLException {
        String sql = "UPDATE kerkesandihme SET data_kerkeses=?, lloji=?, pershkrimi=?, data_mbarimi=?, imoshuarId=?, vullnetarId=?, statusiId=? WHERE kerkeseId=?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, k.getDataKerkeses() != null ? Date.valueOf(k.getDataKerkeses()) : null);
            ps.setString(2, k.getLloji());
            ps.setString(3, k.getPershkrimi());
            ps.setDate(4, k.getDataMbarimi() != null ? Date.valueOf(k.getDataMbarimi()) : null);
            if (k.getImoshuarId() != null) ps.setInt(5, k.getImoshuarId());
            else ps.setNull(5, Types.INTEGER);
            if (k.getVullnetarId() != null) ps.setInt(6, k.getVullnetarId());
            else ps.setNull(6, Types.INTEGER);
            if (k.getStatusiId() != null) ps.setInt(7, k.getStatusiId());
            else ps.setNull(7, Types.INTEGER);
            ps.setInt(8, k.getKerkeseId());

            ps.executeUpdate();
        }
        return k;
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM kerkesandihme WHERE kerkeseId = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private KerkeseNdihme mapRow(ResultSet rs) throws SQLException {
        KerkeseNdihme k = new KerkeseNdihme();
        k.setKerkeseId(rs.getInt("kerkeseId"));

        Date dKerkes = rs.getDate("data_kerkeses");
        k.setDataKerkeses(dKerkes != null ? dKerkes.toLocalDate() : null);

        k.setLloji(rs.getString("lloji"));
        k.setPershkrimi(rs.getString("pershkrimi"));

        Date dMbarimi = rs.getDate("data_mbarimi");
        k.setDataMbarimi(dMbarimi != null ? dMbarimi.toLocalDate() : null);

        int im = rs.getInt("imoshuarId");
        k.setImoshuarId(rs.wasNull() ? null : im);
        int vId = rs.getInt("vullnetarId");
        if (!rs.wasNull()) k.setVullnetarId(vId);

        int s = rs.getInt("statusiId");
        k.setStatusiId(rs.wasNull() ? null : s);
        return k;
    }
}
