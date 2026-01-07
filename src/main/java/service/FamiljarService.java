package service;

import dao.FamiljarDao;
import model.Familjar;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class FamiljarService {

    private final FamiljarDao dao;

    public FamiljarService(FamiljarDao dao){
        this.dao = dao;
    }
    public Familjar create(Familjar f) throws SQLException {
        return dao.create(f);
    }

    //komenti
    public Familjar linkToElder(Familjar f) throws SQLException{
        return dao.create(f);
    }

    public List <Familjar> listAll() throws SQLException{
        return dao.findAll();
    }
//
    public Optional <Familjar> getById(int id) throws SQLException{
        return dao.findById(id);
    }

    public Familjar update(Familjar f) throws SQLException{
        return dao.update(f);
    }

    public void delete(int id) throws SQLException{
        dao.delete(id);
    }
}
