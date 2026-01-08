package service;
import dao.FamiljarMoshuarDao;
import dao.JdbcFamiljarMoshuarDao;
import model.FamiljarMoshuar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import support.DatabaseTestBase;
import java.sql.SQLException;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
public class FamiljarMoshuarServiceTest extends DatabaseTestBase {

    private FamiljarMoshuarService service;
    private FamiljarMoshuarDao dao;

    @BeforeEach
    public void setUp() {
        dao = new JdbcFamiljarMoshuarDao();
        service = new FamiljarMoshuarService(dao);
    }

    @Test
    public void create_getAll_getById_update_delete() throws SQLException {
        FamiljarMoshuar fm = new FamiljarMoshuar();
        fm.setFamiljarId(1);
        fm.setImoshuarId(2);
        fm.setLidhjaFM("x");
        dao.create(fm);

        List<FamiljarMoshuar> all = service.getAll();
        assertEquals(1, all.size());

        Optional<FamiljarMoshuar> got = service.getById(1,2);
        assertTrue(got.isPresent());

        fm.setLidhjaFM("y");
        FamiljarMoshuar updated = service.update(fm);
        assertEquals("y", updated.getLidhjaFM());

        service.delete(1,2);
        assertTrue(service.getById(1,2).isEmpty());
    }
    @Test
    public void create_via_service_is_covered() throws SQLException {
        FamiljarMoshuar fm = new FamiljarMoshuar();
        fm.setFamiljarId(1);
        fm.setImoshuarId(2);
        fm.setLidhjaFM("x");

        FamiljarMoshuar created = service.create(fm);
        assertNotNull(created);
    }
}
