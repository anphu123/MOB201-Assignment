package hieuntph22081.fpoly.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView imgKhoaHoc, imgNews, imgMaps, imgSocial;
    public static int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgKhoaHoc = findViewById(R.id.imgKhoaHoc);
        imgNews = findViewById(R.id.imgNews);
        imgMaps = findViewById(R.id.imgMaps);
        imgSocial = findViewById(R.id.imgSocial);

        userId = getIntent().getExtras().getInt("userId");

        imgKhoaHoc.setOnClickListener(this);
        imgNews.setOnClickListener(this);
        imgMaps.setOnClickListener(this);
        imgSocial.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgKhoaHoc:
                Intent khoaHocIntent = new Intent(MainActivity.this, KhoaHocActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("userId",userId);
                khoaHocIntent.putExtras(bundle);
                startActivity(khoaHocIntent);
                break;
            case R.id.imgNews:
                startActivity(new Intent(MainActivity.this, TinTucActivity.class));
                break;
            case R.id.imgMaps:
                startActivity(new Intent(MainActivity.this, BanDoActivity.class));
                break;
            case R.id.imgSocial:
                startActivity(new Intent(MainActivity.this, XaHoiActivity.class));
                break;
        }
    }
}