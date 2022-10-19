package hieuntph22081.fpoly.assignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hieuntph22081.fpoly.assignment.R;
import hieuntph22081.fpoly.assignment.database.MonHocDAO;
import hieuntph22081.fpoly.assignment.database.MyDatabase;
import hieuntph22081.fpoly.assignment.model.LichHoc;

public class LichHocAdapter extends RecyclerView.Adapter<LichHocAdapter.LichHocViewHolder>{
    Context context;
    List<LichHoc> lichHocs;
    MonHocDAO monHocDAO;

    public LichHocAdapter(Context context, List<LichHoc> lichHocs) {
        this.context = context;
        this.lichHocs = lichHocs;
        monHocDAO = MyDatabase.getInstance(context).monHocDAO();
    }

    @NonNull
    @Override
    public LichHocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_lich_hoc, parent, false);
        return new LichHocViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LichHocViewHolder holder, int position) {
        LichHoc lichHoc = lichHocs.get(position);
        holder.tvMaMon.setText(lichHoc.getMaMon());
        holder.tvTenMon.setText(monHocDAO.getTenMonByMaMon(lichHoc.getMaMon()));
        holder.tvNgay.setText(lichHoc.getNgay());
        holder.tvPhong.setText(lichHoc.getPhongHoc());
    }

    @Override
    public int getItemCount() {
        return lichHocs.size();
    }

    public class LichHocViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaMon, tvTenMon, tvNgay, tvPhong;
        public LichHocViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaMon = itemView.findViewById(R.id.lichHocTvMaMon);
            tvTenMon = itemView.findViewById(R.id.lichHocTvTenMon);
            tvNgay = itemView.findViewById(R.id.lichHocTvNgay);
            tvPhong = itemView.findViewById(R.id.lichHocTvPhong);
        }
    }
}
