package demo.projectZero;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;


import com.rengwuxian.materialedittext.MaterialEditText;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import de.mrapp.android.dialog.MaterialDialog;
import eu.long1.projectZero.R;

public class CharacterActivity extends AppCompatActivity {

    String[] Option1 = {"简易案件", "一般案件"};
    String[] Option2 = {"东城区", "西城区", "海淀区", "朝阳区", "丰台区", "门头沟区", "石景山区",
            "房山区", "通州区", "顺义区", "昌平区", "大兴区", "怀柔区", "平谷区", "延庆区", "密云区"};
    String op1, op2, fileNumber;
    ArrayAdapter<String> arrayAdapter1, arrayAdapter2;
    MaterialEditText materialEditText;
    MaterialBetterSpinner materialDesignSpinner1, materialDesignSpinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);
        arrayAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, Option1);
        materialDesignSpinner1 = (MaterialBetterSpinner) findViewById(R.id.character);
        materialDesignSpinner1.setAdapter(arrayAdapter1);
        //set default value 简易案件
        materialDesignSpinner1.setText(arrayAdapter1.getItem(0));

        arrayAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, Option2);
        materialDesignSpinner2 = (MaterialBetterSpinner) findViewById(R.id.area);
        materialDesignSpinner2.setAdapter(arrayAdapter2);
        //set default value 房山区
        materialDesignSpinner2.setText(arrayAdapter2.getItem(7));

        //no default value for fileNum
        materialEditText = (MaterialEditText) findViewById(R.id.fileNum);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_single_finish, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_single_finish:
                op1 = materialDesignSpinner1.getText().toString();
                op2 = materialDesignSpinner2.getText().toString();
                fileNumber = materialEditText.getText().toString();
                if (fileNumber.length() > 3) {
                    MaterialDialog.Builder dialogBuilder = new MaterialDialog.Builder(this);
                    dialogBuilder
                            .setTitle("警告！")
                            .setTitleColor(ContextCompat.getColor(getApplicationContext(), R.color.primary_dark))
                            .setMessage("案卷号格式有误\n" + fileNumber)
                            .setButtonTextColor(ContextCompat.getColor(getApplicationContext(), R.color.tbgreen))
                            .setPositiveButton("确认", null)
                            .setWidth(900)
                            .setHeight(650);
                    MaterialDialog dialog = dialogBuilder.create();
                    dialog.show();
                } else {
                    if (fileNumber.length() == 0) {
                        fileNumber = "0";
                    }
                    Log.w("format", String.format("%03d", Integer.valueOf(fileNumber)));
                    Intent i = new Intent();
                    i.putExtra("firstLine", op1 + "$" + op2 + "$" + String.format("%03d", Integer.valueOf(fileNumber)));
                    setResult(RESULT_OK, i);
                    finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
