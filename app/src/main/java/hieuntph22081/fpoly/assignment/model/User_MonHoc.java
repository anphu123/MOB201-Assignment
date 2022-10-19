package hieuntph22081.fpoly.assignment.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (primaryKeys = {"userId","maMon"}, tableName = "dangky")
public class User_MonHoc {
    @NonNull
    private int userId;
    @NonNull
    private String maMon;

    public User_MonHoc() {
    }

    public User_MonHoc(int userId, String maMon) {
        this.userId = userId;
        this.maMon = maMon;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }
}
