package Universe_Structure;

import androidx.room.Entity;

import java.util.ArrayList;

@Entity
public class Galaxy {


    //Name of the galaxy
    private String name;
    private ArrayList<SolarSystem> Solar_Systems;
}
