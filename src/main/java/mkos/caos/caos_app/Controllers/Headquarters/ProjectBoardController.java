package mkos.caos.caos_app.Controllers.Headquarters;

import mkos.caos.caos_app.Model.Headquarters.ProjectTasks;
import mkos.caos.caos_app.Service.Headquarters.ProjectTaskService;
import mkos.caos.caos_app.Service.ValidationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/board")
@CrossOrigin
public class ProjectBoardController {

    private final ValidationService validationService;

    private final ProjectTaskService taskService;

    public ProjectBoardController(ValidationService validationService, ProjectTaskService taskService) {
        this.validationService = validationService;
        this.taskService = taskService;
    }
    //addTaskTOBoard
    @PostMapping("/{officialNumber}")
    public ResponseEntity<?>addTaskToBoard(@Valid @RequestBody ProjectTasks projectTasks,BindingResult bindingResult, @PathVariable String officialNumber ){
        ResponseEntity<?>error=validationService.Validation(bindingResult);
        if (error!=null)return error;
        ProjectTasks newTask=taskService.addNewTask(officialNumber,projectTasks);
        return  new ResponseEntity<>(newTask, HttpStatus.CREATED);
    }



    //getProjectBoard
    @GetMapping("/{officialNumber}")
    public Iterable<ProjectTasks>getProjectBoard(@PathVariable String officialNumber){
        return taskService.findProjectBoardByID(officialNumber);
    }

    //getProjectTask
    @GetMapping("/{officialNumber}/{projectId}")
    public ResponseEntity<?>getProjectTask(@PathVariable String officialNumber, @PathVariable String projectId){
        ProjectTasks tasks=taskService.findProjectTaskByProjectSequence(officialNumber,projectId);
        return new ResponseEntity<>(tasks,HttpStatus.OK);
    }

    //updateProjectTask
    @PatchMapping("/{officialNumber}/{projectId}")
    public ResponseEntity<?>updateProjectTask(@Valid @PathVariable String officialNumber, @PathVariable String projectId, BindingResult bindingResult, @RequestBody ProjectTasks  projectTasks){
        ResponseEntity<?>error=validationService.Validation(bindingResult);
        if (error!=null)return error;
        ProjectTasks newTask=taskService.updateProjectTaskByProjectSequence(projectTasks,officialNumber,projectId);
        return new ResponseEntity<>(newTask,HttpStatus.OK);
    }


    //deleteProjectTask
    @DeleteMapping("/{officialNumber}/{projectId}")
    public ResponseEntity<?>deleteProjectTask(@PathVariable String officialNumber, @PathVariable String projectId){
        taskService.deleteProjectTaskByProjectSequence(officialNumber,projectId);
        return new ResponseEntity<>("Project with " + projectId + " id is deleted", HttpStatus.OK);
    }



}
