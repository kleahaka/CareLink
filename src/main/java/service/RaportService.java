package service;

import dao.RaportDao;
import model.Raport;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class RaportService {

    private final RaportDao dao;

    public RaportService(RaportDao dao){
        this.dao = dao;
    }

    public Raport createReport(Raport r) throws SQLException{
        return dao.create(r);
    }

    public Optional <Raport> getReport(int reportId) throws SQLException{
        return dao.findById(reportId);
    }

    public List <Raport> listReports() throws SQLException{
        return dao.findAll();
    }

    public Raport updateReport(Raport r) throws SQLException{
        return dao.update(r);
    }

    public void deleteReport(int reportId) throws SQLException{
        dao.delete(reportId);
    }
}
