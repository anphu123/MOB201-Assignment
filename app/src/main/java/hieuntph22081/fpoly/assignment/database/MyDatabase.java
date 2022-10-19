package hieuntph22081.fpoly.assignment.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import hieuntph22081.fpoly.assignment.model.LichHoc;
import hieuntph22081.fpoly.assignment.model.MonHoc;
import hieuntph22081.fpoly.assignment.model.User;
import hieuntph22081.fpoly.assignment.model.User_MonHoc;

@Database(entities = {User.class, MonHoc.class, LichHoc.class, User_MonHoc.class}, version = 1)
//room.schemaLocation
public abstract class MyDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "ASM_MOD2041.db";
    public static MyDatabase instance;
    public static synchronized MyDatabase getInstance(Context context) {
        if (instance == null) {
            RoomDatabase.Callback rdc = new RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                    db.execSQL("INSERT INTO user VALUES (null,'admin','admin','Nguyen Tien Hieu')");
                    db.execSQL("INSERT INTO MONHOC VALUES('MOB201','Android Nâng Cao','Thầy Huy')," +
                            "('MOB2041','DAM','Thầy Huy ')," +
                            "('ENT2224','Tiếng Anh','Hoàng Thị Thùy Linh')");

                    db.execSQL("INSERT INTO lichhoc VALUES" +
                            "(null, 'MOB201', 'Ca 4 - 03/10/2022', 'D406', 0)," +
                            "(null, 'MOB201', 'Ca 4 - 05/10/2022', 'D406', 0)," +
                            "(null, 'MOB201', 'Ca 4 - 07/10/2022', 'D406', 0)," +
                            "(null, 'MOB201', 'Ca 4 - 10/10/2022', 'D406', 1)," +
                            "(null, 'MOB2041', 'Ca 1 - 05/10/2022', 'Online - https://meet.google.com/zot-hifb-mpv?pli=1&authuser=1', 0)," +
                            "(null, 'MOB2041', 'Ca 1 - 12/10/2022', 'Online - https://meet.google.com/zot-hifb-mpv?pli=1&authuser=1', 0)," +
                            "(null, 'MOB2041', 'Ca 1 - 19/10/2022', 'Online - https://meet.google.com/zot-hifb-mpv?pli=1&authuser=1', 0)," +
                            "(null, 'MOB2041', 'Ca 1 - 26/10/2022', 'Online - https://meet.google.com/zot-hifb-mpv?pli=1&authuser=1', 1)," +
                            "(null, 'ENT2224', 'Ca 1 - 04/10/2022', 'Online - https://meet.google.com/zot-hifb-mpv?pli=1&authuser=1', 0)," +
                            "(null, 'ENT2224', 'Ca 1 - 11/10/2022', 'Online - https://meet.google.com/zot-hifb-mpv?pli=1&authuser=1', 0)," +
                            "(null, 'ENT2224', 'Ca 1 - 18/10/2022', 'Online - https://meet.google.com/zot-hifb-mpv?pli=1&authuser=1', 1)");

                    db.execSQL("INSERT INTO DANGKY VALUES(1,'MOB201')," +
                            "(1,'MOB2041'),"+ "(1,'ENT2224')");
                }

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                }
            };

            instance = Room.databaseBuilder(context.getApplicationContext(), MyDatabase.class, DATABASE_NAME)
                    .addCallback(rdc)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public abstract UserDAO userDAO();
    public abstract MonHocDAO monHocDAO();
    public abstract LichHocDAO lichHocDAO();
    public abstract User_MonHocDAO userMonHocDAO();
}
