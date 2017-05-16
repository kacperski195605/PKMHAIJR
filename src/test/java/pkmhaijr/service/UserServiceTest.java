package pkmhaijr.service;

import lombok.extern.log4j.Log4j2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pkmhaijr.model.dbEntities.User;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by anastasiia on 5/16/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Log4j2
public class UserServiceTest {
    @Autowired UserService userService;

    private User user1;
    private User user2;
    private User user3;

    @Before
    public void setUp(){
        user1 = new User();
        user1.setFirstName("User1FirstName");
        user1.setLastName("User1LastName");
        //TODO  setAddresses, setCards???

        user2 = new User();
        user2.setFirstName("User2FirstName");
        user2.setLastName("User2LastName");

        user3 = new User();
        user3.setFirstName("User3FirstName");
        user3.setLastName("User3LastName");
    }

    private void addAllUsers(){
        userService.addUser(user1);
        userService.addUser(user2);
        userService.addUser(user3);
    }

    @Test
    public void findUsersByIdTest(){
        log.info("Testing finding user by id");
        //preparation
        user1 = userService.addUser(user1);

        //action
        User newUser = userService.findUserByd(user1.getId());

        //assertion
        assertNotNull("User should not be null", newUser);
        assertEquals("User should be equal", user1, newUser);
    }

    @Test
    public void countOfEmptyDatabaseTest(){
        log.info("Testing count of empty database");
        //preparation
        int expectedCount = 0;

        //action
        int actualCount = userService.countUsers();

        //assertion
        assertEquals("Count should be equal to 0", expectedCount, actualCount);
    }

    @Test
    public void countOfNotEmptyDatabaseTest(){
        log.info("Testing count of not empty database");
        //preparation
        addAllUsers();
        int expectedCount = 3;

        //action
        int actualCount = userService.countUsers();

        //assertion
        assertEquals("Count should be equal to 3", expectedCount, actualCount);
    }

    @Test
    public void findAllUsersTest(){
        log.info("Testing finding all users");
        //preparation
        addAllUsers();
        int expectedCount = 3;

        //action
        List<User> users = userService.findAllUsers();

        //assertion
        assertNotNull("List should not be null", users);
        assertEquals("List should have a length of 3", expectedCount, users.size());

        assertTrue("List should contain user 1", users.contains(user1));
        assertTrue("List should contain user 2", users.contains(user2));
        assertTrue("List should contain user 3", users.contains(user3));
    }

    @Test
    public void deleteAddressTest(){
        log.info("Testing deleting user");
        //preparation
        user1 = userService.addUser(user1);
        int expectedCount = 0;

        //action
        userService.deleteUser(user1);
        int actualCount = userService.countUsers();
        User newUser = userService.findUserByd(user1.getId());

        //assertion
        assertEquals("Count should be equal to 0", expectedCount, actualCount);
        assertNull("newUser should be null", newUser);
    }

    @Test
    public void validUpdateUser(){
        log.info("Testing valid updating user");
        //preparation
        user1 = userService.addUser(user1);
        user1.setLastName("User1 new LastName");

        //action
        userService.updateUser(user1);
        User newUser = userService.findUserByd(user1.getId());

        //assertion
        assertNotNull("New user should not be null", newUser);
        assertEquals("New user should have updated lastName field", user1.getLastName(), newUser.getLastName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidUpdateUser(){
        log.info("Testing invalid updating user");
        //preparation
        user1 = userService.addUser(user1);
        userService.deleteUser(user1);

        //action
        userService.updateUser(user1);
    }

    @Test
    public void deleteAllUsersTest(){
        log.info("Testing deleting all users");
        //preparation
        addAllUsers();
        int expectedCount = 0;

        //action
        userService.deleteAllUsers();
        int actualCount = userService.countUsers();

        //assertion
        assertEquals("Count should be equal to 0", expectedCount, actualCount);
    }

    @After
    public void clean(){
        userService.deleteAllUsers();
    }
}
