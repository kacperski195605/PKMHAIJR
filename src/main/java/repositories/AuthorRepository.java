package repositories;

import org.springframework.data.repository.CrudRepository;
import pkmhaijr.model.dbEntities.Author;

/**
 * Created by patry on 23/04/17.
 */
public interface AuthorRepository extends CrudRepository<Author, Integer> {
}
