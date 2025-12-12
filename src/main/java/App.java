import dao.*;
import model.*;
import service.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class App {
    public static void main(String[] args) {
        try {
            // DAO
            JdbcUserDao userDao = new JdbcUserDao();
            JdbcVullnetarDao vullnetarDao = new JdbcVullnetarDao();
            JdbcKerkeseNdihmeDao kerkeseDao = new JdbcKerkeseNdihmeDao();
            JdbcRaportDao raportDao = new JdbcRaportDao();
            JdbcFamiljarDao familjarDao = new JdbcFamiljarDao();
            JdbcFamiljarMoshuarDao fmDao = new JdbcFamiljarMoshuarDao();

            // Servis
            UserService userService = new UserService(userDao);
            VullnetarService vullnetarService = new VullnetarService(vullnetarDao);
            KerkeseNdihmeService kerkeseService = new KerkeseNdihmeService(kerkeseDao);
            RaportService raportService = new RaportService(raportDao);
            FamiljarService familjarService = new FamiljarService(familjarDao);
            FamiljarMoshuarService fmService = new FamiljarMoshuarService(fmDao);

            // --- TESTIMI USER ---
            User user = userService.createUser("Erjona", "erjona@example.com");
            System.out.println("User i krijuar: " + user);

            Optional<User> u = userService.getUserById(user.getUserId());
            System.out.println("Gjej user by ID: " + u);

            // --- TESTIMI VULLNETAR ---
            Vullnetar v = new Vullnetar(null, "Tirana", "Cdo dite 09-12", user.getUserId());
            v = vullnetarService.create(v);
            System.out.println("Vullnetar i krijuar: " + v);

            // --- TESTIMI KERKESE NDIHME ---
            KerkeseNdihme k = new KerkeseNdihme(null, LocalDate.now(), "Vizite mjekesore", "Shoqerim ne spital", null, user.getUserId(), v.getVullnetarId(), 1);
            k = kerkeseService.createRequest(k);
            System.out.println("Kerkese ndihme e krijuar: " + k);

            List<KerkeseNdihme> kerkeseList = kerkeseService.listRequestsForVolunteer(v.getVullnetarId());
            System.out.println("Lista e kerkeseve per vullnetar: " + kerkeseList);

            // --- TESTIMI RAPORT ---
            Raport r = new Raport(null, LocalDate.now(), "Suksesshëm", "U krye vizita", k.getKerkeseId());
            r = raportService.createReport(r);
            System.out.println("Raporti i krijuar: " + r);

            // --- TESTIMI FAMILJAR ---
            Familjar f = new Familjar(null, "Vajza", "+35569111222", user.getUserId());
            f = familjarService.create(f);
            System.out.println("Familjar i krijuar: " + f);

            // --- TESTIMI FAMILJAR MOSHUAR ---
            FamiljarMoshuar fm = new FamiljarMoshuar(f.getFamiljarId(), user.getUserId(), "Vajze");
            fm = fmService.create(fm);
            System.out.println("FamiljarMoshuar i krijuar: " + fm);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
