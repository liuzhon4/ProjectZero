package eu.long1.projectZero;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    EditText txtBadgeNum, txtPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtBadgeNum = (EditText) findViewById(R.id.badge_num);
        txtPassword = (EditText) findViewById(R.id.input_password);
        btnLogin = (Button) findViewById(R.id.btn_login);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtBadgeNum.getText().toString().trim().isEmpty()){
                    txtBadgeNum.setError("请输入工号");
                }else if(txtPassword.getText().toString().trim().isEmpty()){
                    txtPassword.setError("请输入密码");
                }else if (!authentication(txtBadgeNum.getText().toString().trim(), txtPassword.getText().toString().trim())) {
                    Snackbar.make(v, "密码不正确，请联系管理员", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                } else {
//                    Snackbar.make(v, "No validation errors, continue login", Snackbar.LENGTH_LONG).setAction("Action",null).show();
                    Intent i = new Intent(getApplicationContext(), FrontActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }

    public final static boolean isValidEmail(CharSequence target){
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public boolean authentication(String badgeNum, String pwd) {
//        some authentication logic
        return badgeNum.equals("364052") && pwd.equals("1");
    }
}
