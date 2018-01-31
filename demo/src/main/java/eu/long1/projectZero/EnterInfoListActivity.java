package eu.long1.projectZero;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class EnterInfoListActivity extends AppCompatActivity {

    private ListView listView;
    private EnterInfoListAdapter mLVAdapter;
    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;

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
//                        Toast.makeText(getApplicationContext(),
//                                "position " + position + ": " +
//                                list.get(position).getName(), Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(), CharacterActivity.class);
//                        startActivity(i);
                        startActivityForResult(i, SECOND_ACTIVITY_REQUEST_CODE);
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
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String returnString = data.getStringExtra("firstLine");
                Toast.makeText(getApplicationContext(), returnString, Toast.LENGTH_LONG).show();
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
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
