package service;

import dao.IMoshuarDao;
import model.IMoshuar;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class IMoshuarService {

    private final IMoshuarDao dao;

    public IMoshuarService(IMoshuarDao dao) {
        this.dao = dao;
    }

    public IMoshuar create(IMoshuar i) throws SQLException {
        return dao.create(i);
    }

    public Optional<IMoshuar> findById(int id) throws SQLException {
        return dao.findById(id);
    }


    public List<IMoshuar> findAll() throws SQLException {
        return dao.findAll();
    }

    public IMoshuar update(IMoshuar i) throws SQLException {
        return dao.update(i);
    }

    public void delete(int id) throws SQLException {
        dao.delete(id);
    }
}
