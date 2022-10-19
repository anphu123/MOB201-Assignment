package hieuntph22081.fpoly.assignment.database;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import hieuntph22081.fpoly.assignment.model.LichHoc;
import hieuntph22081.fpoly.assignment.model.MonHoc;

@Dao
public interface LichHocDAO {
    @Query("SELECT * FROM lichhoc WHERE maMon = :maMon AND thi = 0")
    List<LichHoc> getLichHocByMaMon(String maMon);

    @Query("SELECT * FROM lichhoc WHERE maMon = :maMon AND thi = 1")
    List<LichHoc> getLichThiByMaMon(String maMon);

    @Query("SELECT * FROM lichhoc WHERE maMon LIKE :key AND thi = 0")
    List<LichHoc> searchLichHoc(String key);

    @Query("SELECT * FROM lichhoc WHERE maMon LIKE :key AND thi = 1")
    List<LichHoc> searchLichThi(String key);
}
