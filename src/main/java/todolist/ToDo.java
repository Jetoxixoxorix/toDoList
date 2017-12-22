package todolist;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class ToDo {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String description;
    private Date date;
    private boolean status;

    public ToDo(int id, String description, Date date, boolean status){
        this.id = id;
        this.description = description;
        this.date = date;
        this.status = status;
    }


    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public boolean isStatus() {
        return status;
    }

}
