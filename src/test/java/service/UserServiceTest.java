package service;

import dao.JdbcUserDao;
import dao.UserDao;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import support.DatabaseTestBase;

import java.sql.SQLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest extends DatabaseTestBase {

    private UserService service;
    private UserDao dao;

    @BeforeEach
    public void setUp() {
        dao = new JdbcUserDao();
        service = new UserService(dao);
    }

    @Test
    public void create_and_find_and_update_user() throws SQLException {
        User u = new User();
        u.setEmri("John");
        u.setEmail("john@example.com");
        u.setPassword("secret");
        dao.create(u);

        Optional<User> found = service.getUserById(u.getUserId());
        assertTrue(found.isPresent());

        List<User> all = service.listAllUsers();
        assertEquals(1, all.size());

        u.setEmri("Johnny");
        User updated = service.updateUser(u);
        assertEquals("Johnny", updated.getEmri());

        service.deleteUser(u.getUserId());
        assertFalse(service.getUserById(u.getUserId()).isPresent());
    }

    @Test
    public void register_and_login_and_resetPassword() throws SQLException {
        User u = new User();
        u.setEmri("Alice");
        u.setEmail("alice@example.com");
        u.setPassword("pw");

        User created = service.register(u);
        assertNotNull(created.getUserId());

        Optional<User> login = service.login("alice@example.com", "pw");
        assertTrue(login.isPresent());

        // registering same email should fail
        assertThrows(IllegalArgumentException.class, () -> service.register(u));

        // reset password and verify login with new password
        service.resetPassword(created.getUserId(), "newpw");
        Optional<User> loginNew = service.login("alice@example.com", "newpw");
        assertTrue(loginNew.isPresent());
    }

    @Test
    public void createUser_and_logout_and_login_failure() throws SQLException {
        // createUser uses default password; ensure it returns created user
        User u = service.createUser("TestName", "test@example.com");
        assertNotNull(u.getUserId());

        // logout is a no-op (should not throw)
        service.logout(u);

        // login failure with wrong password returns empty
        Optional<User> badLogin = service.login("test@example.com", "wrong");
        assertTrue(badLogin.isEmpty());
    }

    @Test
    public void resetPassword_throws_when_user_missing() {
        assertThrows(IllegalArgumentException.class, () -> service.resetPassword(999, "x"));
    }

    @Test
    public void list_get_update_delete_verify_flow() throws SQLException {
        User u = new User();
        u.setEmail("one@one.com");
        u.setEmri("One");
        dao.create(u);
        int id = u.getUserId();

        List<User> all = service.listAllUsers();
        assertEquals(1, all.size());

        Optional<User> byId = service.getUserById(id);
        assertTrue(byId.isPresent());

        User modified = byId.get();
        modified.setEmri("Changed");
        User updated = service.updateUser(modified);
        assertEquals("Changed", updated.getEmri());

        Optional<User> verified = service.verifyUser(id);
        assertTrue(verified.isPresent());
        assertTrue(verified.get().isVerified());

        service.deleteUser(id);
        assertTrue(service.getUserById(id).isEmpty());
    }
}
