package Universe_Structure;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import Universe_DB.AppDataBaseSpace;

@Entity(tableName = AppDataBaseSpace.SOLAR_SYSTEM_TABLE)
public class SolarSystem extends SpaceObject{

    //Variables
    @PrimaryKey(autoGenerate = true)
    private int solarSystemId;
    private int galaxyId;
    private boolean isGoldyLoxZone = false;
    private String typeOfStar;
    private double distanceFromGalaxyCenter;


    //Constructors
    public SolarSystem(){
        new SolarSystem("", "Unknown");
    }

    public SolarSystem(String name, String discoverer){
        super(name, discoverer);
        //this.Planets = new ArrayList<>();
    }

    public boolean determineGoldyLoxZone(Planet planet) {
        if (this.getDistanceFromGalaxyCenter() < 33000 && this.getDistanceFromGalaxyCenter() > 13000){
            if (this.typeOfStar.equals("dwarf star")){
                if(planet.getDistanceFromStar() < 3 && planet.getDistanceFromStar() > 0.01){
                    return true;
                }
            }
            if (this.typeOfStar.equals("giant star")){
                if(planet.getDistanceFromStar() > 10 && planet.getDistanceFromStar() < 100){
                    return true;
                }
            }
            if (this.typeOfStar.equals("super giant star")){
                if(planet.getDistanceFromStar() > 80 && planet.getDistanceFromStar() < 1200){
                    return true;
                }
            }
            if (this.typeOfStar.equals("pulsar")){
                System.out.println("TOO HOT FOR SURVIVAL");
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return Name + " Solar System ID: " + this.getSolarSystemId();
    }

    public int getGalaxy() {
        return galaxyId;
    }

    public void setGalaxy(int galaxy) {
        this.galaxyId = galaxy;
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

    public boolean isGoldyLoxZone() {
        return isGoldyLoxZone;
    }

    public void setGoldyLoxZone(boolean goldyLoxZone) {
        isGoldyLoxZone = goldyLoxZone;
    }

    public String getTypeOfStar() {
        return typeOfStar;
    }

    public void setTypeOfStar(String typeOfStar) {
        this.typeOfStar = typeOfStar;
    }

    public double getDistanceFromGalaxyCenter() {
        return distanceFromGalaxyCenter;
    }

    public void setDistanceFromGalaxyCenter(double distanceFromGalaxyCenter) {
        this.distanceFromGalaxyCenter = distanceFromGalaxyCenter;
    }
}
