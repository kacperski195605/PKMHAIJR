package pkmhaijr.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pkmhaijr.model.dbEntities.CreditCard;
import pkmhaijr.repository.CreditCardRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by patry on 23/04/17.
 */

@Service
@RequiredArgsConstructor(onConstructor = @__(@Inject))
@Log4j2
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;

    public CreditCard addCreditCard(CreditCard creditCard) {
        log.info("Adding credit card: " + creditCard);
        return creditCardRepository.save(creditCard);
    }

    public CreditCard findCreditCardById(Integer id) {
        log.info("Searching for credit card with id: " + id);
        return creditCardRepository.findOne(id);
    }

    public List<CreditCard> findAllCreditCards() {
        log.info("Getting all credit cards");
        return StreamSupport.stream(creditCardRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public void deleteCreditCard(CreditCard creditCard) {
        log.info("Deleting credit card: " + creditCard);
        creditCardRepository.delete(creditCard);
    }

    public CreditCard updateCreditCard(CreditCard newCreditCard) {
        log.info("Updating credit card " + newCreditCard);
        CreditCard creditCard = findCreditCardById(newCreditCard.getId());
        if (creditCard != null) {
            return creditCardRepository.save(newCreditCard);
        } else {
            throw new IllegalArgumentException("There is o credit card with i " + newCreditCard.getId() + " in the database");
        }
    }

    public void deleteAllCreditCards() {
        log.info("Deleting all credit cards");
        creditCardRepository.deleteAll();
    }

    public Integer countCreditCards() {
        log.info("Returning number of credit cards");
        return Math.toIntExact(creditCardRepository.count());
    }
}
