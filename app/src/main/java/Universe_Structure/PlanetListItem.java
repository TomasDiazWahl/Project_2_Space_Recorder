package Universe_Structure;

public class PlanetListItem{
    int planetId;
    String planetName;

    public PlanetListItem (Planet planet){
        planetId = planet.getPlanetId();
        planetName = planet.getName();
    }

    @Override
    public String toString() {
        return planetName;
    }
}
