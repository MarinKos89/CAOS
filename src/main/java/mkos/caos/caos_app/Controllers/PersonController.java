package mkos.caos.caos_app.Controllers;

import mkos.caos.caos_app.Model.DTO.PersonCategory;
import mkos.caos.caos_app.Model.Person;
import mkos.caos.caos_app.Service.PersonService;
import mkos.caos.caos_app.Service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;


@RestController
@RequestMapping("/api/person")
@CrossOrigin
public class PersonController {

    private final PersonService personService;

    private final ValidationService validationService;

    @Autowired
    public PersonController(PersonService personService, ValidationService validationService) {
        this.personService = personService;

        this.validationService = validationService;
    }


    @PostMapping("")
    public ResponseEntity<?>createNewPerson(@Valid @RequestBody Person person, BindingResult result){

        ResponseEntity<?>errorMap=validationService.Validation(result);
        if (errorMap!=null) {
            return errorMap;
        }
        Person newUser= personService.saveOrUpdatePerson(person);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>getPersonById(@PathVariable Long id){

        Person person= personService.findPersonByID(id);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }
/*
    @GetMapping("/all")
    public Iterable<User>getAllUsers(){
        return userService.findAllUsers();
    }*/

    @GetMapping("/all")
    public List<PersonCategory> getAllPersonWithCategory(){
        return personService.findAllPersonAndCategoryForPerson();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>deletePerson(@PathVariable Long id){
        personService.deletePerson(id);
        return new ResponseEntity<>("Person with id " + id + " is deleted", HttpStatus.OK);
    }
}
