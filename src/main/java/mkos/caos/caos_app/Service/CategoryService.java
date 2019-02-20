package mkos.caos.caos_app.Service;

import mkos.caos.caos_app.Model.Category;
import mkos.caos.caos_app.Model.Person;
import mkos.caos.caos_app.Model.User;
import mkos.caos.caos_app.Repository.CategoryRepository;
import mkos.caos.caos_app.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final PersonRepository personRepository;

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(PersonRepository personRepository, CategoryRepository categoryRepository) {
        this.personRepository = personRepository;
        this.categoryRepository = categoryRepository;
    }

    public Category addCategory(Long id,Category category) {

        Person person = personRepository.getById(id);

        Category category2=new Category();
        if (person.getCategory()==null || person.getCategory().isEmpty()){
            category2=saveCategory(category, person);
        }else{

            Category category1=categoryRepository.findFirstByPersonAndStartDateIsBeforeAndEndDateIsAfter(person, category.getStartDate(),  category.getStartDate());
            if(category1!=null){
                category1.setEndDate(category.getStartDate());


                categoryRepository.save(category1);
            }

            category2.setNameOfCategory(category.getNameOfCategory());

            category2=saveCategory(category,person);
        }
        return categoryRepository.save(category2);
    }

    private Category saveCategory(Category category, Person person){
        Category category1=new Category();
        category1.setPerson(person);
        category1.setStartDate(category.getStartDate());
        category1.setEndDate(category.getEndDate());
        category1.setNameOfCategory(category.getNameOfCategory());
        return category1;
    }


    public Iterable<Category>getAllCategory(){
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id){
        return categoryRepository.getById(id);
    }

}
