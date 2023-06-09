package hieuntph22081.fpoly.assignment;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import hieuntph22081.fpoly.assignment.database.MyDatabase;
import hieuntph22081.fpoly.assignment.database.UserDAO;
import hieuntph22081.fpoly.assignment.model.User;

public class SignUpActivity extends AppCompatActivity {
    EditText txtUsername, txtPassword, txtRepassword, txtFullname;
    Button btnSignup, btnCancel;
    UserDAO userDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        txtFullname = findViewById(R.id.signupTxtFullname);
        txtRepassword = findViewById(R.id.signupTxtRepassword);
        txtUsername = findViewById(R.id.signupTxtUsername);
        txtPassword = findViewById(R.id.signupTxtPassword);
        btnSignup = findViewById(R.id.signupBtnSignup);
        btnCancel = findViewById(R.id.signupBtnCancel);
        userDAO = MyDatabase.getInstance(this).userDAO();

        btnSignup.setOnClickListener(v -> {
            if (txtUsername.getText().toString().length() == 0
                    || txtPassword.getText().toString().length() == 0
                    || txtFullname.getText().toString().length() == 0
                    || txtRepassword.getText().toString().length() == 0 ) {
                Toast.makeText(SignUpActivity.this, "Không để trống các ô", Toast.LENGTH_SHORT).show();
                return;
            } else {
                for (User u : userDAO.getAllUser()) {
                    if (txtUsername.getText().toString().equalsIgnoreCase(u.getUserName())) {
                        Toast.makeText(SignUpActivity.this, "username đã tồn tại", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
            if (txtPassword.getText().toString().equals(txtRepassword.getText().toString())) {
                Toast.makeText(SignUpActivity.this, "Mật khẩu không trùng khớp!!!", Toast.LENGTH_SHORT).show();
                txtRepassword.requestFocus();
                return;
            }
            User user = new User();
            user.setUserName(txtUsername.getText().toString());
            user.setPassword(txtPassword.getText().toString());
            user.setFullName(txtFullname.getText().toString());
            userDAO.insertUser(user);
            Toast.makeText(SignUpActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
            finish();
        });

        btnCancel.setOnClickListener(v -> finish());
    }
}