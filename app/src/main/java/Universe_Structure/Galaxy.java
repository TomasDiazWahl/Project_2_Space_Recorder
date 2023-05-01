package Universe_Structure;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import Universe_DB.AppDataBaseSpace;

@Entity(tableName = AppDataBaseSpace.GALAXY_TABLE)
public class Galaxy extends SpaceObject{

    //Variables
    @PrimaryKey(autoGenerate = true)
    private int galaxyId;
    //private ArrayList<SolarSystem> SolarSystems;



    //Constructors
    public Galaxy(){
        new Galaxy("", "Unknown");
    }

    public Galaxy(String name, String discoverer){
        super(name, discoverer);
        //this.SolarSystems = new ArrayList<>();
    }



    //Methods
    /*void addSolarSystem(SolarSystem solarSystem){
        SolarSystems.add(solarSystem);
    }



    //Getters and Setters

    public ArrayList<SolarSystem> getSolarSystems() {
        return SolarSystems;
    }

    public void setSolarSystems(ArrayList<SolarSystem> solarSystems) {
        SolarSystems = solarSystems;
    }*/

    //Getters and Setters

    public int getGalaxyId() {
        return galaxyId;
    }

    public void setGalaxyId(int galaxyId) {
        this.galaxyId = galaxyId;
    }
}
