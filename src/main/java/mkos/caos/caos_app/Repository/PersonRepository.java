package mkos.caos.caos_app.Repository;

import mkos.caos.caos_app.Model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person,Long> {

    Person getById(Long Id);

    @Override
    Iterable<Person> findAll();

}
