package demo.projectZero;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import eu.long1.projectZero.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    EditText txtBadgeNum, txtPassword;
    String badgeNum, password;
    Button btnLogin;
    Switch sw_pwd;

    private SharedPreferences logInPreference;
    private SharedPreferences.Editor logInPreferenceEditor;
    private Boolean saveLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        askPermission();

        txtBadgeNum = (EditText) findViewById(R.id.badge_num);
        txtPassword = (EditText) findViewById(R.id.input_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        sw_pwd = (Switch) findViewById(R.id.sw_savepwd);


        logInPreference = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        logInPreferenceEditor = logInPreference.edit();
        saveLogin = logInPreference.getBoolean("saveLogin", false);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        if (saveLogin) {
            txtBadgeNum.setText(logInPreference.getString("badgeNum", null));
            txtPassword.setText(logInPreference.getString("password", null));
            sw_pwd.setChecked(true);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                badgeNum = txtBadgeNum.getText().toString().trim();
                password = txtPassword.getText().toString().trim();

                if (sw_pwd.isChecked()) {
                    logInPreferenceEditor.putBoolean("saveLogin", true);
                    logInPreferenceEditor.putString("badgeNum", badgeNum);
                    logInPreferenceEditor.putString("password", password);
                    logInPreferenceEditor.apply();
                } else {
                    logInPreferenceEditor.clear();
                    logInPreferenceEditor.apply();
                }

                if (badgeNum.isEmpty()) {
                    txtBadgeNum.setError("请输入工号");
                } else if(password.isEmpty()){
                    txtPassword.setError("请输入密码");
                } else if (!authentication(badgeNum, password)) {
                    Snackbar.make(v, "密码不正确，请联系管理员", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                } else {
                    Intent i = new Intent(getApplicationContext(), FrontActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode) {
            case(1):
                if(grantResults.length > 0
                        && (grantResults[0] == PackageManager.PERMISSION_DENIED
                        || grantResults[1] == PackageManager.PERMISSION_DENIED)) {
                    new MaterialDialog.Builder(this)
                            .title("注意！")
                            .titleColorRes(R.color.primary)
                            .content("不授权将会导致APP无法使用")
                            .positiveText("确认")
                            .positiveColorRes(R.color.tbgreen)
                            .negativeText("取消")
                            .negativeColorRes(R.color.tbgreen)
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    askPermission();
                                }
                            })
                            .onNegative(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    finishAffinity();
                                }
                            })
                            .show();
                }

        }
    }

    private boolean authentication(String badgeNum, String pwd) {
//        some authentication logic

        String pwd_hashed = new String(Hex.encodeHex(DigestUtils.sha256(pwd)));
        return badgeNum.equals("364052") &&
                pwd_hashed.equals("6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b");
    }

    private void askPermission() {
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) ||
        (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

}
