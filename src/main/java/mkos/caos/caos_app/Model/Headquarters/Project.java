package mkos.caos.caos_app.Model.Headquarters;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Enter project name")
    private String projectName; //zapovijed za strazu


    @NotNull(message = "Enter project description")
    private String description; //zapovjed za strazu za bozic

    @NotNull(message = "Enter project officialNumber")
    @Size(min=7, max=7, message = "Valid official number is 1033- and two digit")
    @Column(updatable = false, unique = true)
    private String officialNumber; //urudžbeni broj

    @NotNull(message = "Enter project officialNumber")
    @Size(min=6, max=6, message = "Valid official number is 023- and  two digit")
    @Column(updatable = false, unique = true)
    private String classNumber; //klasa

    @NotNull(message = "Enter date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date; //kreirana 12.12.2018



    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "project")
    @JsonIgnore
    private ProjectBoard board;


    public Project() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getOfficialNumber() {
        return officialNumber;
    }

    public void setOfficialNumber(String officialNumber) {
        this.officialNumber = officialNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public ProjectBoard getBoard() {
        return board;
    }

    public void setBoard(ProjectBoard board) {
        this.board = board;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
