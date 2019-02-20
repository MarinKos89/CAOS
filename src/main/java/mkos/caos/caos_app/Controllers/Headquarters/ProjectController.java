package mkos.caos.caos_app.Controllers.Headquarters;

import mkos.caos.caos_app.Model.Headquarters.Project;
import mkos.caos.caos_app.Service.Headquarters.ProjectService;
import mkos.caos.caos_app.Service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
@CrossOrigin
public class ProjectController {

    private final ProjectService projectService;
    private final ValidationService validationService;
    @Autowired
    public ProjectController(ProjectService projectService, ValidationService validationService) {
        this.projectService = projectService;
        this.validationService = validationService;
    }

    @PostMapping("")
    public ResponseEntity<?>createNewProject(@Valid @RequestBody Project project, BindingResult bindingResult){
        ResponseEntity<?>error=validationService.Validation(bindingResult);
        if (error!=null)return error;
        Project newProject=projectService.saveOrUpdateProject(project);
        return new ResponseEntity<>(newProject, HttpStatus.CREATED);
    }

    //getById
    @GetMapping("/{officialNumber}")
    public ResponseEntity<Project>getProjectByOfficialNumber(@PathVariable String officialNumber){
        Project project=projectService.findByIdentifier(officialNumber);
        return new ResponseEntity<>(project,HttpStatus.OK);
    }

    //getAllProjects
    @GetMapping("/all")
    public Iterable<Project>getAllProjects(){return projectService.findAllProjects();}

    //deleteProject

    @DeleteMapping("/{officialNumber}")
    public ResponseEntity<?>deleteProject(@PathVariable String officialNumber){
        projectService.deleteProjectByIdentifier(officialNumber);
        return new ResponseEntity<>("project with "+officialNumber+" is deleted",HttpStatus.OK);
    }
}
