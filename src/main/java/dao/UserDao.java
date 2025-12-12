package dao;

import model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    User create(User user) throws SQLException;
    Optional<User> findById(int id) throws SQLException;
    List<User> findAll() throws SQLException;
    User update(User user) throws SQLException;
    void delete(int id) throws SQLException;
    Optional<User> findByEmail(String email) throws SQLException;
}
