package todolist.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long user_id;

    @Column(unique = true)
    @NotNull
    @Size(min = 4, max = 30)
    private String username;

    @NotNull
    @Size(min = 6, max = 20)
    private String password;


    public Long getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
