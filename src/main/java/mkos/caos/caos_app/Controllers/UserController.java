package mkos.caos.caos_app.Controllers;

import mkos.caos.caos_app.Model.DTO.UserDto;
import mkos.caos.caos_app.Model.User;
import mkos.caos.caos_app.Service.UserService;
import mkos.caos.caos_app.Service.ValidationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

    private final UserService userService;

    private final ValidationService validationService;

    public UserController(UserService userService, ValidationService validationService) {
        this.userService = userService;
        this.validationService = validationService;
    }

    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return users.stream().map(user -> new UserDto(user.getId(), user.getUsername())).collect(Collectors.toList());
    }

    @PostMapping("")
    public ResponseEntity<?>createNewUser(@Valid @RequestBody User user, BindingResult result){

        ResponseEntity<?>errorMap=validationService.Validation(result);
        if (errorMap!=null){
            return errorMap;
        }
        User user1=userService.createNewUser(user);
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return  new ResponseEntity<>("User with id "+ id + "is deleted", HttpStatus.OK);
    }

    //Napraviti metodu u servisu i na kontroleru endpoint za izmjenu ROLA


}
