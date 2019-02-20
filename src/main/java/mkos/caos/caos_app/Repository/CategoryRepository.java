package mkos.caos.caos_app.Repository;

import mkos.caos.caos_app.Model.Category;
import mkos.caos.caos_app.Model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    @Override
    Iterable<Category> findAll();

    Category findFirstByPersonAndStartDateIsBeforeAndEndDateIsAfter(Person person, Date date, Date date2);

    List<Category>findAllByPerson_IdOrderByIdDesc(Long userID);

    Category getById(Long id);
}
