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
    String toDisplay;




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

    //Methods

    public void addPlanet(String id){
        if (!planets.contains(id)){
            planets.add(id);
        }
    }

    public void addSolarSystem(String id){
        if (!solarSystems.contains(id)){
            solarSystems.add(id);
        }
    }

    public void addGalaxy(String id){
        if (!galaxies.contains(id)){
            galaxies.add(id);
        }
    }

    public boolean hasPlanet(String id){
        return planets.contains(id);
    }

    public boolean hasSystem(String id){
        return solarSystems.contains(id);
    }

    public boolean hasGalaxy(String id){
        return galaxies.contains(id);
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

    public String getToDisplay() {
        return toDisplay;
    }

    public void setToDisplay(String toDisplay) {
        this.toDisplay = toDisplay;
    }

    @Override
    public String toString() {
        return this.getName() + " User ID: " + this.getUserId();
    }
}
