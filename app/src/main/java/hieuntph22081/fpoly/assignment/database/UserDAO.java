package hieuntph22081.fpoly.assignment.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import hieuntph22081.fpoly.assignment.model.User;

@Dao
public interface UserDAO {
    @Insert
    void insertUser(User user);

    @Query("select * from user")
    List<User> getAllUser();
}
