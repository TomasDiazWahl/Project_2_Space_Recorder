package Universe_Structure;

public class Planet extends SpaceObject{

    //Variables
    private String Population;
    private String Climate;
    private SolarSystem solarSystem;

    //Constructors
    Planet(){
        new Planet("", "", "", "", new SolarSystem());
    }

    Planet(String name, String discoverer, String population, String climate, SolarSystem s){
        super(name, discoverer);
        this.Population = population;
        this.Climate = climate;
        this.solarSystem = s;
    }

    //Methods

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
