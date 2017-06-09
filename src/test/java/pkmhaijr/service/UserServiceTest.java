package pkmhaijr.service;

import lombok.extern.log4j.Log4j2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pkmhaijr.model.dbEntities.Address;
import pkmhaijr.model.dbEntities.CreditCard;
import pkmhaijr.model.dbEntities.User;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by anastasiia on 5/16/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Log4j2
public class UserServiceTest {
    @Autowired UserService userService;
    @Autowired AddressService addressService;
    @Autowired CreditCardService creditCardService;

    private User user1;
    private User user2;
    private User user3;

    private Address address1;
    private Address address2;
    private Address address3;

    private CreditCard card1;
    private  CreditCard card2;
    private  CreditCard card3;

    @Before
    public void setUp(){

        address1 = new Address();
        address1.setStreet("Street1");
        address1.setPostalCode("Code1");
        address1.setHouseNumber("1");
        address1.setCountry("Country1");
        address1.setCity("City1");
        address1.setApartmentNumber("1");

        address2 = new Address();
        address2.setStreet("Street2");
        address2.setPostalCode("Code2");
        address2.setHouseNumber("2");
        address2.setCountry("Country2");
        address2.setCity("City2");
        address2.setApartmentNumber("2");

        address3 = new Address();
        address3.setStreet("Street3");
        address3.setPostalCode("Code3");
        address3.setHouseNumber("3");
        address3.setCountry("Country3");
        address3.setCity("City3");
        address3.setApartmentNumber("3");

        Set<Address> addressSet1 = new LinkedHashSet<>();
        addressSet1.add(address1);
        Set<Address> addressSet2 = new LinkedHashSet<>();
        addressSet2.add(address2);
        Set<Address> addressSet3 = new LinkedHashSet<>();
        addressSet3.add(address3);

        card1 = new CreditCard();
        card1.setNumber("1");
        card1.setOwner("Owner1");

        card2 = new CreditCard();
        card2.setNumber("2");
        card2.setOwner("Owner2");

        card3 = new CreditCard();
        card3.setNumber("3");
        card3.setOwner("Owner3");

        Set<CreditCard> cardSet1 = new LinkedHashSet<>();
        cardSet1.add(card1);
        Set<CreditCard> cardSet2 = new LinkedHashSet<>();
        cardSet2.add(card2);
        Set<CreditCard> cardSet3 = new LinkedHashSet<>();
        cardSet3.add(card3);


        user1 = new User();
        user1.setFirstName("User1FirstName");
        user1.setLastName("User1LastName");
        user1.setAddresses(addressSet1);
        user1.setCards(cardSet2);

        user2 = new User();
        user2.setFirstName("User2FirstName");
        user2.setLastName("User2LastName");
        user2.setAddresses(addressSet2);
        user2.setCards(cardSet2);

        user3 = new User();
        user3.setFirstName("User3FirstName");
        user3.setLastName("User3LastName");
        user3.setAddresses(addressSet3);
        user3.setCards(cardSet3);
    }

    private void addAllUsers(){
        addressService.addAddress(address1);
        addressService.addAddress(address2);
        addressService.addAddress(address3);

        creditCardService.addCreditCard(card1);
        creditCardService.addCreditCard(card2);
        creditCardService.addCreditCard(card3);

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
    public void deleteUserTest(){
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
