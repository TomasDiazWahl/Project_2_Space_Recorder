package Universe_Structure;

import androidx.room.Entity;

import java.util.ArrayList;

@Entity
public class Galaxy extends SpaceObject{

    //Variables
    private ArrayList<SolarSystem> SolarSystems;



    //Constructors
    Galaxy(){
        new Galaxy("", "Unknown");
    }

    Galaxy(String name, String discoverer){
        super(name, discoverer);
        this.SolarSystems = new ArrayList<>();
    }



    //Methods
    void addSolarSystem(SolarSystem solarSystem){
        SolarSystems.add(solarSystem);
    }



    //Getters and Setters

    public ArrayList<SolarSystem> getSolarSystems() {
        return SolarSystems;
    }

    public void setSolarSystems(ArrayList<SolarSystem> solarSystems) {
        SolarSystems = solarSystems;
    }
}
