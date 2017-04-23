package repositories;

import org.springframework.data.repository.CrudRepository;
import pkmhaijr.model.dbEntities.Address;

/**
 * Created by patry on 23/04/17.
 */
public interface AddressRepository extends CrudRepository<Address, Integer> {
}
