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

public class ReasonListActivity extends AppCompatActivity {

    private ListView listView;
    private EnterInfoListAdapter mLVAdapter;
    private ArrayList<Boolean> checkList = new ArrayList<>(
            Arrays.asList(false, false, false, false, false));
    private String firstReturnLine, secondReturnLine;

    private List<Bean> list = Arrays.asList(
            new Bean(R.drawable.ic_check_box_outline, "1"),
            new Bean(R.drawable.ic_check_box_outline, "2"),
            new Bean(R.drawable.ic_check_box_outline, "3"),
            new Bean(R.drawable.ic_check_box_outline, "4"),
            new Bean(R.drawable.ic_check_box_outline, "5"));

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
                    case (1):
                    case (2):
                    case (3):
                    case (4):
                }
            }
        });
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
                Toast.makeText(getApplicationContext(), "button pressed", Toast.LENGTH_LONG).show();
                Log.w("finish", "button pressed");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
