package UserDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert
    void Insert(User...users);

    @Update
    void Update(User...users);

    @Delete
    void Delete(User...users);

    @Query("SELECT * FROM " + AppDataBaseUser.DATABASE_TABLE)
    List<User> getUsers();

    @Query("SELECT * FROM " + AppDataBaseUser.DATABASE_TABLE + " WHERE UserId = :userId")
    List<User> getUserById(int userId);

    @Query("SELECT * FROM " + AppDataBaseUser.DATABASE_TABLE + " WHERE name = :username")
    List<User> getUserByName(String username);
}
