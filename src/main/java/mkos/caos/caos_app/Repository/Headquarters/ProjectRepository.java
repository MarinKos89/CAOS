package mkos.caos.caos_app.Repository.Headquarters;

import mkos.caos.caos_app.Model.Headquarters.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project,Long> {

    @Override
    Iterable<Project> findAll();

    Project findByOfficialNumber(String officialNumber);
}
