package service;

import dao.JdbcVullnetarDao;
import dao.VullnetarDao;
import model.Vullnetar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import support.DatabaseTestBase;

import java.sql.SQLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class VullnetarServiceTest extends DatabaseTestBase {

    private VullnetarService service;
    private VullnetarDao dao;

    @BeforeEach
    public void setUp() {
        dao = new JdbcVullnetarDao();
        service = new VullnetarService(dao);
    }

    @Test
    public void create_login_list_update_delete_flow() throws SQLException {
        Vullnetar v = new Vullnetar();
        v.setUserId(55);
        dao.create(v);

        Optional<Vullnetar> login = service.login(55);
        assertTrue(login.isPresent());

        List<Vullnetar> all = service.listAll();
        assertEquals(1, all.size());

        Vullnetar toUpdate = login.get();
        toUpdate.setDisponueshmeria("updated");
        Vullnetar updated = service.update(toUpdate);
        assertEquals("updated", updated.getDisponueshmeria());

        service.delete(updated.getVullnetarId());
        assertTrue(service.listAll().isEmpty());
    }

    @Test
    public void login_returns_empty_when_missing() throws SQLException {
        Optional<Vullnetar> login = service.login(9999);
        assertTrue(login.isEmpty());
    }
}
