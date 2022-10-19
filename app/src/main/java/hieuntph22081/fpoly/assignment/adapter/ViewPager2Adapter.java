package hieuntph22081.fpoly.assignment.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import hieuntph22081.fpoly.assignment.fragment.DangKyLopFragment;
import hieuntph22081.fpoly.assignment.fragment.LichHocFragment;
import hieuntph22081.fpoly.assignment.fragment.LichThiFragment;

public class ViewPager2Adapter extends FragmentStateAdapter {
    public ViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new LichHocFragment();
            case 1:
                return new LichThiFragment();
            case 2:
                return new DangKyLopFragment();
            default:
                return new LichHocFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
