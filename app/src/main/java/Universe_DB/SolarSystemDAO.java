package Universe_DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import Universe_Structure.SolarSystem;

@Dao

public interface SolarSystemDAO {
    @Insert
    void Insert(SolarSystem...solarSystems);

    @Update
    void Update(SolarSystem...solarSystems);

    @Delete
    void Delete(SolarSystem...solarSystems);


    @Query("SELECT * FROM " + AppDataBaseSpace.SOLAR_SYSTEM_TABLE)
    List<SolarSystem> getSolarSystems();

    @Query("SELECT * FROM " + AppDataBaseSpace.SOLAR_SYSTEM_TABLE + " WHERE SolarSystemId = :SolarSystemId")
    SolarSystem getSolarSystemById(int SolarSystemId);

    @Query("SELECT * FROM " + AppDataBaseSpace.SOLAR_SYSTEM_TABLE + " WHERE name = :SolarSystemName")
    SolarSystem getSolarSystemByName(String SolarSystemName);

    @Query("SELECT * FROM " + AppDataBaseSpace.SOLAR_SYSTEM_TABLE + " WHERE discoverer = :SolarSystemOwner")
    List<SolarSystem> getSolarSystemByOwner(String SolarSystemOwner);
}
