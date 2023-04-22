package Universe_DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import Universe_Structure.SpaceObject;

@Database(entities = {SpaceObject.class}, version = 1)
public abstract class AppDataBaseSpace extends RoomDatabase {
    public static final String DATABASE_NAME = "SpaceObject.db";
    public static final String DATABASE_TABLE = "SpaceObject_table";
    private static volatile AppDataBaseSpace instance;
    private static final Object LOCK = new Object();
    public abstract SpaceObjectDAO GalaxyDAO();

    public static AppDataBaseSpace getInstance(Context context){
        if (instance == null){
            synchronized (LOCK){
                if (instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBaseSpace.class, DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }
}
