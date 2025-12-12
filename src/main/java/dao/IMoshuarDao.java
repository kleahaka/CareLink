package dao;

import model.IMoshuar;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IMoshuarDao {
    IMoshuar create(IMoshuar iMoshuar) throws SQLException;
    Optional<IMoshuar> findById(int id) throws SQLException;
    List<IMoshuar> findAll() throws SQLException;
    IMoshuar update(IMoshuar iMoshuar) throws SQLException;
    void delete(int id) throws SQLException;
}