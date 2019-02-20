package mkos.caos.caos_app.Controllers;

import mkos.caos.caos_app.Model.Category;
import mkos.caos.caos_app.Model.User;
import mkos.caos.caos_app.Repository.CategoryRepository;
import mkos.caos.caos_app.Service.CategoryService;
import mkos.caos.caos_app.Service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/category")
@CrossOrigin
public class CategoryController {

    private final CategoryService categoryService;

    private final ValidationService validationService;

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryService categoryService, ValidationService validationService, CategoryRepository categoryRepository) {
        this.categoryService = categoryService;
        this.validationService = validationService;
        this.categoryRepository = categoryRepository;
    }


    @PostMapping("/{id}")
    public ResponseEntity<?> addCategoryToUser(@Valid @RequestBody Category category,
                                            BindingResult result, @PathVariable Long id)  {


        ResponseEntity<?> errorMap = validationService.Validation(result);
        if (errorMap != null) return errorMap;


         Category newCategory = categoryService.addCategory(id,category);

        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);

    }

    @GetMapping("/all")
    public Iterable<Category>getAllCategories(){
        return categoryService.getAllCategory();
    }


    @GetMapping("/history/{id}")
    public Iterable<Category>getCategoryForUser(@PathVariable Long id){

        return categoryRepository.findAllByPerson_IdOrderByIdDesc(id);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?>getCategoryById(@PathVariable Long id){
        Category category=categoryService.getCategoryById(id);
        return new ResponseEntity<>(category,HttpStatus.OK);
    }


}
