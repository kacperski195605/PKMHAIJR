package pkmhaijr.service;

import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
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

    @Autowired
    private CreditCardService creditCardService;

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
        Assertions.assertThat(newCard).isNotNull();
        Assertions.assertThat(newCard).isEqualTo(card1);
    }

    @Test
    public void countOfEmptyDatabaseTest() {
        log.info("Testing count of empty database");
        //preparation
        int expectedCount = 0;

        //action
        int actualCount = creditCardService.countCreditCards();

        //assertion
        Assertions.assertThat(actualCount).isEqualTo(expectedCount);
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
        Assertions.assertThat(expectedCount).isEqualTo(actualCount);
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
        Assertions.assertThat(cards).isNotNull();
        Assertions.assertThat(expectedCount).isEqualTo(cards.size());
        Assertions.assertThat(cards).contains(card1, card2, card3);
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
        Assertions.assertThat(expectedCount).isEqualTo(actualCount);
        Assertions.assertThat(newCard).isNull();
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
        Assertions.assertThat(newCard).isNotNull();
        Assertions.assertThat(card1.getOwner()).isEqualTo(newCard.getOwner());
    }

    @Test
    public void invalidUpdateCreditCard() {
        log.info("Testing invalid updating credit card");
        //preparation
        card1 = creditCardService.addCreditCard(card1);
        creditCardService.deleteCreditCard(card1);

        //action
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> creditCardService.updateCreditCard(card1));
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
        Assertions.assertThat(expectedCount).isEqualTo(actualCount);
    }

    @After
    public void clean() {
        creditCardService.deleteAllCreditCards();
    }
}
