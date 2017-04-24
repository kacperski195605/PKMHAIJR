package pkmhaijr.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pkmhaijr.model.dbEntities.Author;

/**
 * Created by patry on 23/04/17.
 */
@Repository
public interface AuthorRepository extends CrudRepository<Author, Integer> {
}
