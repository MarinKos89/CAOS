package mkos.caos.caos_app.Model.Headquarters;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
public class ProjectTasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false)
    private String officialNumber;

    @Column(updatable = false)
    private String classNumber;

    @Column(updatable = false,unique = true)
    private String projectOrder;

    @NotBlank(message = "Please choose project status")
    private String projectStatus;

    private Integer projectPriority;

    @Column(updatable = false, unique = true)
    private String projectSequence;

    @NotBlank(message = "Please enter summary about project task")
    private String projectSummary;

    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="board_id",updatable = false,nullable = false)
    @JsonIgnore
    private ProjectBoard board;

    public ProjectTasks() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOfficialNumber() {
        return officialNumber;
    }

    public void setOfficialNumber(String officialNumber) {
        this.officialNumber = officialNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public String getProjectSummary() {
        return projectSummary;
    }

    public void setProjectSummary(String projectSummary) {
        this.projectSummary = projectSummary;
    }



    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public String getProjectOrder() {
        return projectOrder;
    }

    public void setProjectOrder(String projectOrder) {
        this.projectOrder = projectOrder;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    public Integer getProjectPriority() {
        return projectPriority;
    }

    public void setProjectPriority(Integer projectPriority) {
        this.projectPriority = projectPriority;
    }

    public String getProjectSequence() {
        return projectSequence;
    }

    public void setProjectSequence(String projectSequence) {
        this.projectSequence = projectSequence;
    }

    public ProjectBoard getBoard() {
        return board;
    }

    public void setBoard(ProjectBoard board) {
        this.board = board;
    }
}
