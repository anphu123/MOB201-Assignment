package hieuntph22081.fpoly.assignment.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "user")
public class User {
    @PrimaryKey (autoGenerate = true)
    public int id;
    private String userName;
    private String password;
    private String fullName;

    public User() {
    }

    public User(int id, String userName, String password, String fullName) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
