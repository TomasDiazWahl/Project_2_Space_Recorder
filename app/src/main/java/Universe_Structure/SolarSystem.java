package Universe_Structure;

import java.util.ArrayList;

public class SolarSystem extends SpaceObject{

    //Variables
    private ArrayList<Planet> Planets;
    private Galaxy galaxy;

    //Constructors
    SolarSystem(){
        new SolarSystem("", "Unknown", new Galaxy());
    }

    SolarSystem(String name,String discoverer, Galaxy galaxy){
        super(name, discoverer);
        this.Planets = new ArrayList<>();
        this.galaxy = galaxy;
    }




    //Methods

    void addPlanet(Planet planet){
        Planets.add(planet);
    }




    //Getters and Setters

    public ArrayList<Planet> getPlanets() {
        return Planets;
    }

    public void setPlanets(ArrayList<Planet> planets) {
        Planets = planets;
    }

    public Galaxy getGalaxy() {
        return galaxy;
    }

    public void setGalaxy(Galaxy galaxy) {
        this.galaxy = galaxy;
    }
}
