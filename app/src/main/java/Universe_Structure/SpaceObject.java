package Universe_Structure;

public abstract class SpaceObject {

    public String Name;
    public String Discoverer;


    //Default Constructor
    SpaceObject(){
        this.Name = "";
        this.Discoverer = "Unknown";
    }
    SpaceObject(String name, String discoverer){
        this.Name = name;
        this.Discoverer = discoverer;
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
}
