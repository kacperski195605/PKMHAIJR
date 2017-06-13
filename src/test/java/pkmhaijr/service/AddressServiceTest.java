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
import pkmhaijr.model.dbEntities.Address;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by patry on 24/04/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Log4j2
public class AddressServiceTest {

    @Autowired
    private AddressService addressService;

    private Address address1;
    private Address address2;
    private Address address3;

    @Before
    public void setUp() {
        addressService.deleteAllAddresses();
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

    }

    private void addAllAddresses() {
        addressService.addAddress(address1);
        addressService.addAddress(address2);
        addressService.addAddress(address3);
    }

    @Test
    public void findAddressByIdTest() {
        log.info("Testing finding an address by id");
        //preparation
        address1 = addressService.addAddress(address1);

        //action
        Address newAddress = addressService.findAddressById(address1.getId());

        //assertion
        Assertions.assertThat(newAddress).isNotNull();
        Assertions.assertThat(newAddress).isEqualTo(address1);
    }

    @Test
    public void countOfEmptyDatabaseTest() {
        log.info("Testing count of empty database");
        //preparation
        int expectedCount = 0;

        //action
        int actualCount = addressService.countAddresses();

        //assertion
        Assertions.assertThat(expectedCount).isEqualTo(actualCount);
    }

    @Test
    public void countOfNotEmptyDatabaseTest() {
        log.info("Testing count of not empty database");
        //preparation
        addAllAddresses();
        int expectedCount = 3;

        //action
        int actualCount = addressService.countAddresses();

        //assertion
        Assertions.assertThat(expectedCount).isEqualTo(actualCount);
    }

    @Test
    public void findAllAddressesTest() {
        log.info("Testing finding all addresses");
        //preparation
        addAllAddresses();
        int expectedCount = 3;

        //action
        List<Address> addresses = addressService.findAllAddresses();

        //assertion
        Assertions.assertThat(addresses).isNotNull();
        Assertions.assertThat(expectedCount).isEqualTo(addresses.size());
        Assertions.assertThat(addresses).contains(address1, address2, address3);
    }

    @Test
    public void deleteAddressTest() {
        log.info("Testing deleting address");
        //preparation
        address1 = addressService.addAddress(address1);
        int expectedCount = 0;

        //action
        addressService.deleteAddress(address1);
        int actualCount = addressService.countAddresses();
        Address newAddress = addressService.findAddressById(address1.getId());

        //assertion
        Assertions.assertThat(expectedCount).isEqualTo(actualCount);
        Assertions.assertThat(newAddress).isNull();
    }

    @Test
    public void validUpdateAddress() {
        log.info("Testing valid updating address");
        //preparation
        address1 = addressService.addAddress(address1);
        address1.setCity("New city");

        //action
        addressService.updateAddress(address1);
        Address newAddress = addressService.findAddressById(address1.getId());

        //assertion
        Assertions.assertThat(newAddress).isNotNull();
        Assertions.assertThat(address1.getCity()).isEqualTo(newAddress.getCity());
    }

    @Test()
    public void invalidUpdateAddress() {
        log.info("Testing invalid updating address");
        //preparation
        address1 = addressService.addAddress(address1);
        addressService.deleteAddress(address1);

        //assertion
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> addressService.updateAddress(address1));
    }

    @Test
    public void deleteAllAddressesTest() {
        log.info("Testing deleting all addresses");
        //preparation
        addAllAddresses();
        int expectedCount = 0;

        //action
        addressService.deleteAllAddresses();
        int actualCount = addressService.countAddresses();

        //assertion
        Assertions.assertThat(expectedCount).isEqualTo(actualCount);
    }

    @After
    public void clean() {
        addressService.deleteAllAddresses();
    }
}
