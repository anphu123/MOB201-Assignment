package hieuntph22081.fpoly.assignment.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hieuntph22081.fpoly.assignment.R;
import hieuntph22081.fpoly.assignment.database.MyDatabase;
import hieuntph22081.fpoly.assignment.database.User_MonHocDAO;
import hieuntph22081.fpoly.assignment.model.MonHoc;
import hieuntph22081.fpoly.assignment.model.User_MonHoc;
import hieuntph22081.fpoly.assignment.service.DangKyHocService;

public class DangKyLopAdapter extends RecyclerView.Adapter<DangKyLopAdapter.DangKyLopViewHolder> {
    Context context;
    List<MonHoc> monHocs;
    User_MonHocDAO userMonHocDAO;

    public DangKyLopAdapter(Context context) {
        this.context = context;
        userMonHocDAO = MyDatabase.instance.userMonHocDAO();
    }

    public void setData(List<MonHoc> monHocs) {
        this.monHocs = monHocs;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DangKyLopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_dang_ky,parent,false);
        return new DangKyLopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DangKyLopViewHolder holder, int position) {
        MonHoc monHoc = monHocs.get(position);
        holder.tvMaMon.setText(monHoc.getMaMon());
        holder.tvTenMon.setText(monHoc.getTenMonHoc());

        int count = 0;
        for (User_MonHoc um : userMonHocDAO.getAllDangKy()) {
            if (um.getMaMon().equals(monHoc.getMaMon())) {
                count++;
            }
        }

        if (count > 0) {
            holder.btnDangKyLop.setText("Hủy đăng ký");
            holder.btnDangKyLop.setBackgroundColor(Color.RED);
        } else {
            holder.btnDangKyLop.setText("Đăng ký");
            holder.btnDangKyLop.setBackgroundColor(Color.BLUE);
        }

        holder.btnDangKyLop.setOnClickListener(v -> {
            Intent intent = new Intent(context, DangKyHocService.class);
            intent.putExtra("maMon", monHoc.getMaMon());
            context.startService(intent);
            if (holder.btnDangKyLop.getText().equals("Hủy đăng ký")) {
                holder.btnDangKyLop.setText("Đăng ký");
                holder.btnDangKyLop.setBackgroundColor(Color.BLUE);
            } else {
                holder.btnDangKyLop.setText("Hủy đăng ký");
                holder.btnDangKyLop.setBackgroundColor(Color.RED);
            }
        });
    }

    @Override
    public int getItemCount() {
        return monHocs.size();
    }

    public class DangKyLopViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaMon, tvTenMon;
        Button btnDangKyLop;
        public DangKyLopViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaMon = itemView.findViewById(R.id.dangKyTvMaMon);
            tvTenMon = itemView.findViewById(R.id.dangKyTvTenMon);
            btnDangKyLop = itemView.findViewById(R.id.btnDangKyLop);
        }
    }
}
