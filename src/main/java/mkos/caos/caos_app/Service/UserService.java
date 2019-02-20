package mkos.caos.caos_app.Service;

import mkos.caos.caos_app.Exceptions.UsernameExistsException;
import mkos.caos.caos_app.Model.User;
import mkos.caos.caos_app.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    //SECURITY METODA
    public User saveUser (User newUser){

        try{
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));

            newUser.setUsername(newUser.getUsername());

            return userRepository.save(newUser);

        }catch (Exception e){
            throw new UsernameExistsException("Username '"+newUser.getUsername()+"' already exists");
        }

    }



    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public User createNewUser(User user){
        return  userRepository.save(user);
    }

    public void deleteUser(Long userID){
        User user=userRepository.getById(userID);
        userRepository.delete(user);
    }



}
