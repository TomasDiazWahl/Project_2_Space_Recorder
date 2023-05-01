package UserDB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = AppDataBaseUser.DATABASE_TABLE)
public class User {

    //Comment
    @PrimaryKey(autoGenerate = true)
    private int UserId;
    String Name;
    private String Password;
    int Age;
    int isAdmin;
    ArrayList<String> planets;
    ArrayList<String> solarSystems;
    ArrayList<String> galaxies;




    //Constructor
    public User(){
       this("Bob", "", 50, 0);
    }

    public User(String name, String password, int age, int admin){
        this.Name = name;
        this.Password = password;
        this.Age = age;
        this.isAdmin = admin;
        this.planets = new ArrayList<>();
        this.solarSystems = new ArrayList<>();
        this.galaxies = new ArrayList<>();
    }

    //Getters and Setters

    public ArrayList<String> getPlanets() {
        return planets;
    }

    public void setPlanets(ArrayList<String> planets) {
        this.planets = planets;
    }

    public ArrayList<String> getSolarSystems() {
        return solarSystems;
    }

    public void setSolarSystems(ArrayList<String> solarSystems) {
        this.solarSystems = solarSystems;
    }

    public ArrayList<String> getGalaxies() {
        return galaxies;
    }

    public void setGalaxies(ArrayList<String> galaxies) {
        this.galaxies = galaxies;
    }

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

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "User{" +
                "UserId=" + UserId +
                ", Name='" + Name + '\'' +
                ", Password='" + Password + '\'' +
                ", Age=" + Age +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
