package service;

import dao.JdbcRaportDao;
import dao.RaportDao;
import model.Raport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import support.DatabaseTestBase;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class RaportServiceTest extends DatabaseTestBase {

    private RaportService service;
    private RaportDao dao;

    @BeforeEach
    public void setUp() {
        dao = new JdbcRaportDao();
        service = new RaportService(dao);
    }

    @Test
    public void create_get_list_update_delete() throws SQLException{
        Raport r = new Raport();
        r.setRezultati("ok");
        r.setDataRaportit(LocalDate.now());
        dao.create(r);

        Optional<Raport> got = service.getReport(r.getRaportId());
        assertTrue(got.isPresent());

        List<Raport> all = service.listReports();
        assertEquals(1, all.size());

        r.setRezultati("changed");
        Raport updated = service.updateReport(r);
        assertEquals("changed", updated.getRezultati());

        service.deleteReport(r.getRaportId());
        assertTrue(service.getReport(r.getRaportId()).isEmpty());
    }
    @Test
    public void createReport_via_service_is_covered() throws SQLException {
        Raport r = new Raport();
        r.setRezultati("ok");
        r.setDataRaportit(java.time.LocalDate.now());

        Raport created = service.createReport(r);
        assertNotNull(created);
    }
}
