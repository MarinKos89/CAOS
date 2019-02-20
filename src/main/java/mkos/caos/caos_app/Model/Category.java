package mkos.caos.caos_app.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "enter name of category")
    private String nameOfCategory;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private  Date startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @JsonIgnore
    private Person person;

    public Category() {
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameOfCategory() {
        return nameOfCategory;
    }

    public void setNameOfCategory(String nameOfCategory) {
        this.nameOfCategory = nameOfCategory;
    }


    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", nameOfCategory='" + nameOfCategory + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", person=" + person +
                '}';
    }
}
