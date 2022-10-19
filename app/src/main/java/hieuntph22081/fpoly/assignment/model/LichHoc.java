package hieuntph22081.fpoly.assignment.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class LichHoc implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String maMon;
    private String ngay;
    private String phongHoc;
    private int thi;

    public LichHoc() {
    }

    public LichHoc(int id, String maMon, String ngay, String phongHoc, int thi) {
        this.id = id;
        this.maMon = maMon;
        this.ngay = ngay;
        this.phongHoc = phongHoc;
        this.thi = thi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getPhongHoc() {
        return phongHoc;
    }

    public void setPhongHoc(String phongHoc) {
        this.phongHoc = phongHoc;
    }

    public int getThi() {
        return thi;
    }

    public void setThi(int thi) {
        this.thi = thi;
    }
}
