package eu.long1.projectZero;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.Arrays;

public class CharacterActivity extends AppCompatActivity {

    String[] Option1 = {"简易案件", "一般案件"};
    String[] Option2 = {"东城", "西城", "海淀", "朝阳", "丰台", "门头沟", "石景山",
            "房山", "通州", "顺义", "昌平", "大兴", "怀柔", "平谷", "延庆", "密云"};
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
        materialDesignSpinner1.setText(arrayAdapter1.getItem(0).toString());

        arrayAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, Option2);
        materialDesignSpinner2 = (MaterialBetterSpinner) findViewById(R.id.area);
        materialDesignSpinner2.setAdapter(arrayAdapter2);
        materialDesignSpinner2.setText(arrayAdapter2.getItem(7).toString());

        materialEditText = (MaterialEditText) findViewById(R.id.fileNum);
        String fileNumber = materialEditText.getText().toString();

    }
}
