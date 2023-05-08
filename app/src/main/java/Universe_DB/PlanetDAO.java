package Universe_DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import Universe_Structure.Planet;

@Dao
public interface PlanetDAO {
    @Insert
    void Insert(Planet...planets);

    @Update
    void Update(Planet...planets);

    @Delete
    void Delete(Planet...planets);


    @Query("SELECT * FROM " + AppDataBaseSpace.PLANET_TABLE)
    List<Planet> getPlanets();

    @Query("SELECT * FROM " + AppDataBaseSpace.PLANET_TABLE + " WHERE planetId = :PlanetId")
    Planet getPlanetById(int PlanetId);

    @Query("SELECT * FROM " + AppDataBaseSpace.PLANET_TABLE + " WHERE name = :PlanetName")
    Planet getPlanetByName(String PlanetName);

    @Query("SELECT * FROM " + AppDataBaseSpace.PLANET_TABLE + " WHERE discoverer = :user")
    List<Planet> getPlanetByOwner(String user);
}
