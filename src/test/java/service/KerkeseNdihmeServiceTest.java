package service;

import dao.KerkeseNdihmeDao;
import dao.JdbcKerkeseNdihmeDao;
import model.KerkeseNdihme;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import support.DatabaseTestBase;

import java.sql.SQLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class KerkeseNdihmeServiceTest extends DatabaseTestBase {

    private KerkeseNdihmeService service;
    private KerkeseNdihmeDao dao;

    @BeforeEach
    public void setUp() {
        dao = new JdbcKerkeseNdihmeDao();
        service = new KerkeseNdihmeService(dao);
    }

    @Test
    public void create_and_list_and_get_request() throws SQLException {
        KerkeseNdihme k = new KerkeseNdihme();
        k.setImoshuarId(10);
        k.setVullnetarId(20);
        dao.create(k);

        List<KerkeseNdihme> forUser = service.listRequestsForUser(10);
        assertEquals(1, forUser.size());

        List<KerkeseNdihme> forVolunteer = service.listRequestsForVolunteer(20);
        assertEquals(1, forVolunteer.size());

        Optional<KerkeseNdihme> got = service.getRequest(k.getKerkeseId());
        assertTrue(got.isPresent());
    }

    @Test
    public void updateStatus_updates_when_found_and_throws_when_missing() throws SQLException {
        KerkeseNdihme k = new KerkeseNdihme();
        k.setImoshuarId(1);
        dao.create(k);
        int id = k.getKerkeseId();

        KerkeseNdihme updated = service.updateStatus(id, 5);
        assertEquals(5, updated.getStatusiId());

        assertThrows(IllegalArgumentException.class, () -> service.updateStatus(9999, 2));
    }


    @Test
    public void listRequestsForUser_ignores_null_imoshuarId_and_non_matching_ids() throws SQLException {
        // 1) null imoshuarId (duhet të filtrohet jashtë)
        KerkeseNdihme kNull = new KerkeseNdihme();
        kNull.setImoshuarId(null);
        kNull.setVullnetarId(1);
        dao.create(kNull);

        // 2) imoshuarId jo-null, por jo i njëjtë (duhet të filtrohet jashtë)
        KerkeseNdihme kOther = new KerkeseNdihme();
        kOther.setImoshuarId(999);
        kOther.setVullnetarId(1);
        dao.create(kOther);

        // 3) i saktë (duhet të kthehet)
        KerkeseNdihme kMatch = new KerkeseNdihme();
        kMatch.setImoshuarId(10);
        kMatch.setVullnetarId(1);
        dao.create(kMatch);

        List<KerkeseNdihme> result = service.listRequestsForUser(10);
        assertEquals(1, result.size());
    }

    @Test
    public void listRequestsForVolunteer_ignores_null_vullnetarId_and_non_matching_ids() throws SQLException {
        // 1) null vullnetarId
        KerkeseNdihme kNull = new KerkeseNdihme();
        kNull.setImoshuarId(1);
        kNull.setVullnetarId(null);
        dao.create(kNull);

        // 2) vullnetarId jo-null por mismatch
        KerkeseNdihme kOther = new KerkeseNdihme();
        kOther.setImoshuarId(1);
        kOther.setVullnetarId(999);
        dao.create(kOther);

        // 3) match
        KerkeseNdihme kMatch = new KerkeseNdihme();
        kMatch.setImoshuarId(1);
        kMatch.setVullnetarId(20);
        dao.create(kMatch);

        List<KerkeseNdihme> result = service.listRequestsForVolunteer(20);
        assertEquals(1, result.size());
    }
    @Test
    public void createRequest_via_service_is_covered() throws SQLException {
        KerkeseNdihme k = new KerkeseNdihme();
        k.setImoshuarId(10);
        k.setVullnetarId(20);

        KerkeseNdihme created = service.createRequest(k);
        assertNotNull(created);
    }
}
