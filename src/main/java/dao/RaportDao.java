package dao;


import model.Raport;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface RaportDao {
    Raport create(Raport raport) throws SQLException;
    Optional<Raport> findById(int id) throws SQLException;
    List<Raport> findAll() throws SQLException;
    Raport update(Raport raport) throws SQLException;
    void delete(int id) throws SQLException;
}
