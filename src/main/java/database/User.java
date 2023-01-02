package database;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "users")
public class User {
    @Id
    private String username;
    private String password;

    //@ElementCollection
    //private ArrayList<String> history;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        //history = new ArrayList<String>();
    }

    public User() {
        System.out.println("Wrong constructor data");
    }


    @Override
    public String toString() {
        return "Users{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }


}
