package repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pkmhaijr.model.dbEntities.User;

/**
 * Created by patry on 23/04/17.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}
