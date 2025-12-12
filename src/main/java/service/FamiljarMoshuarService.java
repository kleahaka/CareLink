package service;

import dao.FamiljarMoshuarDao;
import model.FamiljarMoshuar;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class FamiljarMoshuarService {

    private final FamiljarMoshuarDao dao;

    public FamiljarMoshuarService(FamiljarMoshuarDao dao) {
        this.dao = dao;
    }

    // Krijon një lidhje të re midis familjarit dhe të moshuarit
    public FamiljarMoshuar create(FamiljarMoshuar fm) throws SQLException {
        return dao.create(fm);
    }

    // Merr një lidhje sipas familjarId dhe imoshuarId
    public Optional<FamiljarMoshuar> getById(int familjarId, int imoshuarId) throws SQLException {
        return dao.findById(familjarId, imoshuarId);
    }

    // Liston të gjitha lidhjet
    public List<FamiljarMoshuar> getAll() throws SQLException {
        return dao.findAll();
    }

    // Përditëson një lidhje ekzistuese
    public FamiljarMoshuar update(FamiljarMoshuar fm) throws SQLException {
        return dao.update(fm);
    }

    // Fshin një lidhje sipas familjarId dhe imoshuarId
    public void delete(int familjarId, int imoshuarId) throws SQLException {
        dao.delete(familjarId, imoshuarId);
    }
}
