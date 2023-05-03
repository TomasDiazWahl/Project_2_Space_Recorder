package Universe_DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import Universe_Structure.Galaxy;
import Universe_Structure.Planet;
import Universe_Structure.SolarSystem;

@Database(entities = {Planet.class, SolarSystem.class, Galaxy.class}, version = 1)
public abstract class AppDataBaseSpace extends RoomDatabase {
    public static final String DATABASE_NAME = "SpaceObject.db";
    public static final String PLANET_TABLE =  "planet_table";
    public static final String SOLAR_SYSTEM_TABLE = "solar_system_table";
    public static final String GALAXY_TABLE = "galaxy_table";
    private static volatile AppDataBaseSpace instance;
    private static final Object LOCK = new Object();
    public abstract GalaxyDAO GalaxyDAO();
    public abstract SolarSystemDAO SolarSystemDAO();
    public abstract PlanetDAO PlanetDAO();

    public static AppDataBaseSpace getInstance(Context context){
        if (instance == null){
            synchronized (LOCK){
                if (instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBaseSpace.class, DATABASE_NAME)
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }
}
