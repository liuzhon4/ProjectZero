package demo.projectZero;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.mancj.materialsearchbar.MaterialSearchBar;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eu.long1.projectZero.R;

public class CigaretteLogActivity extends AppCompatActivity {

    private MaterialSearchBar searchBar;
    private EditText amountInput;
    private View positiveAction;

    private static final int ACTIVITY_REQUEST_CODE_SCAN = 0;

    String barCode;
    TextView barCodeTextView;
    TextView nameTextView;
    TextView priceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cigarette_log);

        //stop soft keyboard pushing up the view
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


        searchBar = (MaterialSearchBar) findViewById(R.id.cigaretteLog);

        searchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {

            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                List<String> result = getResult(searchBar.getText());
//                showDialog(searchBar.getText());
                if (result.get(0).equals("未知")) {
                    showNotExistDialog(result.get(2));
                } else {
                    showDialog(result);
                }
            }

            @Override
            public void onButtonClicked(int buttonCode) {
                switch (buttonCode) {
                    case (MaterialSearchBar.BUTTON_SPEECH):
                        Intent i = new Intent(getApplicationContext(), SimpleScannerActivity.class);
                        startActivityForResult(i, ACTIVITY_REQUEST_CODE_SCAN);
                    case (MaterialSearchBar.BUTTON_BACK):
                        searchBar.disableSearch();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTIVITY_REQUEST_CODE_SCAN) {
            if (resultCode == RESULT_OK) {
                barCode = data.getStringExtra("barCode");
                List<String> result = getResult(barCode);
                if (result.get(0).equals("未知")) {
                    showNotExistDialog(result.get(2));
                } else {
                    showDialog(result);
                }
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
                Toast.makeText(getApplicationContext(), "卷烟录入完成", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void showDialog(List<String> input) {
        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .title("卷烟登记")
                .titleColorRes(R.color.tbgreen)
                .customView(R.layout.cigarette_log_dialog, true)
                .positiveText("确认")
                .negativeText("取消")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Toast.makeText(getApplicationContext(),
                                "名称：" + nameTextView.getText() +  " 数量: " + amountInput.getText().toString(),
                                Toast.LENGTH_LONG).show();
                    }
                })
                .build();

        positiveAction = dialog.getActionButton(DialogAction.POSITIVE);
        amountInput = (EditText) dialog.getCustomView().findViewById(R.id.amount);
        amountInput.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        positiveAction.setEnabled(s.toString().trim().length() > 0);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {}
                });

        //change text after custom view
        View v = dialog.getCustomView();
        nameTextView = (TextView) v.findViewById(R.id.nameTextView);
        barCodeTextView = (TextView) v.findViewById(R.id.barCodeTextView);
        priceTextView = (TextView) v.findViewById(R.id.priceTextView);

        nameTextView.setText(input.get(0));
        barCodeTextView.setText(input.get(2));
        priceTextView.setText(input.get(1));

        v.findViewById(R.id.amount).requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        dialog.show();
        positiveAction.setEnabled(false); // disabled by default

        searchBar.disableSearch();
    }

    public List<String> getResult(String barCode) {
        AssetManager am = getAssets();
        String line;
        List<String> result = Arrays.asList("未知", "未知", barCode);
        try{
            InputStream is = am.open("db.txt");
            BufferedReader reader = new BufferedReader((new InputStreamReader(is)));
            while ((line = reader.readLine()) != null) {
                List<String> lineList = Arrays.asList(line.split("-"));
                if (barCode.equals(lineList.get(2)) || barCode.equals(lineList.get(3))) {
                    result.set(0, lineList.get(4));
                    result.set(1, lineList.get(6));
                }
            }
        } catch (IOException e) {
            Log.e("assetFile, db.txt", e.getMessage());
        }
        return result;
    }

    public void showNotExistDialog(String barCode) {
        new MaterialDialog.Builder(this)
                .title("注意！")
                .titleColorRes(R.color.primary)
                .content(barCode + "\n卷烟条码不存在！")
                .positiveText("确认！")
                .positiveColorRes(R.color.tbgreen)
                .show();
    }

}
