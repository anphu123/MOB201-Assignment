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

import java.util.List;

import hieuntph22081.fpoly.assignment.R;
import hieuntph22081.fpoly.assignment.adapter.DangKyLopAdapter;
import hieuntph22081.fpoly.assignment.database.MonHocDAO;
import hieuntph22081.fpoly.assignment.database.MyDatabase;
import hieuntph22081.fpoly.assignment.database.User_MonHocDAO;
import hieuntph22081.fpoly.assignment.model.MonHoc;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DangKyLopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DangKyLopFragment extends Fragment {
    RecyclerView recyclerView;
    DangKyLopAdapter adapter;
    MonHocDAO monHocDAO;
    User_MonHocDAO userMonHocDAO;
    EditText txtTimLop;
    public DangKyLopFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DangKyLopFragment newInstance() {
        DangKyLopFragment fragment = new DangKyLopFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dang_ky_lop, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerViewDangKyLop);
        monHocDAO = MyDatabase.getInstance(getContext()).monHocDAO();
        userMonHocDAO = MyDatabase.instance.userMonHocDAO();
        txtTimLop = view.findViewById(R.id.txtTimLop);
        txtTimLop.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (txtTimLop.getText().toString().trim().length() == 0) {
                    loadData();
                } else {
                    adapter = new DangKyLopAdapter(getContext());
                    adapter.setData(monHocDAO.searchMonHoc("%" + txtTimLop.getText().toString().trim() + "%"));
                    LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                    recyclerView.setLayoutManager(manager);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        loadData();
    }

    public void loadData() {
        adapter = new DangKyLopAdapter(getContext());
        adapter.setData(monHocDAO.getAllMonHoc());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}