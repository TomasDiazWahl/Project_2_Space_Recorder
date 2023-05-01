package Universe_DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import Universe_Structure.Galaxy;

@Dao
public interface GalaxyDAO {
    @Insert
    void Insert(Galaxy...galaxies);

    @Update
    void Update(Galaxy...galaxies);

    @Delete
    void Delete(Galaxy...galaxies);


    @Query("SELECT * FROM " + AppDataBaseSpace.GALAXY_TABLE)
    List<Galaxy> getGalaxies();

    @Query("SELECT * FROM " + AppDataBaseSpace.GALAXY_TABLE + " WHERE galaxyId = :GalaxyId")
    List<Galaxy> getGalaxyById(int GalaxyId);

    @Query("SELECT * FROM " + AppDataBaseSpace.GALAXY_TABLE + " WHERE name = :GalaxyName")
    List<Galaxy> getGalaxyByName(String GalaxyName);
}
