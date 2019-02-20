package mkos.caos.caos_app.Repository;


import mkos.caos.caos_app.Model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
    User getById(Long Id);

    @Override
    List<User> findAll();
}
