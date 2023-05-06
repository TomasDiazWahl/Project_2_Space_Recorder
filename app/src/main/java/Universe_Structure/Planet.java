package Universe_Structure;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import Universe_DB.AppDataBaseSpace;

@Entity(tableName = AppDataBaseSpace.PLANET_TABLE)
public class Planet extends SpaceObject{

    //Variables
    @PrimaryKey(autoGenerate = true)
    private int planetId;
    private String Population;
    private String Climate;
    private int solarSystemId;
    private int galaxyId;
    private boolean isGoldyLoxZone = false;
    private double distanceFromStar;
    //SolarSystemDAO solarSystemDAO;


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

    public int getSolarSystemId() {
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
    public boolean getIsGoldyLoxZone(){
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
}
