package pkmhaijr.service;

import lombok.extern.log4j.Log4j2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pkmhaijr.model.dbEntities.CreditCard;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by margo on 5/15/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Log4j2
public class CreditCardServiceTest {

    @Autowired CreditCardService creditCardService;

    private CreditCard card1;
    private CreditCard card2;
    private CreditCard card3;

    @Before
    public void setUp() {
        card1 = new CreditCard();
        card1.setNumber("1");
        card1.setOwner("Owner1");

        card2 = new CreditCard();
        card2.setNumber("2");
        card2.setOwner("Owner2");

        card3 = new CreditCard();
        card3.setNumber("3");
        card3.setOwner("Owner3");
    }

    private void addAllCreditCards() {
        creditCardService.addCreditCard(card1);
        creditCardService.addCreditCard(card2);
        creditCardService.addCreditCard(card3);
    }

    @Test
    public void findCreditCardByIdTest() {
        log.info("Testing finding a credit card by id");
        //preparation
        card1 = creditCardService.addCreditCard(card1);

        //action
        CreditCard newCard = creditCardService.findCreditCardById(card1.getId());

        //assertion
        assertNotNull("Credit card should not be null", newCard);
        assertEquals("Credit cards should be equal", card1, newCard);
    }

    @Test
    public void countOfEmptyDatabaseTest() {
        log.info("Testing count of empty database");
        //preparation
        int expectedCount = 0;

        //action
        int actualCount = creditCardService.countCreditCards();

        //assertion
        assertEquals("Count should be equal to 0", expectedCount, actualCount);
    }

    @Test
    public void countOfNotEmptyDatabaseTest() {
        log.info("Testing count of not empty database");
        //preparation
        addAllCreditCards();
        int expectedCount = 3;

        //action
        int actualCount = creditCardService.countCreditCards();

        //assertion
        assertEquals("Count should be equal to 3", expectedCount, actualCount);
    }

    @Test
    public void findAllCreditCardsTest() {
        log.info("Testing finding all credit cards");
        //preparation
        addAllCreditCards();
        int expectedCount = 3;

        //action
        List<CreditCard> cards = creditCardService.findAllCreditCards();

        //assertion
        assertNotNull("List should not be null", cards);
        assertEquals("List should have a length of 3", expectedCount, cards.size());
        //TODO: change these 3 into something nicer
        assertTrue("List should contain address 1", cards.contains(card1));
        assertTrue("List should contain address 2", cards.contains(card2));
        assertTrue("List should contain address 3", cards.contains(card3));
    }

    @Test
    public void deleteCreditCardTest() {
        log.info("Testing deleting credit card");
        //preparation
        card1 = creditCardService.addCreditCard(card1);
        int expectedCount = 0;

        //action
        creditCardService.deleteCreditCard(card1);
        int actualCount = creditCardService.countCreditCards();
        CreditCard newCard = creditCardService.findCreditCardById(card1.getId());

        //assertion
        assertEquals("Count should be equal to 0", expectedCount, actualCount);
        assertNull("newCard should be null", newCard);
    }

    @Test
    public void validUpdateCreditCard() {
        log.info("Testing valid updating credit card");
        //preparation
        card1 = creditCardService.addCreditCard(card1);
        card1.setOwner("New owner");

        //action
        creditCardService.updateCreditCard(card1);
        CreditCard newCard = creditCardService.findCreditCardById(card1.getId());

        //assertion
        assertNotNull("New credit card should not be null", newCard);
        assertEquals("New credit card should have updated owner field", card1.getOwner(), newCard.getOwner());
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidUpdateCreditCard() {
        log.info("Testing invalid updating credit card");
        //preparation
        card1 = creditCardService.addCreditCard(card1);
        creditCardService.deleteCreditCard(card1);

        //action
        creditCardService.updateCreditCard(card1);
    }

    @Test
    public void deleteAllCreditCardsTest() {
        log.info("Testing deleting all credit cards");
        //preparation
        addAllCreditCards();
        int expectedCount = 0;

        //action
        creditCardService.deleteAllCreditCards();
        int actualCount = creditCardService.countCreditCards();

        //assertion
        assertEquals("Count should be equal to 0", expectedCount, actualCount);
    }

    @After
    public void clean() {
        creditCardService.deleteAllCreditCards();
    }
}
