package demo.projectZero;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.angads25.filepicker.controller.DialogSelectionListener;
import com.github.angads25.filepicker.model.DialogConfigs;
import com.github.angads25.filepicker.model.DialogProperties;
import com.github.angads25.filepicker.view.FilePickerDialog;

import java.io.File;

import eu.long1.projectZero.R;

public class MerchantActivity extends AppCompatActivity {
    Button btn1;
    Button btn2;
    Button btn3;
    FilePickerDialog filePicker;
    DialogProperties properties = new DialogProperties();
    String filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant);


        //initialize buttons
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);

        //initialize select file dialog
        properties.selection_mode = DialogConfigs.SINGLE_MODE;
        properties.selection_type = DialogConfigs.FILE_SELECT;
        //weChat default download library
        properties.root = new File("/mnt/sdcard/tencent/MicroMsg/Download");
        properties.error_dir = new File(DialogConfigs.DEFAULT_DIR);
        properties.offset = new File(DialogConfigs.DEFAULT_DIR);
        properties.extensions = new String[] {"xls", "csv", "xlsx"};

        filePicker = new FilePickerDialog(MerchantActivity.this, properties);
        filePicker.setTitle("选取文件");
        filePicker.setPositiveBtnName("确认");
        filePicker.setNegativeBtnName("取消");

        filePicker.setDialogSelectionListener(new DialogSelectionListener() {
            @Override
            public void onSelectedFilePaths(String[] files) {
                filePath = files[0];
                Toast.makeText(getApplicationContext(), "文件选取成功", Toast.LENGTH_LONG).show();

            }
        });

        MaterialDialog.Builder alert = new MaterialDialog.Builder(this)
                .title("注意！")
                .titleColorRes(R.color.primary)
                .content("请先选取三户管理文件")
                .positiveText("确认")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        filePicker.show();
                    }
                })
                .negativeText("取消");
        alert.show();

        //button listeners
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Merchant1Activity.class);
                i.putExtra("filePath", filePath);
                startActivity(i);

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Merchant2Activity.class);
                i.putExtra("filePath", filePath);
                startActivity(i);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Merchant3Activity.class);
                i.putExtra("filePath", filePath);
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_import_file, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_import_file:
//                Toast.makeText(getApplicationContext(), "导入档案", Toast.LENGTH_SHORT).show();
                filePicker.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case FilePickerDialog.EXTERNAL_READ_PERMISSION_GRANT: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(filePicker != null) {
                        filePicker.show();
                    }
                } else {
                    Toast.makeText(MerchantActivity.this, "请开启内存使用权限", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
