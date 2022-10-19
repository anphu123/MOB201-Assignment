package hieuntph22081.fpoly.assignment.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MonHoc {
    @PrimaryKey (autoGenerate = false)
    @NonNull
    private String maMon;
    private String tenMonHoc;
    private String giaoVien;

    public MonHoc() {
    }

    public MonHoc(String maMon, String tenMonHoc, String giaoVien) {
        this.maMon = maMon;
        this.tenMonHoc = tenMonHoc;
        this.giaoVien = giaoVien;
    }

    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public String getGiaoVien() {
        return giaoVien;
    }

    public void setGiaoVien(String giaoVien) {
        this.giaoVien = giaoVien;
    }
}
