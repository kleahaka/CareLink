package service;

import dao.KerkeseNdihmeDao;
import model.KerkeseNdihme;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class KerkeseNdihmeService {

    private final KerkeseNdihmeDao dao;

    public KerkeseNdihmeService(KerkeseNdihmeDao dao) {
        this.dao = dao;
    }

    // KP3 – Krijim kërkese
    public KerkeseNdihme createRequest(KerkeseNdihme k) throws SQLException {
        return dao.create(k);
    }

    public List<KerkeseNdihme> listRequestsForUser(int imoshuarId) throws SQLException {
        return dao.findAll().stream()
                .filter(k -> k.getImoshuarId() != null && k.getImoshuarId() == imoshuarId)
                .collect(Collectors.toList());
    }

    public List<KerkeseNdihme> listRequestsForVolunteer(int vullnetarId) throws SQLException {
        return dao.findAll().stream()
                .filter(k -> k.getVullnetarId() != null && k.getVullnetarId() == vullnetarId)
                .collect(Collectors.toList());
    }

    public KerkeseNdihme updateStatus(int requestId, int statusId) throws SQLException {
        Optional<KerkeseNdihme> k = dao.findById(requestId);
        if (k.isPresent()) {
            k.get().setStatusiId(statusId);
            return dao.update(k.get());
        }
        throw new IllegalArgumentException("Request not found");
    }

    public Optional<KerkeseNdihme> getRequest(int requestId) throws SQLException {
        return dao.findById(requestId);
    }
}
