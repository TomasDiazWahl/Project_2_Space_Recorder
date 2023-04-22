package UserDB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = AppDataBaseUser.DATABASE_TABLE)
public class User {

    @PrimaryKey(autoGenerate = true)
    private int UserId;

    String Name;
    private String Password;
    int Age;




    //Constructor
    public User(){
       this("Bob", "", 50);
    }

    public User(String name, String password, int age){
        this.Name = name;
        this.Password = password;
        this.Age = age;
    }

    //Getters and Setters


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }
}
