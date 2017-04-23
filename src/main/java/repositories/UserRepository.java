package repositories;

import org.springframework.data.repository.CrudRepository;
import pkmhaijr.model.dbEntities.User;

/**
 * Created by patry on 23/04/17.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
}
