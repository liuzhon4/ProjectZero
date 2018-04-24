package demo.projectZero;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.ListView;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eu.long1.projectZero.R;

public class ReasonListActivity extends AppCompatActivity {

    private ListView listView;
    private ReasonListAdapter mLVAdapter;
    private ArrayList<Boolean> checkList = new ArrayList<>(
            Arrays.asList(false, false, false, false, false));

    private List<Bean> list = Arrays.asList(
            new Bean(R.drawable.ic_check_box_outline, "未在当地烟草专卖批发企业进货"),
            new Bean(R.drawable.ic_check_box_outline, "无烟草专卖零售许可证经营烟草制品零售业务"),
            new Bean(R.drawable.ic_check_box_outline, "销售专供出口卷烟"),
            new Bean(R.drawable.ic_check_box_outline, "销售无标志外国卷烟"),
            new Bean(R.drawable.ic_check_box_outline, "销售非法生产烟草专卖品"),
            new Bean(R.drawable.ic_check_box_outline, "无烟草专卖品准运证运输烟草专卖品"),
            new Bean(R.drawable.ic_check_box_outline, "待拓展案由"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_view);

        listView = (ListView) findViewById(R.id.lv);
        mLVAdapter = new ReasonListAdapter(this, list, R.layout.item_list_view_2, R.layout.item_list_view_2);
        listView.setAdapter(mLVAdapter);
//        CheckBox cb = (CheckBox) listView.getChildAt(0).findViewById(R.id.cb);

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
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < list.size(); i++) {
                    CheckBox cb = (CheckBox) listView.getChildAt(i).findViewById(R.id.cb);
                    if (cb.isChecked()) {
                        sb.append(cb.getText());
                        sb.append("$");
                    }
                }
                String result = sb.toString();
                if (result.length() == 0) {
                    new MaterialDialog.Builder(this)
                            .title("警告！")
                            .titleColorRes(R.color.primary)
                            .content("请至少选择一个案由")
                            .positiveText("确认")
                            .positiveColorRes(R.color.tbgreen)
                            .show();
                } else {
                    Intent i = new Intent();
                    i.putExtra("secondLine", result.substring(0, result.length() - 1));
                    setResult(RESULT_OK, i);
                    finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
