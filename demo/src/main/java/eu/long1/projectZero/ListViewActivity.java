package eu.long1.projectZero;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.yuyh.easyadapter.abslistview.EasyLVAdapter;
import com.yuyh.easyadapter.recyclerview.EasyRVAdapter;

import java.util.Arrays;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    private ListView listView;
    private ListViewAdapter mLVAdapter;

    private List<Bean> list = Arrays.asList(
            new Bean(R.drawable.ic_check_box, "案件性质、地区、案卷号"),
            new Bean(R.drawable.ic_check_box_outline, "案由"),
            new Bean(R.drawable.ic_check_box_outline, "当事人信息"),
            new Bean(R.drawable.ic_check_box_outline, "涉案卷烟登记"),
            new Bean(R.drawable.ic_check_box_outline, "拍照"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        listView = (ListView) findViewById(R.id.lv);
        mLVAdapter = new ListViewAdapter(this, list, R.layout.item_list_view_1, R.layout.item_list_view_1);
        listView.setAdapter(mLVAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case (0):
//                        Toast.makeText(getApplicationContext(), "pressed position " + position, Toast.LENGTH_LONG).show();
                        View single = listView.getChildAt(0);
                        ImageView imgView = (ImageView) single.findViewById(R.id.img);
                        imgView.setImageResource(R.drawable.ic_check_box);
                    case (1):
                        Toast.makeText(getApplicationContext(), "pressed position " + position, Toast.LENGTH_LONG).show();
                    case (2):
                        Toast.makeText(getApplicationContext(), "pressed position " + position, Toast.LENGTH_LONG).show();
                    case (3):
                        Toast.makeText(getApplicationContext(), "pressed position " + position, Toast.LENGTH_LONG).show();
                    case (4):
                        Toast.makeText(getApplicationContext(), "pressed position " + position, Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
