package hieuntph22081.fpoly.assignment.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import hieuntph22081.fpoly.assignment.MainActivity;
import hieuntph22081.fpoly.assignment.database.LichHocDAO;
import hieuntph22081.fpoly.assignment.database.MyDatabase;
import hieuntph22081.fpoly.assignment.database.User_MonHocDAO;
import hieuntph22081.fpoly.assignment.model.LichHoc;
import hieuntph22081.fpoly.assignment.model.User_MonHoc;

public class LichThiService extends Service {
    LichHocDAO lichHocDAO;
    User_MonHocDAO userMonHocDAO;
    List<User_MonHoc> user_monHocs = new ArrayList<>();
    List<LichHoc> lichHocs = new ArrayList<>();

    public LichThiService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        lichHocDAO = MyDatabase.instance.lichHocDAO();
        userMonHocDAO = MyDatabase.instance.userMonHocDAO();
        user_monHocs = userMonHocDAO.getUserWithMonHocs(MainActivity.userId);
        lichHocs.clear();
        for (User_MonHoc um : user_monHocs) {
                lichHocs.addAll(lichHocDAO.getLichThiByMaMon(um.getMaMon()));
        }
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction("hieuntph22081.fpoly.assignment.LICH_THI");
        Bundle bundle = new Bundle();
        bundle.putSerializable("lichThi", (Serializable) lichHocs);
        broadcastIntent.putExtras(bundle);
        sendBroadcast(broadcastIntent);
        return super.onStartCommand(intent, flags, startId);
    }
}