package todolist;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String description;
    private String date;
    private boolean status;


    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public boolean isStatus() {
        return status;
    }

}
