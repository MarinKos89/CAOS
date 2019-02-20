package mkos.caos.caos_app.Service;

import mkos.caos.caos_app.Model.Category;
import mkos.caos.caos_app.Model.DTO.PersonCategory;
import mkos.caos.caos_app.Model.Person;
import mkos.caos.caos_app.Repository.CategoryRepository;
import mkos.caos.caos_app.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PersonService {

    private static final Category RRV = new Category();

    static {
        RRV.setNameOfCategory("Redovno radno vrijeme");
    }

    private final PersonRepository personRepository;

    private final CategoryRepository categoryRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, CategoryRepository categoryRepository) {
        this.personRepository = personRepository;
        this.categoryRepository = categoryRepository;
    }


    public Person saveOrUpdatePerson(Person person){


        return personRepository.save(person);
    }

    private Iterable<Person>findAllPerson(){
        return personRepository.findAll();
    }


    public Person findPersonByID(Long id){
        return personRepository.getById(id);
    }

    public void deletePerson(Long id){
        Person person= personRepository.getById(id);

        personRepository.delete(person);
    }

    public List<PersonCategory> findAllPersonAndCategoryForPerson(){

        Iterable<Person> getAll = findAllPerson();
        List<PersonCategory> personCategory =new ArrayList<>();
        for (Person person:getAll) {
            Date now = new Date(System.currentTimeMillis());
            Category category = categoryRepository.findFirstByPersonAndStartDateIsBeforeAndEndDateIsAfter(person,now,now);
            personCategory.add(new PersonCategory(person, category != null ? category : RRV));
        }
        return personCategory;
    }

}
