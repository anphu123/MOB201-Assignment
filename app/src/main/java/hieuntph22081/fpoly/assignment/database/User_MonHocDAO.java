package hieuntph22081.fpoly.assignment.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import hieuntph22081.fpoly.assignment.model.User;
import hieuntph22081.fpoly.assignment.model.User_MonHoc;

@Dao
public interface User_MonHocDAO {
    @Insert
    void insertMonHocForUser(User_MonHoc userMonHoc);

    @Query("SELECT * FROM dangky WHERE userId = (:userId)")
    List<User_MonHoc> getUserWithMonHocs(int userId);

    @Query("SELECT * FROM dangky")
    List<User_MonHoc> getAllDangKy();

    @Query("SELECT maMon FROM dangky GROUP BY maMon")
    List<String> getAllMaMonFromDangKy();

    @Delete
    void deleteMonHocOfUser(User_MonHoc userMonHoc);
}
