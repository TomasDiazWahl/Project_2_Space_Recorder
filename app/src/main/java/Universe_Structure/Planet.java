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
    private boolean isGoldyLoxZone = false;
    private double distanceFromStar;

    private double Size;


    //Constructors
    public Planet(){
        new Planet("", "", "", "");
    }

    public Planet(String name, String discoverer){
        this(name, discoverer, "You", "Cool");
    }

    public Planet(String name, String discoverer, String population, String climate){
        super(name, discoverer);
        this.Population = population;
        this.Climate = climate;
        this.Size = 0;
        this.distanceFromStar = 0;
        this.solarSystemId = 0;
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

    public boolean getIsGoldyLoxZone(){
        return isGoldyLoxZone;
    }

    public void setGoldyLoxZone(boolean goldyLoxZone) {
        isGoldyLoxZone = goldyLoxZone;
    }

    public double getDistanceFromStar() {
        return distanceFromStar;
    }

    public void setDistanceFromStar(double distanceFromStar) {
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
    public double getSize() {
        return Size;
    }

    public void setSize(double size) {
        Size = size;
    }
}
