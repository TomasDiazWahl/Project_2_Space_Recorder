package UserDB;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class AppDataBaseUser extends RoomDatabase {
    public static final String DATABASE_NAME = "User.db";
    public static final String DATABASE_TABLE = "User_table";
    private static volatile AppDataBaseUser instance;
    private static final Object LOCK = new Object();
    public abstract UserDAO UserDAO();

    public static AppDataBaseUser getInstance(Context context){
        if (instance == null){
            synchronized (LOCK){
                if (instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBaseUser.class, DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }
}
