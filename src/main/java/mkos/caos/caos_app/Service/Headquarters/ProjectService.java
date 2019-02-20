package mkos.caos.caos_app.Service.Headquarters;

import mkos.caos.caos_app.Exceptions.ProjectIdentifierException;
import mkos.caos.caos_app.Model.Headquarters.Project;
import mkos.caos.caos_app.Model.Headquarters.ProjectBoard;
import mkos.caos.caos_app.Repository.Headquarters.ProjectBoardRepository;
import mkos.caos.caos_app.Repository.Headquarters.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectBoardRepository boardRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, ProjectBoardRepository boardRepository) {
        this.projectRepository = projectRepository;
        this.boardRepository = boardRepository;
    }


    //saveOrUpdate
    public Project saveOrUpdateProject(Project project){
        try {
            project.setOfficialNumber(project.getOfficialNumber());

            if (project.getId()==null){
                ProjectBoard board=new ProjectBoard();
                project.setBoard(board);
                board.setProject(project);
                board.setOfficialNumber(project.getOfficialNumber());
            }
            if (project.getId()!=null){
                project.setBoard(boardRepository.findByOfficialNumber(project.getOfficialNumber()));
            }
            return projectRepository.save(project);
        }catch (Exception e){
           throw new ProjectIdentifierException("Official number "+project.getOfficialNumber()+" already exists");
        }


    }

    //findAll
    public Iterable<Project>findAllProjects(){
        return projectRepository.findAll();
    }

    //findByIdentifier
    public Project findByIdentifier(String officialNumber){
        Project project=projectRepository.findByOfficialNumber(officialNumber);
        if (project==null){
            throw  new ProjectIdentifierException("Official number "+officialNumber+" does not exist");
        }
       return project;
    }


    //deleteProjectByIdentifier
    public void deleteProjectByIdentifier(String officialNumber){
        Project project=projectRepository.findByOfficialNumber(officialNumber);
        if (project==null){
            throw  new ProjectIdentifierException("Can't delete "+officialNumber+" This project does not exist");        }

        projectRepository.delete(project);
    }

}
