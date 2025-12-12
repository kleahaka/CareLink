package service;

import dao.VullnetarDao;
import model.Vullnetar;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class VullnetarService {

    private final VullnetarDao dao;

    public VullnetarService(VullnetarDao dao) {
        this.dao = dao;
    }
    public Vullnetar create(Vullnetar v) throws SQLException {
        return dao.create(v);
    }


    // Login ose gjetja sipas userId
    public Optional<Vullnetar> login(int userId) throws SQLException {
        return dao.findAll().stream()
                .filter(v -> v.getUserId() != null && v.getUserId() == userId)
                .findFirst();
    }

    public List<Vullnetar> listAll() throws SQLException {
        return dao.findAll();
    }

    public Vullnetar update(Vullnetar v) throws SQLException {
        return dao.update(v);
    }

    public void delete(int id) throws SQLException {
        dao.delete(id);
    }
}
