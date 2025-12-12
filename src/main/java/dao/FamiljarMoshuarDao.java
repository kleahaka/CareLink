package dao;

import model.FamiljarMoshuar;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface FamiljarMoshuarDao {
    FamiljarMoshuar create(FamiljarMoshuar fm) throws SQLException;

    // Për composite key, përdorim dy parametra
    Optional<FamiljarMoshuar> findById(int familjarId, int imoshuarId) throws SQLException;

    List<FamiljarMoshuar> findAll() throws SQLException;

    FamiljarMoshuar update(FamiljarMoshuar fm) throws SQLException;

    void delete(int familjarId, int imoshuarId) throws SQLException;
}
