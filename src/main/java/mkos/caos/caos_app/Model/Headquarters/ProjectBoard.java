package mkos.caos.caos_app.Model.Headquarters;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ProjectBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer projectOrder=0;



    private String officialNumber;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id",nullable = false)
    @JsonIgnore
    private Project project;

    @OneToMany(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER,mappedBy = "board", orphanRemoval = true)
    private List<ProjectTasks> projectTasks=new ArrayList<>();

    public ProjectBoard() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProjectOrder() {
        return projectOrder;
    }

    public void setProjectOrder(Integer projectOrder) {
        this.projectOrder = projectOrder;
    }

    public String getOfficialNumber() {
        return officialNumber;
    }

    public void setOfficialNumber(String officialNumber) {
        this.officialNumber = officialNumber;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<ProjectTasks> getProjectTasks() {
        return projectTasks;
    }

    public void setProjectTasks(List<ProjectTasks> projectTasks) {
        this.projectTasks = projectTasks;
    }
}
