package UserDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface UserDAO {

    @Insert
    void Insert(User...users);

    @Update
    void Update(User...users);

    @Delete
    void Delete(User...users);
}
