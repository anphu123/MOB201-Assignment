package hieuntph22081.fpoly.assignment.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import hieuntph22081.fpoly.assignment.MainActivity;
import hieuntph22081.fpoly.assignment.R;
import hieuntph22081.fpoly.assignment.adapter.DangKyLopAdapter;
import hieuntph22081.fpoly.assignment.adapter.LichHocAdapter;
import hieuntph22081.fpoly.assignment.database.LichHocDAO;
import hieuntph22081.fpoly.assignment.database.MyDatabase;
import hieuntph22081.fpoly.assignment.database.User_MonHocDAO;
import hieuntph22081.fpoly.assignment.model.LichHoc;
import hieuntph22081.fpoly.assignment.model.User_MonHoc;
import hieuntph22081.fpoly.assignment.service.LichHocService;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LichHocFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LichHocFragment extends Fragment {
    RecyclerView recyclerView;
    List<LichHoc> lichHocs = new ArrayList<>();
    LichHocAdapter adapter;
    IntentFilter filter;
    EditText txtTimLop;
    LichHocDAO lichHocDAO;
    public LichHocFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static LichHocFragment newInstance() {
        LichHocFragment fragment = new LichHocFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lich_hoc, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerViewLichHoc);
        lichHocDAO = MyDatabase.instance.lichHocDAO();
        Intent intent = new Intent(getContext(), LichHocService.class);
        getContext().startService(intent);
        filter = new IntentFilter("hieuntph22081.fpoly.assignment.LICH_HOC");

        txtTimLop = view.findViewById(R.id.txtTimLop);
        txtTimLop.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (txtTimLop.getText().toString().trim().length() == 0) {
                    getContext().startService(intent);
                } else {
                    adapter = new LichHocAdapter(getContext(), lichHocDAO.searchLichHoc("%" + txtTimLop.getText().toString().trim() + "%"));
                    LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                    recyclerView.setLayoutManager(manager);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        Intent intent = new Intent(getContext(), LichHocService.class);
        getContext().startService(intent);
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            lichHocs = (List<LichHoc>) intent.getExtras().getSerializable("lichHoc");
            adapter = new LichHocAdapter(getContext(), lichHocs);
            LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        getContext().registerReceiver(broadcastReceiver,filter);
    }

    @Override
    public void onStop() {
        super.onStop();
        getContext().unregisterReceiver(broadcastReceiver);
    }
}