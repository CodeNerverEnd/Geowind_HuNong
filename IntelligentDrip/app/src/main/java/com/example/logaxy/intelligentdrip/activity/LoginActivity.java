package com.example.logaxy.intelligentdrip.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.logaxy.intelligentdrip.R;

public class LoginActivity extends BaseActivity {

    private String userName = "123456";
    private String passWord = "123456";

    private Button loginButton;
    private EditText userNameEditText;
    private EditText passwordEdtiText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initData();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usr = userNameEditText.getText().toString();
                String pwd = passwordEdtiText.getText().toString();

                if (checkUser(usr, pwd)) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    initData();
                    LoginActivity.this.finish();
                } else {
                    Toast.makeText(LoginActivity.this, "账号或密码有误！", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private boolean checkUser(String usr, String pwd) {
        if (usr.equals(userName) && pwd.equals(passWord))
            return true;
        return false;
    }

    private void initData() {
        userNameEditText.setText("");
        passwordEdtiText.setText("");
    }

    private void initView() {
        loginButton = (Button) findViewById(R.id.btn_Login);
        userNameEditText = (EditText) findViewById(R.id.edt_UerName);
        passwordEdtiText = (EditText) findViewById(R.id.edt_PassWord);
    }
}
