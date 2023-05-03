package Universe_Structure;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import Universe_DB.AppDataBaseSpace;

@Entity(tableName = AppDataBaseSpace.PLANET_TABLE)
public class Planet extends SpaceObject{

    //Variables
    @PrimaryKey(autoGenerate = true)
    private int planetId;
    private String user;
    private String Population;
    private String Climate;
    /*private int solarSystemId;
    private int galaxyId;
    private boolean isGoldyLoxZone;
    private double distanceFromStar;
    private double distanceFromGalaxyCenter;
    private SolarSystem currentSystem;*/


    //Constructors
    public Planet(){
        new Planet("", "", "", "");
    }

    public Planet(String name, String discoverer, String population, String climate){
        super(name, discoverer);
        this.Population = population;
        this.Climate = climate;
    }

    //Methods


    public int getPlanetId() {
        return planetId;
    }

    public void setPlanetId(int planetId) {
        this.planetId = planetId;
    }

    /*public int getSolarSystemId() {
        return solarSystemId;
    }

    public void setSolarSystemId(int solarSystemId) {
        this.solarSystemId = solarSystemId;
    }

    public int getGalaxyId() {
        return galaxyId;
    }

    public void setGalaxyId(int galaxyId) {
        this.galaxyId = galaxyId;
    }

    public boolean isGoldyLoxZone() {
        String typeOfStar = currentSystem.typeOfStar;
        if (typeOfStar.equals("brown dwarf")){
            if(distanceFromStar < 0.3 && distanceFromStar > 0.01){
                return true;
            }
        }
        if (typeOfStar.equals("red dwarf")){
            if(distanceFromStar > 0.3 && distanceFromStar < 10){
                return true;
            }
        }

        return isGoldyLoxZone;
    }

    public void setGoldyLoxZone(boolean goldyLoxZone) {
        isGoldyLoxZone = goldyLoxZone;
    }

    public double getDistanceFromStar() {
        return distanceFromStar;
    }

    public void setDistanceFromStar(int distanceFromStar) {
        this.distanceFromStar = distanceFromStar;
    }

    public double getDistanceFromGalaxyCenter() {
        return distanceFromGalaxyCenter;
    }

    public void setDistanceFromGalaxyCenter(int distanceFromGalaxyCenter) {
        this.distanceFromGalaxyCenter = distanceFromGalaxyCenter;
    }*/

    public String getPopulation() {
        return Population;
    }

    public void setPopulation(String population) {
        Population = population;
    }

    public String getClimate() {
        return Climate;
    }

    public void setClimate(String climate) {
        Climate = climate;
    }

    public String getUser(){
        return user;
    }

    public void setUser(String name){
        user = name;
    }
}
