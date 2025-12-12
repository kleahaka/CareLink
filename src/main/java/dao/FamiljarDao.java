package dao;


import model.Familjar;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface FamiljarDao {
    Familjar create(Familjar f) throws SQLException;
    Optional<Familjar> findById(int id) throws SQLException;
    List<Familjar> findAll() throws SQLException;
    Familjar update(Familjar f) throws SQLException;
    void delete(int id) throws SQLException;
}