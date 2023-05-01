package Universe_Structure;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import Universe_DB.AppDataBaseSpace;

@Entity(tableName = AppDataBaseSpace.SOLAR_SYSTEM_TABLE)
public class SolarSystem extends SpaceObject{

    //Variables
    @PrimaryKey(autoGenerate = true)
    private int solarSystemId;

    private int galaxy;
    /*private int numberOfPlanets;
    String typeOfStar;
    private ArrayList<Planet> Planets;*/


    //Constructors
    public SolarSystem(){
        new SolarSystem("", "Unknown",  0);
    }

    public SolarSystem(String name, String discoverer, int galaxy){
        super(name, discoverer);
        //this.Planets = new ArrayList<>();
        this.galaxy = galaxy;
    }


    //Methods

    /*void addPlanet(Planet planet){
        Planets.add(planet);
    }*/




    //Getters and Setters

    public int getGalaxy() {
        return galaxy;
    }

    public void setGalaxy(int galaxy) {
        this.galaxy = galaxy;
    }

    public int getSolarSystemId() {
        return solarSystemId;
    }

    public void setSolarSystemId(int solarSystemId) {
        this.solarSystemId = solarSystemId;
    }

    /*public ArrayList<Planet> getPlanets() {
        return Planets;
    }

    public void setPlanets(ArrayList<Planet> planets) {
        Planets = planets;
    }*/
}
