package service;

import dao.IMoshuarDao;
import dao.JdbcIMoshuarDao;
import model.IMoshuar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import support.DatabaseTestBase;

import java.sql.SQLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class IMoshuarServiceTest extends DatabaseTestBase {

    private IMoshuarService service;
    private IMoshuarDao dao;

    @BeforeEach
    public void setUp() {
        dao = new JdbcIMoshuarDao();
        service = new IMoshuarService(dao);
    }

    @Test
    public void create_find_update_delete_flow() throws SQLException {
        IMoshuar i = new IMoshuar();
        i.setAdresa("ElderAddress");
        dao.create(i);
        int id = i.getImoshuarId();

        Optional<IMoshuar> got = service.findById(id);
        assertTrue(got.isPresent());

        List<IMoshuar> all = service.findAll();
        assertEquals(1, all.size());

        IMoshuar m = got.get();
        m.setAdresa("NewAddress");
        IMoshuar updated = service.update(m);
        assertEquals("NewAddress", updated.getAdresa());

        service.delete(id);
        assertTrue(service.findById(id).isEmpty());
    }
}
