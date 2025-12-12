package service;

import dao.UserDao;
import model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }
    public User createUser(String emri, String email) throws SQLException {
        User u = new User();
        u.setEmri(emri);
        u.setEmail(email);
        u.setPassword("default123");
        return userDao.create(u);
    }


    public User register(User user) throws SQLException {
        Optional<User> existing = userDao.findByEmail(user.getEmail());
        if (existing.isPresent()) throw new IllegalArgumentException("Email already exists!");
        return userDao.create(user);
    }

    // KP2 – Login
    public Optional<User> login(String email, String password) throws SQLException {
        Optional<User> userOpt = userDao.findByEmail(email);
        if (userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {
            return userOpt;
        }
        return Optional.empty();
    }

    // KP2.1 – Rivendosje fjalëkalimi
    public void resetPassword(int userId, String newPassword) throws SQLException {
        Optional<User> userOpt = userDao.findById(userId);
        if (userOpt.isPresent()) {
            User u = userOpt.get();
            u.setPassword(newPassword);
            userDao.update(u);
        } else throw new IllegalArgumentException("User not found");
    }

    // KP2.2 – Logout
    public void logout(User user) {
    }

    // KP14, KP18 – CRUD & Verifikim
    public List<User> listAllUsers() throws SQLException{
        return userDao.findAll();
    }

    public Optional<User> getUserById(int userId) throws SQLException{
        return userDao.findById(userId);
    }

    public User updateUser(User user) throws SQLException{
        return userDao.update(user);
    }

    public void deleteUser(int userId) throws SQLException{
        userDao.delete(userId);
    }

    public Optional<User> verifyUser(int userId) throws SQLException {
        Optional<User> u = userDao.findById(userId);
        if (u.isPresent()) {
            u.get().setVerified(true);
            userDao.update(u.get());
            return u;
        }
        return Optional.empty();
    }
}
