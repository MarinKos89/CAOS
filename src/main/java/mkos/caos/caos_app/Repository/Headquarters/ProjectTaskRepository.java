package mkos.caos.caos_app.Repository.Headquarters;

import mkos.caos.caos_app.Model.Headquarters.ProjectTasks;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTasks,Long> {

    ProjectTasks findByProjectSequence(String projectId);

    List<ProjectTasks>findByOfficialNumber(String officialNumber);
}
