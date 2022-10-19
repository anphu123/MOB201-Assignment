package hieuntph22081.fpoly.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hieuntph22081.fpoly.assignment.database.MyDatabase;
import hieuntph22081.fpoly.assignment.database.UserDAO;
import hieuntph22081.fpoly.assignment.model.User;

public class LoginActivity extends AppCompatActivity {
    UserDAO userDAO;
    EditText txtUserName,txtPassword;
    TextView tvSignUp;
    Button btnLogin;
    CheckBox chkRemember;
    int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUserName = findViewById(R.id.loginTxtUserName);
        txtPassword = findViewById(R.id.loginTxtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        chkRemember = findViewById(R.id.chkRemember);
        tvSignUp = findViewById(R.id.tvSignUp);
        MyDatabase.getInstance(this);
        userDAO = MyDatabase.getInstance(this).userDAO();

        List<Object> chkList = readPreference();
        if (chkList.size()>0)
             if (Boolean.parseBoolean(chkList.get(3).toString())){
                txtUserName.setText(chkList.get(1).toString());
                txtPassword.setText(chkList.get(2).toString());
                chkRemember.setChecked(Boolean.parseBoolean(chkList.get(3).toString()));
            }

        tvSignUp.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
        });

        btnLogin.setOnClickListener(v -> {
            String u = txtUserName.getText().toString();
            String p = txtPassword.getText().toString();
            boolean status = chkRemember.isChecked();

            if (u.length() == 0 || p.length() == 0) {
                Toast.makeText(LoginActivity.this, "Không để trống các ô", Toast.LENGTH_SHORT).show();
            } else {
                boolean check = false;
                for (User user : userDAO.getAllUser()) {
                    if (u.equals(user.getUserName()) && p.equals(user.getPassword())) {
                        check = true;
                        userId = user.getId();
                        break;
                    }
                }
                if (check) {
                    savePreference(userId,u,p,status);
                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("userId", userId);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Thông tin sai", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    void savePreference(int id, String u, String p, boolean status) {
        SharedPreferences s = getSharedPreferences("MY_FILE",MODE_PRIVATE);
        SharedPreferences.Editor editor = s.edit();
        if (!status) { // Khong luu
            editor.clear();
        } else { // luu
            editor.putInt("id", id);
            editor.putString("U",u);
            editor.putString("P",p);
            editor.putBoolean("CHK",status);
        }
        editor.commit();
    }

    List<Object> readPreference() {
        List<Object> ls = new ArrayList<>();
        SharedPreferences s = getSharedPreferences("MY_FILE",MODE_PRIVATE);
        ls.add(s.getInt("id",-1));
        ls.add(s.getString("U",""));
        ls.add(s.getString("P",""));
        ls.add(s.getBoolean("CHK",false));
        return ls;
    }
}