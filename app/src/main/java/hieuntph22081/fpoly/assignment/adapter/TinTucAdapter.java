package hieuntph22081.fpoly.assignment.adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hieuntph22081.fpoly.assignment.R;
import hieuntph22081.fpoly.assignment.model.TinTuc;

public class TinTucAdapter extends RecyclerView.Adapter<TinTucAdapter.TinTucViewHolder>{
    Context context;
    List<TinTuc> tinTucs;
    public TinTucAdapter(Context context, List<TinTuc> tinTucs) {
        this.context = context;
        this.tinTucs = tinTucs;
    }

    @NonNull
    @Override
    public TinTucViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_tin_tuc,parent, false);
        return new TinTucViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TinTucViewHolder holder, int position) {
        TinTuc tinTuc = tinTucs.get(position);
        holder.tvTitle.setText(tinTuc.getTitle());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.tvDescription.setText(Html.fromHtml(tinTuc.getDescription(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            holder.tvDescription.setText(Html.fromHtml(tinTuc.getDescription()));
        }
        holder.tvPubDate.setText(tinTuc.getPubDate());
        holder.itemView.setOnClickListener(v -> openWebPage(tinTuc.getLink()));
    }

    public void openWebPage(String url) {
        try {
            Uri webpage = Uri.parse(url);
            Intent myIntent = new Intent(Intent.ACTION_VIEW, webpage);
            context.startActivity(myIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "No application can handle this request. Please install a web browser or check your URL.",  Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return tinTucs.size();
    }

    public class TinTucViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDescription, tvPubDate;
        public TinTucViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvPubDate = itemView.findViewById(R.id.tvPubDate);
        }
    }
}
