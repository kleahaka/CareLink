package dao;

import model.Vullnetar;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface VullnetarDao {
    Vullnetar create(Vullnetar vullnetar) throws SQLException;
    Optional<Vullnetar> findById(int id) throws SQLException;
    List<Vullnetar> findAll() throws SQLException;
    Vullnetar update(Vullnetar vullnetar) throws SQLException;
    void delete(int id) throws SQLException;
}