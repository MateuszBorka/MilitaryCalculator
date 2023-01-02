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

}
