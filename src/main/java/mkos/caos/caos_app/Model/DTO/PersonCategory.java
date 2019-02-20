package mkos.caos.caos_app.Model.DTO;


import mkos.caos.caos_app.Model.Category;
import mkos.caos.caos_app.Model.Person;




public class PersonCategory {

    private Person person;


    private  Category category;

    public PersonCategory(Person person, Category category) {
        this.person = person;

        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }


}
