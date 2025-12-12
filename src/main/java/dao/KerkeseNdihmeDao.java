package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import model.KerkeseNdihme;

public interface KerkeseNdihmeDao {
    KerkeseNdihme create(KerkeseNdihme k) throws SQLException;
    Optional<KerkeseNdihme> findById(int id) throws SQLException;
    List<KerkeseNdihme> findAll() throws SQLException;
    KerkeseNdihme update(KerkeseNdihme k) throws SQLException;
    void delete(int id) throws SQLException;
}
