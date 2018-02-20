package eu.long1.projectZero;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PhotoActivity extends AppCompatActivity {

    private ListView listView;
    private EnterInfoListAdapter mLVAdapter;
    private ArrayList<Boolean> checkList = new ArrayList<>(
            Arrays.asList(false, false, false, false, false));
    private String firstReturnLine, secondReturnLine, thirdReturnLine;
    private static final int ACTIVITY_REQUEST_CODE_ZERO = 0;
    private static final int ACTIVITY_REQUEST_CODE_ONE = 1;
    private static final int ACTIVITY_REQUEST_CODE_TWO = 2;

    private List<Bean> list = Arrays.asList(
            new Bean(R.drawable.ic_check_box_outline, "身份证照片"),
            new Bean(R.drawable.ic_check_box_outline, "卷烟照片"),
            new Bean(R.drawable.ic_check_box_outline, "工商营业执照照片"),
            new Bean(R.drawable.ic_check_box_outline, "烟草专卖许可证照片"),
            new Bean(R.drawable.ic_check_box_outline, "待定"),
            new Bean(R.drawable.ic_check_box_outline, "待定"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_view);

        listView = (ListView) findViewById(R.id.lv);
        mLVAdapter = new EnterInfoListAdapter(this, list, R.layout.item_list_view_1, R.layout.item_list_view_1);
        listView.setAdapter(mLVAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (list.get(position).getName().equals("身份证照片")) {
                        Toast.makeText(getApplicationContext(), list.get(position).getName(), Toast.LENGTH_LONG).show();
//                        Intent i0 = new Intent(getApplicationContext(), CharacterActivity.class);
//                        startActivityForResult(i0, ACTIVITY_REQUEST_CODE_ZERO);
                    }
                    if(list.get(position).getName().equals("卷烟照片")) {
                        Toast.makeText(getApplicationContext(), list.get(position).getName(), Toast.LENGTH_LONG).show();
//                        Intent i1 = new Intent(getApplicationContext(), ReasonListActivity.class);
//                        startActivityForResult(i1, ACTIVITY_REQUEST_CODE_ONE);
                    }
                    if(list.get(position).getName().equals("工商营业执照照片")) {
                        Toast.makeText(getApplicationContext(), list.get(position).getName(), Toast.LENGTH_LONG).show();
//                        Intent i2 = new Intent(getApplicationContext(), TargetActivity.class);
//                        startActivityForResult(i2, ACTIVITY_REQUEST_CODE_TWO);
                    }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTIVITY_REQUEST_CODE_ZERO) {
            if (resultCode == RESULT_OK) {
                firstReturnLine = data.getStringExtra("firstLine");
                setImageAndCheckList(0, true);
            }
        }
        if (requestCode == ACTIVITY_REQUEST_CODE_ONE) {
            if (resultCode == RESULT_OK) {
                secondReturnLine = data.getStringExtra("secondLine");
                setImageAndCheckList(1, true);
            } else {
                secondReturnLine = null;
                setImageAndCheckList(1, false);
            }
        }
        if (requestCode == ACTIVITY_REQUEST_CODE_TWO) {
            if(resultCode == RESULT_OK) {
                thirdReturnLine = data.getStringExtra("thirdLine");
                setImageAndCheckList(2, true);
            } else {
                setImageAndCheckList(2, false);
            }
        }
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
                Toast.makeText(getApplicationContext(), "拍照完成按钮", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setImageAndCheckList(Integer index, Boolean state) {
        View single = listView.getChildAt(index);
        ImageView imgView = (ImageView) single.findViewById(R.id.img);
        if (state) {
            imgView.setImageResource(R.drawable.ic_check_box);
            checkList.set(index, true);
        } else {
            imgView.setImageResource(R.drawable.ic_check_box_outline);
            checkList.set(index, false);
        }
    }
}
