package hieuntph22081.fpoly.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import hieuntph22081.fpoly.assignment.adapter.ViewPager2Adapter;
import hieuntph22081.fpoly.assignment.fragment.DangKyLopFragment;
import hieuntph22081.fpoly.assignment.fragment.LichHocFragment;
import hieuntph22081.fpoly.assignment.fragment.LichThiFragment;

public class KhoaHocActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {
    BottomNavigationView bottomNav;
    ViewPager2 viewPager2;
    ViewPager2Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khoa_hoc);

        bottomNav = findViewById(R.id.bottomNav);
        viewPager2 = findViewById(R.id.viewPage2);
        adapter = new ViewPager2Adapter(this);
        viewPager2.setAdapter(adapter);
        viewPager2.setPageTransformer(new DepthPageTransformer());
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        bottomNav.getMenu().findItem(R.id.navigation_xem_lich_hoc).setChecked(true);
                        break;
                    case 1:
                        bottomNav.getMenu().findItem(R.id.navigation_lich_thi).setChecked(true);
                        break;
                    case 2:
                        bottomNav.getMenu().findItem(R.id.navigation_dang_ky_khoa_hoc).setChecked(true);
                        break;
                }
            }
        });
        bottomNav.setOnItemSelectedListener(this);
//        replaceFragment(new LichHocFragment());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_xem_lich_hoc:
                viewPager2.setCurrentItem(0);
                break;

            case R.id.navigation_lich_thi:
                viewPager2.setCurrentItem(1);
                break;

            case R.id.navigation_dang_ky_khoa_hoc:
                viewPager2.setCurrentItem(2);
                break;
        }
        return true;
    }

}