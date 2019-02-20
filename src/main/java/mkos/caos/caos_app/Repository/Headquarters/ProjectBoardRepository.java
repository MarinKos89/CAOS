package mkos.caos.caos_app.Repository.Headquarters;

import mkos.caos.caos_app.Model.Headquarters.ProjectBoard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectBoardRepository extends CrudRepository<ProjectBoard,Long> {

    ProjectBoard findByOfficialNumber(String officialNumber);
}
