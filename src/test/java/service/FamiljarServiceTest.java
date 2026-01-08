package service;

import dao.FamiljarDao;
import dao.JdbcFamiljarDao;
import model.Familjar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import support.DatabaseTestBase;

import java.sql.SQLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class FamiljarServiceTest extends DatabaseTestBase {

    private FamiljarService service;
    private FamiljarDao dao;

    @BeforeEach
    public void setUp() {
        dao = new JdbcFamiljarDao();
        service = new FamiljarService(dao);
    }

    @Test
    public void create_and_link_list_get_update_delete() throws SQLException {
        Familjar f = new Familjar();
        f.setKontakt("k");
        Familjar linked = service.linkToElder(f);
        int id = linked.getFamiljarId();

        List<Familjar> all = service.listAll();
        assertEquals(1, all.size());

        Optional<Familjar> byId = service.getById(id);
        assertTrue(byId.isPresent());

        Familjar modified = byId.get();
        modified.setKontakt("newk");
        Familjar updated = service.update(modified);
        assertEquals("newk", updated.getKontakt());

        service.delete(id);
        assertTrue(service.getById(id).isEmpty());
    }
    @Test
    public void create_via_service_is_covered() throws SQLException {
        Familjar f = new Familjar();
        f.setKontakt("k");

        Familjar created = service.create(f);
        assertNotNull(created);
    }
}
