package hieuntph22081.fpoly.assignment.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import hieuntph22081.fpoly.assignment.MainActivity;
import hieuntph22081.fpoly.assignment.database.MonHocDAO;
import hieuntph22081.fpoly.assignment.database.MyDatabase;
import hieuntph22081.fpoly.assignment.database.User_MonHocDAO;
import hieuntph22081.fpoly.assignment.model.MonHoc;
import hieuntph22081.fpoly.assignment.model.User_MonHoc;

public class DangKyHocService extends Service {
    User_MonHocDAO userMonHocDAO;
    public DangKyHocService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        userMonHocDAO = MyDatabase.instance.userMonHocDAO();
        String maMon = intent.getStringExtra("maMon");
        if (userMonHocDAO.getAllMaMonFromDangKy().contains(maMon)) {
            userMonHocDAO.deleteMonHocOfUser(new User_MonHoc(MainActivity.userId, maMon));
            Toast.makeText(this, "Hủy thành công!", Toast.LENGTH_SHORT).show();
        } else {
            userMonHocDAO.insertMonHocForUser(new User_MonHoc(MainActivity.userId, maMon));
            Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
        }

        return super.onStartCommand(intent, flags, startId);
    }
}