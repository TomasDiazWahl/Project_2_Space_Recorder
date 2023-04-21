package Universe_DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import Universe_Structure.SpaceObject;

@Dao
interface SpaceObjectDAO {

    @Insert
    void Insert(SpaceObject...spaceObjects);

    @Update
    void Update(SpaceObject...spaceObjects);

    @Delete
    void Delete(SpaceObject...spaceObjects);
}
