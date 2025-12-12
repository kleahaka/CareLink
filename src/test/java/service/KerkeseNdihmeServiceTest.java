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
}
