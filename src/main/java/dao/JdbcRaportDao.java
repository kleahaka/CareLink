package dao;

import db.DatabaseManager;
import model.Raport;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcRaportDao implements RaportDao {

    @Override
    public Raport create(Raport r) throws SQLException {
        String sql = "INSERT INTO raport (data_raportit, rezultati, pershkrimi, kerkeseId) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setDate(1, r.getDataRaportit() != null ? Date.valueOf(r.getDataRaportit()) : null);
            ps.setString(2, r.getRezultati());
            ps.setString(3, r.getPershkrimi());
            if (r.getKerkeseId() != null) ps.setInt(4, r.getKerkeseId());
            else ps.setNull(4, Types.INTEGER);

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) r.setRaportId(rs.getInt(1));
            }
        }
        return r;
    }

    @Override
    public Optional<Raport> findById(int id) throws SQLException {
        String sql = "SELECT * FROM raport WHERE raportId = ?";
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
    public List<Raport> findAll() throws SQLException {
        List<Raport> list = new ArrayList<>();
        String sql = "SELECT * FROM raport";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) list.add(mapRow(rs));
        }
        return list;
    }

    @Override
    public Raport update(Raport r) throws SQLException {
        String sql = "UPDATE raport SET data_raportit=?, rezultati=?, pershkrimi=?, kerkeseId=? WHERE raportId=?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, r.getDataRaportit() != null ? Date.valueOf(r.getDataRaportit()) : null);
            ps.setString(2, r.getRezultati());
            ps.setString(3, r.getPershkrimi());
            if (r.getKerkeseId() != null) ps.setInt(4, r.getKerkeseId());
            else ps.setNull(4, Types.INTEGER);
            ps.setInt(5, r.getRaportId());

            ps.executeUpdate();
        }
        return r;
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM raport WHERE raportId = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private Raport mapRow(ResultSet rs) throws SQLException {
        Raport r = new Raport();
        r.setRaportId(rs.getInt("raportId"));

        Date dRaport = rs.getDate("data_raportit");
        r.setDataRaportit(dRaport != null ? dRaport.toLocalDate() : null);

        r.setRezultati(rs.getString("rezultati"));
        r.setPershkrimi(rs.getString("pershkrimi"));
        int k = rs.getInt("kerkeseId");
        r.setKerkeseId(rs.wasNull() ? null : k);
        return r;
    }
}
