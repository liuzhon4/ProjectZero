package eu.long1.projectZero;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class EnterInfoListActivity extends AppCompatActivity {

    private ListView listView;
    private EnterInfoListAdapter mLVAdapter;
    private ArrayList<Boolean> checkList = new ArrayList<>(
            Arrays.asList(false, false, false, false, false));
    private String firstReturnLine, secondReturnLine;
    private static final int ACTIVITY_REQUEST_CODE_ZERO = 0;

    private List<Bean> list = Arrays.asList(
            new Bean(R.drawable.ic_check_box_outline, "案件性质、地区、案卷号"),
            new Bean(R.drawable.ic_check_box_outline, "案由"),
            new Bean(R.drawable.ic_check_box_outline, "当事人信息"),
            new Bean(R.drawable.ic_check_box_outline, "涉案卷烟登记"),
            new Bean(R.drawable.ic_check_box_outline, "拍照"));

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
                switch (position){
                    case (0):
                        //how to change view pic
//                        View single = listView.getChildAt(0);
//                        ImageView imgView = (ImageView) single.findViewById(R.id.img);
//                        imgView.setImageResource(R.drawable.ic_check_box);
                        Intent i = new Intent(getApplicationContext(), CharacterActivity.class);
                        startActivityForResult(i, ACTIVITY_REQUEST_CODE_ZERO);
                    case (1):
                    case (2):
                    case (3):
                    case (4):
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
                View single = listView.getChildAt(0);
                ImageView imgView = (ImageView) single.findViewById(R.id.img);
                imgView.setImageResource(R.drawable.ic_check_box);
                checkList.set(0, true);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_all_finish, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_all_finish:
                Toast.makeText(getApplicationContext(), "finish button pressed", Toast.LENGTH_LONG).show();
                Log.w("finish", "button pressed");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
