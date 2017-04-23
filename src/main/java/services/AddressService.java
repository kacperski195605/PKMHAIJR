package services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pkmhaijr.model.dbEntities.Address;
import repositories.AddressRepository;

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
public class AddressService {

    private final AddressRepository addressRepository;

    public Address addAddress(Address address) {
       log.info("Adding address: " + address);
       return addressRepository.save(address);
    }

    public Address findAddressById(Integer id) {
        log.info("Searching for address with id: " + id);
        return addressRepository.findOne(id);
    }

    public List<Address> findAllAddresses() {
        log.info("Getting all addresses");
        return StreamSupport.stream(addressRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public void deleteAddress(Address address) {
        log.info("Deleting address: " + address);
        addressRepository.delete(address);
    }

    public Address updateAddress(Address newAddress) {
        log.info("Updating address: " + newAddress);
        Address oldAddress = findAddressById(newAddress.getId());
        if (oldAddress != null) {
            return addressRepository.save(newAddress);
        } else {
            throw new IllegalArgumentException("There is no address with id " + newAddress.getId() + " in database");
        }
    }
}
