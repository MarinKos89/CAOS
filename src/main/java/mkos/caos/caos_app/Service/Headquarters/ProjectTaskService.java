package mkos.caos.caos_app.Service.Headquarters;

import mkos.caos.caos_app.Exceptions.ProjectNotFoundException;
import mkos.caos.caos_app.Model.Headquarters.Project;
import mkos.caos.caos_app.Model.Headquarters.ProjectBoard;
import mkos.caos.caos_app.Model.Headquarters.ProjectTasks;
import mkos.caos.caos_app.Repository.Headquarters.ProjectBoardRepository;
import mkos.caos.caos_app.Repository.Headquarters.ProjectRepository;
import mkos.caos.caos_app.Repository.Headquarters.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {

    private final ProjectTaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final ProjectBoardRepository boardRepository;

    @Autowired
    public ProjectTaskService(ProjectTaskRepository taskRepository, ProjectRepository projectRepository, ProjectBoardRepository boardRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.boardRepository = boardRepository;
    }


    //addNewTask

    public ProjectTasks addNewTask(String officialNumber, ProjectTasks projectTasks){

        try {
            ProjectBoard board=boardRepository.findByOfficialNumber(officialNumber);
            projectTasks.setBoard(board);

            Integer order=board.getProjectOrder();
            order++;
            board.setProjectOrder(order);

            projectTasks.setProjectSequence(board.getOfficialNumber()+"-"+order);
            projectTasks.setOfficialNumber(officialNumber);

            if (projectTasks.getProjectStatus().equals("") || projectTasks.getProjectStatus()==null){
                projectTasks.setProjectStatus("Unclassified");
            }
            if (projectTasks.getProjectPriority()==0 || projectTasks.getProjectPriority()==null){
                projectTasks.setProjectPriority(3);
            }
            return  taskRepository.save(projectTasks);

        }catch (Exception e){
            throw new ProjectNotFoundException("Project not found");

        }

    }

    //findTask
    public Iterable<ProjectTasks>findProjectBoardByID(String officialNumber){
        Project project=projectRepository.findByOfficialNumber(officialNumber);
        if (project==null){
            throw new ProjectNotFoundException("project "+officialNumber+" not found");

        }
        return taskRepository.findByOfficialNumber(officialNumber);
    }

    public ProjectTasks findProjectTaskByProjectSequence(String boardId, String projectId){
        ProjectBoard board=boardRepository.findByOfficialNumber(boardId);
        if (board ==null){
            throw  new ProjectNotFoundException("project with id "+boardId+"does not exist");
        }
        ProjectTasks tasks=taskRepository.findByProjectSequence(projectId);
        if (tasks==null){
            throw  new ProjectNotFoundException("Project with "+projectId+" not found");
        }
        if (!tasks.getOfficialNumber().equals(boardId)){
            throw  new ProjectNotFoundException("project task "+projectId+"does not exist in "+boardId);
        }
        return tasks;
    }

    //updateTask

    public ProjectTasks updateProjectTaskByProjectSequence(ProjectTasks updatedProjectTask,String boardId,String projectId){
        findProjectTaskByProjectSequence(boardId, projectId);
        ProjectTasks tasks;
        tasks=updatedProjectTask;
        return taskRepository.save(tasks);

    }


    //deleteTask

    public void deleteProjectTaskByProjectSequence(String boardId, String projectId){
        ProjectTasks tasks=findProjectTaskByProjectSequence(boardId,projectId);
        taskRepository.delete(tasks);
    }


}
