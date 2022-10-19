package hieuntph22081.fpoly.assignment.database;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import hieuntph22081.fpoly.assignment.model.MonHoc;

@Dao
public interface MonHocDAO {
    @Query("SELECT tenMonHoc FROM monhoc WHERE maMon = :maMon")
    String getTenMonByMaMon(String maMon);

    @Query("SELECT * FROM monhoc")
    List<MonHoc> getAllMonHoc();

    @Query("SELECT * FROM monhoc WHERE maMon LIKE :key OR tenMonHoc LIKE :key")
    List<MonHoc> searchMonHoc(String key);
}
