package mkos.caos.caos_app.Controllers;

import mkos.caos.caos_app.Model.User;
import mkos.caos.caos_app.Security.JwtTokenProvider;
import mkos.caos.caos_app.Security.Logins.JWTLoginResponse;
import mkos.caos.caos_app.Security.Logins.LoginRequest;
import mkos.caos.caos_app.Service.UserService;
import mkos.caos.caos_app.Service.ValidationService;
import mkos.caos.caos_app.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static mkos.caos.caos_app.Security.Constants.TOKEN_PREFIX;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;


    private final ValidationService validationService;

    private final UserValidator userValidator;

    @Autowired
    public AuthController(UserService userService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, ValidationService validationService, UserValidator userValidator) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.validationService = validationService;
        this.userValidator = userValidator;
    }


    @PostMapping("/login")
    public ResponseEntity<?>userAuthenticate(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
        ResponseEntity<?>errorMap=validationService.Validation(result);
        if (errorMap!=null) {
            return errorMap;
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt=TOKEN_PREFIX+jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTLoginResponse(true,jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<?>userRegistration(@Valid @RequestBody User user, BindingResult result){

        userValidator.validate(user,result);
        User newUser=userService.saveUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }


}
