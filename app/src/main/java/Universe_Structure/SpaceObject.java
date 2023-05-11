package Universe_Structure;

import androidx.room.ColumnInfo;

public abstract class SpaceObject {
    @ColumnInfo(name = "name")
    public String Name;
    public String Discoverer;
    private String Kind;


    //Default Constructor
    SpaceObject(){
        this("", "");
    }
    SpaceObject(String name, String discoverer){
        this.Name = name;
        this.Discoverer = discoverer;

        if (this instanceof Galaxy){
            Kind = "Galaxy";
        }
        else if (this instanceof SolarSystem){
            Kind = "Solar System";
        }
        else{
            Kind = "Planet";
        }
    }

    //Methods

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDiscoverer() {
        return Discoverer;
    }

    public void setDiscoverer(String discoverer) {
        Discoverer = discoverer;
    }

    public String getKind() {
        return Kind;
    }

    public void setKind(String kind) {
        Kind = kind;
    }

    public boolean isThisUserOwner (String userName){
        return false;
    }
}
