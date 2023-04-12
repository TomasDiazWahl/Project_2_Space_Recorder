package Universe_DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
interface GalaxyDAO {

    @Insert
    void Insert();

    @Update
    void Update();

    @Delete
    void Delete();
}
